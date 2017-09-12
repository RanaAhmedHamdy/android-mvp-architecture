package com.example.enozom.storesapp.data.source;

import android.support.annotation.NonNull;

import com.example.enozom.storesapp.data.Store;

import java.util.ArrayList;

/**
 * Created by enozom on 9/11/2017.
 */

public interface StoresDataSource {

    interface StoreGenericCallback {

        void onSuccess(ArrayList<Store> stores);

        void onServerError();

        void onConnectionError();

        void onCustomError(@NonNull String message);
    }

    void getStores(@NonNull StoreGenericCallback callback);
}
