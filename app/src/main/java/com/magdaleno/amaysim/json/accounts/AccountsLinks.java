package com.magdaleno.amaysim.json.accounts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class AccountsLinks {
    @SerializedName("self")
    private String self;

    public String getSelf() {
        return self;
    }
}
