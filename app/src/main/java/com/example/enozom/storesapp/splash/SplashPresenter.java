package com.example.enozom.storesapp.splash;


import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by enozom on 9/12/2017.
 */

public class SplashPresenter implements SplashContract.Presenter{

    private final SplashContract.View splashView;
    private final String TAG = "SplashPresenter";

    public SplashPresenter(@NonNull SplashContract.View splashView) {
        this.splashView = splashView;
        splashView.setPresenter(this);
        Log.d(TAG, "init");
    }

    @Override
    public void start() {
        splashView.startTimer();
    }
}
