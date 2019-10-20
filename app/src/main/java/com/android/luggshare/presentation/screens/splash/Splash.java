package com.android.luggshare.presentation.screens.splash;

import android.os.Bundle;
import android.os.Handler;

import com.android.luggshare.R;
import com.android.luggshare.common.managers.ApplicationStateManager;
import com.android.luggshare.presentation.activities.CoreActivity;
import com.android.luggshare.presentation.screens.dashboard.activities.DashboardActivity;
import com.android.luggshare.presentation.screens.login.activities.LoginActivity;

public class Splash extends CoreActivity {

    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (ApplicationStateManager.getInstance().getIsAuthenticated()) {
                    navigateToIntent(DashboardActivity.class, true);
                } else {
                    navigateToIntent(LoginActivity.class, true);
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
