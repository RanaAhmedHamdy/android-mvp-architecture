package com.example.enozom.storesapp.splash;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.enozom.storesapp.R;
import com.example.enozom.storesapp.stores.StoresActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A placeholder fragment containing a simple view.
 */
public class SplashActivityFragment extends Fragment implements SplashContract.View{

    private SplashContract.Presenter presenter;
    private final long SPLASH_DELAY = 3000; // msec

    public static SplashActivityFragment newInstance(){return new SplashActivityFragment();}

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View row = inflater.inflate(R.layout.fragment_splash, container, false);
        return row;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void navigateToStoresActivity() {
        if (getActivity() != null) {
            Intent intent = new Intent(getActivity(), StoresActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void startTimer() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                navigateToStoresActivity();
            }
        },SPLASH_DELAY);
    }
}
