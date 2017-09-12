package com.example.enozom.storesapp.stores;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.enozom.storesapp.R;
import com.example.enozom.storesapp.data.source.StoresRepository;
import com.example.enozom.storesapp.data.source.remote.StoresRemoteDataSource;

public class StoresActivity extends AppCompatActivity {

    private StoresPresenter storesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StoresActivityFragment storesActivityFragment =
                (StoresActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (storesActivityFragment == null) {
            // Create the fragment
            storesActivityFragment = StoresActivityFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, storesActivityFragment);
            transaction.commit();

            // Create the presenter
            storesPresenter = new StoresPresenter(StoresRepository.getInstance(StoresRemoteDataSource.getInstance()), storesActivityFragment);
        }
    }
}
