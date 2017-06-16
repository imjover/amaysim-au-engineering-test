package com.magdaleno.amaysim.json;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class Collection implements Serializable { // implements JsonDeserializer<Collection> {
    @SerializedName("data")
    private Resource data;
    @SerializedName("included")
    private ArrayList<Resource> included;

    public Resource getData() {
        return data;
    }

    public ArrayList<Resource> getIncluded() {
        return included;
    }

//    @Override
//    public Collection deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        JsonObject jobject = json.getAsJsonObject();
//
//
////        JsonParser parser = new JsonParser();
////        JsonObject object1 = parser.parse(jobject).getAsJsonObject().getAsJsonObject("object1");
//
//        Resource data = context.deserialize(jobject.get("data"), Resource.class);
//        Map<String, Resource> included = new HashMap<>();
//
//        Type includedType = new TypeToken< List<Resource>>(){}.getType();
//        List<Resource> includedList = context.deserialize(jobject.get("included"), includedType);
//
//        for(Resource resource : includedList) {
//            included.put(resource.getType(), resource);
//        }
//
//        return new Collection(data, included);
//    }
}
