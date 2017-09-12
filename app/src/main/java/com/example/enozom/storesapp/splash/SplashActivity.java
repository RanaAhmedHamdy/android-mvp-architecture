package com.example.enozom.storesapp.splash;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.enozom.storesapp.R;

public class SplashActivity extends AppCompatActivity {

    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashActivityFragment splashActivityFragment =
                (SplashActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        if (splashActivityFragment == null) {
            // Create the fragment
            splashActivityFragment = SplashActivityFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment, splashActivityFragment);
            transaction.commit();

            // Create the presenter
            splashPresenter = new SplashPresenter(splashActivityFragment);
        }
        splashPresenter = new SplashPresenter(splashActivityFragment);
    }

}
