package com.magdaleno.amaysim.json.accounts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class AccountsAttributes {
    @SerializedName("payment-type")
    private String paymentType;
    @SerializedName("unbilled-charges")
    private String unbilledCharges;
    @SerializedName("next-billing-date")
    private String nextBillingDate;
    @SerializedName("title")
    private String title;
    @SerializedName("first-name")
    private String firstName;
    @SerializedName("last-name")
    private String lastName;
    @SerializedName("date-of-birth")
    private String dateOfBirth;
    @SerializedName("contact-number")
    private String contactNumber;
    @SerializedName("email-address")
    private String emailAddress;
    @SerializedName("email-address-verified")
    private boolean emailAddressVerified;
    @SerializedName("email-subscription-status")
    private boolean emailSubscriptionStatus;

    public String getPaymentType() {
        return paymentType;
    }

    public String getUnbilledCharges() {
        return unbilledCharges;
    }

    public String getNextBillingDate() {
        return nextBillingDate;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public boolean isEmailAddressVerified() {
        return emailAddressVerified;
    }

    public boolean isEmailSubscriptionStatus() {
        return emailSubscriptionStatus;
    }
}
