package com.magdaleno.amaysim.json.products;

import com.google.gson.annotations.SerializedName;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class ProductsAttributes {
    @SerializedName("name")
    private String name;
    @SerializedName("included-data")
    private Integer includedData;
    @SerializedName("included-credit")
    private Integer includedCredit;
    @SerializedName("included-international-talk")
    private Integer includedInternationalTalk;
    @SerializedName("unlimited-text")
    private boolean unlimitedText;
    @SerializedName("unlimited-talk")
    private boolean unlimitedTalk;
    @SerializedName("unlimited-international-text")
    private boolean unlimitedInternationalText;
    @SerializedName("unlimited-international-talk")
    private boolean unlimitedInternationalTalk;
    @SerializedName("price")
    private int price;

    public String getName() {
        return name;
    }

    public Integer getIncludedData() {
        return includedData;
    }

    public Integer getIncludedCredit() {
        return includedCredit;
    }

    public Integer getIncludedInternationalTalk() {
        return includedInternationalTalk;
    }

    public boolean isUnlimitedText() {
        return unlimitedText;
    }

    public boolean isUnlimitedTalk() {
        return unlimitedTalk;
    }

    public boolean isUnlimitedInternationalText() {
        return unlimitedInternationalText;
    }

    public boolean isUnlimitedInternationalTalk() {
        return unlimitedInternationalTalk;
    }

    public int getPrice() {
        return price;
    }
}
