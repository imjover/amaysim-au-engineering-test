package com.magdaleno.amaysim.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.magdaleno.amaysim.BuildConfig;
import com.magdaleno.amaysim.R;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class NetworkSender implements RemoteContract.RemoteService {

    private final Context mContext;

    public NetworkSender(Context context) {
        this.mContext = context;
    }

    @Override
    public void send(String fileName, final RemoteContract.RemoteCallback callback) {

        // Check Internet connectivity.
        if(!CheckNetwork.isNetworkAvailable(mContext)) {
            callback.onFailure(mContext.getString(R.string.no_connectivity_msg));
            return;
        }

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<JsonObject> file = service.getFile(fileName);

        file.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                switch(response.code()) {
                    case 200: // Success
                        callback.onResponse(response.body().toString());
                        break;
                    default:
                        callback.onFailure(mContext.getString(R.string.failed_contacting_server_msg));
                        break;
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                callback.onFailure(mContext.getString(R.string.failed_contacting_server_msg));
            }
        });
    }
}
