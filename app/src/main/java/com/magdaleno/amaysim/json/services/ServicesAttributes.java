package com.magdaleno.amaysim.json.services;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class ServicesAttributes {
    @SerializedName("msn")
    private String msn;
    @SerializedName("credit")
    private int credit;
    @SerializedName("credit-expiry")
    private String creditExpiry;
    @SerializedName("data-usage-threshold")
    private boolean dataUsageThreshold;

    public String getMsn() {
        return msn;
    }

    public int getCredit() {
        return credit;
    }

    public String getCreditExpiry() {
        return creditExpiry;
    }

    public boolean isDataUsageThreshold() {
        return dataUsageThreshold;
    }
}
