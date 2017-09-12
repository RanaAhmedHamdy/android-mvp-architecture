package com.example.enozom.storesapp.data.source.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.enozom.storesapp.Constants;
import com.example.enozom.storesapp.CustomApplication;
import com.example.enozom.storesapp.data.Store;
import com.example.enozom.storesapp.data.source.StoresDataSource;
import com.example.enozom.storesapp.utils.APIUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by enozom on 9/11/2017.
 */

public class StoresRemoteDataSource implements StoresDataSource{
    private static StoresRemoteDataSource INSTANCE;
    private String TAG = "EventRemoteDataSource";

    public static StoresRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StoresRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getStores(@NonNull final StoreGenericCallback callback) {
        APIUtils.getInstance(CustomApplication.getAppContext()).requestJsonArray(APIUtils.GET_METHOD, Constants.STORES_URL, null, true, new APIUtils.VolleyCallbackArray() {

            @Override
            public void onSuccess(@Nullable JSONArray result) {
                if (result != null) {
                    try {
                        ArrayList<Store> events = Store.serializeFromJson(result);
                        Log.d(TAG, Integer.toString(events.size()));
                        callback.onSuccess(events);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
                if(message != null) {
                    callback.onCustomError(message);
                }
            }
        });
    }
}
