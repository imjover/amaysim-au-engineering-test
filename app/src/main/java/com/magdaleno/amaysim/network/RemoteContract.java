package com.magdaleno.amaysim.network;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public interface RemoteContract {
    interface RemoteService {
        void send(String fileName, RemoteCallback callback);
    }

    interface RemoteCallback {
        void onResponse(String response);
        void onFailure(String message);
    }
}
