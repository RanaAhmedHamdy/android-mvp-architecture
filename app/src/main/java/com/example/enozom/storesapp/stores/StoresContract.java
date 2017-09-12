package com.example.enozom.storesapp.stores;

import com.example.enozom.storesapp.BasePresenter;
import com.example.enozom.storesapp.BaseView;
import com.example.enozom.storesapp.data.Store;

import java.util.ArrayList;

/**
 * Created by enozom on 9/11/2017.
 */

public interface StoresContract {
    interface Presenter extends BasePresenter {
        void getStores();
        void filterStoresByName(String text);
    }

    interface View extends BaseView<Presenter> {
        void setStores(ArrayList<Store> stores);
        void updateStores(ArrayList<Store> filteredStores);
    }
}
