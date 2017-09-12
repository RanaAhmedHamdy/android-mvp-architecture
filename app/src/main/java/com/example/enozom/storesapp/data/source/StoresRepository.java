package com.example.enozom.storesapp.data.source;

import android.support.annotation.NonNull;

import com.example.enozom.storesapp.data.Store;

import java.util.ArrayList;

/**
 * Created by enozom on 9/11/2017.
 */

public class StoresRepository implements StoresDataSource {
    private static StoresRepository INSTANCE = null;
    private final StoresDataSource storesDataSource;

    public StoresRepository(StoresDataSource storesDataSource) {
        this.storesDataSource = storesDataSource;
    }

    public static StoresRepository getInstance(StoresDataSource storesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new StoresRepository(storesRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getStores(@NonNull final StoreGenericCallback callback) {
            storesDataSource.getStores(new StoresDataSource.StoreGenericCallback() {
                @Override
                public void onSuccess(ArrayList<Store> stores) {
                    callback.onSuccess(stores);
                }

                @Override
                public void onServerError() {
                    callback.onServerError();
                }

                @Override
                public void onConnectionError() {
                    callback.onConnectionError();
                }

                @Override
                public void onCustomError(@NonNull String message) {
                    callback.onCustomError(message);
                }
            });
        }
    }
