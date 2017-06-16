package com.magdaleno.amaysim.network;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public interface GitHubService {
    @GET("amaysim-au/engineering-test-resources/master/{file}")
    Call<JsonObject> getFile(@Path("file") String file);
}
