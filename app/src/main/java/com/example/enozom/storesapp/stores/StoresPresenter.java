package com.example.enozom.storesapp.stores;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.enozom.storesapp.data.Store;
import com.example.enozom.storesapp.data.source.StoresDataSource;
import com.example.enozom.storesapp.data.source.StoresRepository;

import java.util.ArrayList;

/**
 * Created by enozom on 9/11/2017.
 */

public class StoresPresenter implements StoresContract.Presenter{

    private final StoresRepository storesRepository;
    private final StoresContract.View storesView;
    private final String TAG = "StoresPresenter";
    private ArrayList<Store> allStores;

    public StoresPresenter(@NonNull StoresRepository storesRepository, @NonNull StoresContract.View storesView) {
        this.storesView = storesView;
        this.storesRepository = storesRepository;
        storesView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void filterStoresByName(String text) {
        ArrayList<Store> filteredStores = new ArrayList<>();
        if(allStores != null) {
            for (int i = 0; i < allStores.size(); i++) {
                if (allStores.get(i).getStoreName().toLowerCase().contains(text.toLowerCase())) {
                    filteredStores.add(allStores.get(i));
                }
            }
            storesView.updateStores(filteredStores);
        }
    }

    @Override
    public void getStores() {

        storesRepository.getStores(new StoresDataSource.StoreGenericCallback() {

            @Override
            public void onSuccess(ArrayList<Store> stores) {
                //storesView.setLoadingIndicator(false);
                allStores = stores;
                storesView.setStores(stores);
            }

            @Override
            public void onServerError() {
                //storesView.setLoadingIndicator(false);
            }

            @Override
            public void onConnectionError() {
                //storesView.setLoadingIndicator(false);
            }

            @Override
            public void onCustomError(@NonNull String message) {
                Log.d(TAG + " error:", message);
                //storesView.setLoadingIndicator(false);
            }
        });
    }
}
