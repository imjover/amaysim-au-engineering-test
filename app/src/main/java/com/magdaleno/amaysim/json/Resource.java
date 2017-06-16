package com.magdaleno.amaysim.json;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class Resource implements Serializable {
    @SerializedName("type")
    private String type;
    @SerializedName("id")
    private String id;
    @SerializedName("attributes")
    private JsonObject attributes;
    @SerializedName("links")
    private JsonObject links;
    @SerializedName("relationships")
    private JsonObject relationships;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public JsonObject getAttributes() {
        return attributes;
    }

    public JsonObject getLinks() {
        return links;
    }

    public JsonObject getRelationships() {
        return relationships;
    }
}
