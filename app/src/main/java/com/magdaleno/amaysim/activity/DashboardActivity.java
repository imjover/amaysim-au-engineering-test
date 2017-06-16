package com.magdaleno.amaysim.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.magdaleno.amaysim.BuildConfig;
import com.magdaleno.amaysim.R;
import com.magdaleno.amaysim.json.JsonValues;
import com.magdaleno.amaysim.json.Resource;
import com.magdaleno.amaysim.json.accounts.AccountsAttributes;
import com.magdaleno.amaysim.json.Collection;
import com.magdaleno.amaysim.json.products.ProductsAttributes;
import com.magdaleno.amaysim.json.services.ServicesAttributes;
import com.magdaleno.amaysim.json.subscriptions.SubscriptionsAttributes;
import com.magdaleno.amaysim.util.AssetUtil;
import com.magdaleno.amaysim.util.Data;
import com.magdaleno.amaysim.util.Monetary;

import org.w3c.dom.Text;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/15/2017.
 */

public class DashboardActivity extends AppCompatActivity {

    public static String RESPONSE = "RESPONSE";
    private Collection mCollection;
    private AccountsAttributes accountsAttributes;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    // Accounts
    private TextView tv_name;
    private TextView tv_contact;
    private TextView tv_email;
    private ImageView iv_email_verified;
    private TextView tv_birth_date;
    private TextView tv_payment_type;
    private TextView tv_unbilled_charges;
    private TextView tv_next_billing_date;
    // Services
    private TextView tv_remaining_balance;
    private TextView tv_expiry_date;
    private ImageView iv_threshold;
    // Subscriptions
    private TextView tv_data_balance;
    private TextView tv_credit_balance;
    private TextView tv_rollover_data_balance;
    private TextView tv_rollover_credit_balance;
    private TextView tv_international_talk_balance;
    private TextView tv_subscription_expiry_date;
    private ImageView iv_auto_renew;
    private ImageView iv_primary_subscription;
    // Products
    private TextView tv_product_name;
    private TextView tv_product_amount;
    private TextView tv_included_data;
    private TextView tv_included_credit;
    private TextView tv_included_international_talk;
    private ImageView iv_unlimited_text;
    private ImageView iv_unlimited_talk;
    private ImageView iv_unlimited_international_text;
    private ImageView iv_unlimited_international_talk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_act);
        init();
        displayUserInfo();
    }

    private void init() {
        // Accounts
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_email = (TextView) findViewById(R.id.tv_email);
        iv_email_verified = (ImageView) findViewById(R.id.iv_email_verified);
        tv_birth_date = (TextView) findViewById(R.id.tv_birth_date);
        tv_payment_type = (TextView) findViewById(R.id.tv_payment_type);
        tv_unbilled_charges = (TextView) findViewById(R.id.tv_unbilled_charges);
        tv_next_billing_date = (TextView) findViewById(R.id.tv_next_billing_date);
        // Services
        tv_remaining_balance = (TextView) findViewById(R.id.tv_remaining_balance);
        tv_expiry_date = (TextView) findViewById(R.id.tv_expiry_date);
        iv_threshold = (ImageView) findViewById(R.id.iv_threshold);
        // Subscriptions
        tv_data_balance = (TextView) findViewById(R.id.tv_data_balance);
        tv_credit_balance = (TextView) findViewById(R.id.tv_credit_balance);
        tv_rollover_data_balance = (TextView) findViewById(R.id.tv_rollover_data_balance);
        tv_rollover_credit_balance = (TextView) findViewById(R.id.tv_rollover_credit_balance);
        tv_international_talk_balance = (TextView) findViewById(R.id.tv_international_talk_balance);
        tv_subscription_expiry_date = (TextView) findViewById(R.id.tv_subscription_expiry_date);
        iv_auto_renew = (ImageView) findViewById(R.id.iv_auto_renew);
        iv_primary_subscription = (ImageView) findViewById(R.id.iv_primary_subscription);
        // Products
        tv_product_name = (TextView) findViewById(R.id.tv_product_name);
        tv_product_amount = (TextView) findViewById(R.id.tv_product_amount);
        tv_included_data = (TextView) findViewById(R.id.tv_included_data);
        tv_included_credit = (TextView) findViewById(R.id.tv_included_credit);
        tv_included_international_talk = (TextView) findViewById(R.id.tv_included_international_talk);
        iv_unlimited_text = (ImageView) findViewById(R.id.iv_unlimited_text);
        iv_unlimited_talk = (ImageView) findViewById(R.id.iv_unlimited_talk);
        iv_unlimited_international_text = (ImageView) findViewById(R.id.iv_unlimited_international_text);
        iv_unlimited_international_talk = (ImageView) findViewById(R.id.iv_unlimited_international_talk);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        String response = getIntent().getStringExtra(DashboardActivity.RESPONSE);
        mCollection = new Gson().fromJson(response, Collection.class);
    }

    private void displayUserInfo() {
        setupAccount();

        for(Resource resource : mCollection.getIncluded()) {
            if(resource.getType().equals(JsonValues.Types.SERVICES))
                setupServices(resource);
            else if(resource.getType().equals(JsonValues.Types.SUBSCRIPTIONS))
                setupSubscriptions(resource);
            else if(resource.getType().equals(JsonValues.Types.PRODUCTS))
                setupProducts(resource);
        }
    }

    private void setupAccount() {
        // get Accounts
        if(mCollection.getData().getType().equals(JsonValues.Types.ACCOUNTS)) {
            accountsAttributes = new Gson().fromJson(
                    mCollection.getData().getAttributes().toString(), AccountsAttributes.class);

            final String name = accountsAttributes.getTitle() + ". " +
                    accountsAttributes.getFirstName() + " " +
                    accountsAttributes.getLastName();
            tv_name.setText(name);
            tv_contact.setText(accountsAttributes.getContactNumber());
            tv_email.setText(accountsAttributes.getEmailAddress());
            iv_email_verified.setImageResource( JsonValues.BoolTypes.getImageRes( accountsAttributes.isEmailAddressVerified()));

            tv_birth_date.setText( accountsAttributes.getDateOfBirth());

            if(accountsAttributes.getPaymentType()!=null) {
                tv_payment_type.setText( accountsAttributes.getPaymentType());
            }
            if(accountsAttributes.getUnbilledCharges()!=null) {
                tv_unbilled_charges.setText( accountsAttributes.getUnbilledCharges());
            }
            if(accountsAttributes.getNextBillingDate()!=null) {
                tv_next_billing_date.setText(accountsAttributes.getNextBillingDate());
            }

            // Setup appBarLayout here, because the Title (Customer's name) is only available in this function
            AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {
                        collapsingToolbarLayout.setTitle(name);
                        isShow = true;
                    } else if(isShow) {
                        collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                        isShow = false;
                    }
                }
            });
        }
    }

    private void setupServices(Resource resource) {
        ServicesAttributes servicesAttributes = new Gson().fromJson(
                resource.getAttributes().toString(), ServicesAttributes.class);

        tv_remaining_balance.setText( getString(R.string.currency_unit) + " " +
                Monetary.valueOf( Monetary.addDecimalToBigDecimal( servicesAttributes.getCredit())));
        tv_expiry_date.setText( servicesAttributes.getCreditExpiry());
        iv_threshold.setImageResource( JsonValues.BoolTypes.getImageRes( servicesAttributes.isDataUsageThreshold()));
    }

    private void setupSubscriptions(Resource resource) {
        SubscriptionsAttributes subscriptionsAttributes = new Gson().fromJson(
                resource.getAttributes().toString(), SubscriptionsAttributes.class);

        if(subscriptionsAttributes.getIncludedDataBalance()!=null) {
            tv_data_balance.setText(Data.valueOf( Data.mbToGB_toBigDecimal(subscriptionsAttributes.getIncludedDataBalance())) + " " +
                    getString(R.string.data_unit));
        }
        if(subscriptionsAttributes.getIncludedCreditBalance()!=null) {
            tv_credit_balance.setText(getString(R.string.currency_unit) + " " +
                    Monetary.valueOf(Monetary.addDecimalToBigDecimal(subscriptionsAttributes.getIncludedCreditBalance())));
        }
        if(subscriptionsAttributes.getIncludedRolloverDataBalance()!=null) {
            tv_rollover_data_balance.setText(Data.valueOf( Data.mbToGB_toBigDecimal(subscriptionsAttributes.getIncludedRolloverDataBalance())) + " " +
                    getString(R.string.data_unit));
        }
        if(subscriptionsAttributes.getIncludedRolloverCreditBalance()!=null) {
            tv_rollover_credit_balance.setText(getString(R.string.currency_unit) + " " +
                    Monetary.valueOf(Monetary.addDecimalToBigDecimal(subscriptionsAttributes.getIncludedRolloverCreditBalance())));
        }
        if(subscriptionsAttributes.getIncludedInternationalTalkBalance()!=null) {
            tv_international_talk_balance.setText(subscriptionsAttributes.getIncludedInternationalTalkBalance() + " " +
                    getString(R.string.talk_unit));
        }
        tv_subscription_expiry_date.setText(subscriptionsAttributes.getExpiryDate());
        iv_auto_renew.setImageResource( JsonValues.BoolTypes.getImageRes( subscriptionsAttributes.isAutoRenewal()));
        iv_primary_subscription.setImageResource( JsonValues.BoolTypes.getImageRes( subscriptionsAttributes.isPrimarySubscription()));
    }

    private void setupProducts(Resource resource) {
        ProductsAttributes productsAttributes = new Gson().fromJson(
                resource.getAttributes().toString(), ProductsAttributes.class);

        tv_product_name.setText( productsAttributes.getName());
        tv_product_amount.setText( getString(R.string.currency_unit) + " " +
                Monetary.valueOf(Monetary.addDecimalToBigDecimal(productsAttributes.getPrice())));

        if(productsAttributes.getIncludedData()!=null) {
            tv_included_data.setText(Data.valueOf( Data.mbToGB_toBigDecimal(productsAttributes.getIncludedData())) + " " +
                    getString(R.string.data_unit));
        }
        if(productsAttributes.getIncludedCredit()!=null) {
            tv_included_credit.setText(getString(R.string.currency_unit) + " " +
                    Monetary.valueOf(Monetary.addDecimalToBigDecimal(productsAttributes.getIncludedCredit())));
        }
        if(productsAttributes.getIncludedInternationalTalk()!=null) {
            tv_included_international_talk.setText(productsAttributes.getIncludedInternationalTalk() + " " +
                    getString(R.string.talk_unit)
            );
        }
        iv_unlimited_text.setImageResource( JsonValues.BoolTypes.getImageRes( productsAttributes.isUnlimitedText()));
        iv_unlimited_talk.setImageResource( JsonValues.BoolTypes.getImageRes( productsAttributes.isUnlimitedTalk()));
        iv_unlimited_international_text.setImageResource( JsonValues.BoolTypes.getImageRes( productsAttributes.isUnlimitedInternationalText()));
        iv_unlimited_international_talk.setImageResource( JsonValues.BoolTypes.getImageRes( productsAttributes.isUnlimitedInternationalTalk()));
    }
}
