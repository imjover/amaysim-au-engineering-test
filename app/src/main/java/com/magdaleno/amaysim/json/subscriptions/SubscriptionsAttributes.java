package com.magdaleno.amaysim.json.subscriptions;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class SubscriptionsAttributes {
    @SerializedName("included-data-balance")
    private Integer includedDataBalance;
    @SerializedName("included-credit-balance")
    private Integer includedCreditBalance;
    @SerializedName("included-rollover-credit-balance")
    private Integer includedRolloverCreditBalance;
    @SerializedName("included-rollover-data-balance")
    private Integer includedRolloverDataBalance;
    @SerializedName("included-international-talk-balance")
    private Integer includedInternationalTalkBalance;
    @SerializedName("expiry-date")
    private String expiryDate;
    @SerializedName("auto-renewal")
    private boolean autoRenewal;
    @SerializedName("primary-subscription")
    private boolean primarySubscription;

    public Integer getIncludedDataBalance() {
        return includedDataBalance;
    }

    public Integer getIncludedCreditBalance() {
        return includedCreditBalance;
    }

    public Integer getIncludedRolloverCreditBalance() {
        return includedRolloverCreditBalance;
    }

    public Integer getIncludedRolloverDataBalance() {
        return includedRolloverDataBalance;
    }

    public Integer getIncludedInternationalTalkBalance() {
        return includedInternationalTalkBalance;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean isAutoRenewal() {
        return autoRenewal;
    }

    public boolean isPrimarySubscription() {
        return primarySubscription;
    }
}
