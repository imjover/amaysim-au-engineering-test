package com.magdaleno.amaysim.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.magdaleno.amaysim.R;
import com.magdaleno.amaysim.json.Collection;
import com.magdaleno.amaysim.json.JsonValues;
import com.magdaleno.amaysim.json.Resource;
import com.magdaleno.amaysim.json.services.ServicesAttributes;
import com.magdaleno.amaysim.network.NetworkSender;
import com.magdaleno.amaysim.network.RemoteContract;
import com.magdaleno.amaysim.util.AssetUtil;
import com.tmxlr.lib.driodvalidatorlight.Form;
import com.tmxlr.lib.driodvalidatorlight.helper.RegexTemplate;

import java.util.ArrayList;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class LoginActivity extends Activity implements View.OnClickListener {

    private MaterialDialog progressDialog;
    private CoordinatorLayout coordinator_layout;
    private EditText et_msn;
    private Button btn_login;

    private Form mFormValidator;

    private String mResponse;
    private String mMsn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);
        init();
        getData();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_login:
                // Verify login
                if(mFormValidator.validate()) {
                    if(mResponse==null) {
                        getData();
                        return;
                    }
                    if(!et_msn.getText().toString().equals( mMsn)) {
                        showSnackBar(R.string.invalid_msn_validation);
                        return;
                    }
                    clearForm();
                    Intent intent = new Intent(this, SplashActivity.class);
                    intent.putExtra(DashboardActivity.RESPONSE, mResponse);
                    startActivity(intent);
                }
                break;
        }
    }

    private void init() {
        coordinator_layout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        et_msn = (EditText) findViewById(R.id.et_msn);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        // Set validations.
        mFormValidator = new Form(this);
        mFormValidator.check(et_msn, RegexTemplate.NOT_EMPTY_PATTERN, getString(R.string.required_validation).replace("<variable>", getString(R.string.msn_input_hint)));
    }

    private void getData() {
        showProgressDialog(R.string.server_lbl, R.string.fetching_data_msg, R.drawable.ic_cloud_download);
        RemoteContract.RemoteService networkSender = new NetworkSender(this);
        networkSender.send("collection.json", new RemoteContract.RemoteCallback() {
            @Override
            public void onResponse(String response) {
                mResponse = response;

                // get MSN from response
                Collection collection = new Gson().fromJson(mResponse, Collection.class);
                ArrayList<Resource> included = collection.getIncluded();
                for(Resource resource : included) {
                    if(resource.getType().equals(JsonValues.Types.SERVICES)) {
                        ServicesAttributes attributes = new Gson().fromJson(
                                resource.getAttributes().toString(), ServicesAttributes.class);
                        mMsn = attributes.getMsn();
                        break;
                    }
                }

                dismissProgressDialog();
            }

            @Override
            public void onFailure(String message) {
                dismissProgressDialog();
                showSnackBar(message);
            }
        });
    }

    private void clearForm() {
        et_msn.setText("");
    }

    private void showSnackBar(String message) {
        Snackbar.make(coordinator_layout, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void showSnackBar(int message) {
        Snackbar.make(coordinator_layout, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void dismissProgressDialog() {
        if(progressDialog!=null)
            progressDialog.dismiss();
    }

    private void showProgressDialog(final int title, final int message, final int icon) {
        dismissProgressDialog();

        this.runOnUiThread(
                new Runnable() {
                    public void run() {
                        progressDialog = new MaterialDialog.Builder(LoginActivity.this)
                                .title(title)
                                .content(message)
                                .iconRes(icon)
                                .progress(true, 0)
                                .progressIndeterminateStyle(true)
                                .cancelable(false)
                                .show();
                    }
                }
        );
    }
}
