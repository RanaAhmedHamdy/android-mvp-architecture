package com.example.enozom.storesapp.splash;

import com.example.enozom.storesapp.BasePresenter;
import com.example.enozom.storesapp.BaseView;

/**
 * Created by enozom on 9/12/2017.
 */

public class SplashContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {
        void navigateToStoresActivity();

        void startTimer();
    }
}
