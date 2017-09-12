package com.example.enozom.storesapp.data;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by enozom on 9/11/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Store {

    @Nullable
    @JsonProperty("StoreID")
    private int StoreID;

    @Nullable
    @JsonProperty("StoreName")
    private String StoreName;

    @Nullable
    @JsonProperty("StoreDescription")
    private String StoreDescription;

    @Nullable
    @JsonProperty("StoreLogo")
    private String StoreLogo;

    public Store() {
    }

    public Store(int storeID, String storeName, String storeDescription, String storeLogo) {
        StoreID = storeID;
        StoreName = storeName;
        StoreDescription = storeDescription;
        StoreLogo = storeLogo;
    }

    public void setStoreID(@Nullable int storeID) {
        StoreID = storeID;
    }

    public void setStoreName(@Nullable String storeName) {
        StoreName = storeName;
    }

    public void setStoreDescription(@Nullable String storeDescription) {
        StoreDescription = storeDescription;
    }

    public void setStoreLogo(@Nullable String storeLogo) {
        StoreLogo = storeLogo;
    }

    public String getStoreName() {
        return StoreName;
    }

    public String getStoreDescription() {
        return StoreDescription;
    }

    public String getStoreLogo() {
        return StoreLogo;
    }

    public static ArrayList<Store> serializeFromJson(JSONArray storesJSON) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        ArrayList<Store> stores = new ArrayList<>();
        for (int i = 0; i < storesJSON.length(); i++) {
            Store store = mapper.readValue(storesJSON.get(i).toString(), Store.class);
            stores.add(store);
        }
        return stores;
    }
}
