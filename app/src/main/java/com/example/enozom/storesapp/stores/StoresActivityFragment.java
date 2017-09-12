package com.example.enozom.storesapp.stores;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.enozom.storesapp.R;
import com.example.enozom.storesapp.data.Store;
import com.example.enozom.storesapp.utils.SimpleDividerItemDecorationUtils;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class StoresActivityFragment extends Fragment implements StoresContract.View{

    private final String TAG = "StoresActivityFragment";
    private RecyclerView recyclerView;
    private StoresAdapter adapter;
    private StoresContract.Presenter presenter;
    private EditText searchBar;

    public StoresActivityFragment() {
    }

    public static StoresActivityFragment newInstance() {
        return new StoresActivityFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_stores, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.stores_recycler_view);
        searchBar = (EditText) getActivity().findViewById(R.id.searchEditText);

        getData();

        searchBar.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                //Log.d(TAG, searchBar.getText().toString());
                presenter.filterStoresByName(searchBar.getText().toString());
            }
        });

        return root;
    }

    @Override
    public void setStores(ArrayList<Store> stores) {
        SimpleDividerItemDecorationUtils dividerItemDecoration = new SimpleDividerItemDecorationUtils(recyclerView.getContext());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StoresAdapter(stores, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateStores(ArrayList<Store> filteredStores) {
        adapter = new StoresAdapter(filteredStores, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setPresenter(StoresContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void getData(){
        presenter.getStores();
    }
}
