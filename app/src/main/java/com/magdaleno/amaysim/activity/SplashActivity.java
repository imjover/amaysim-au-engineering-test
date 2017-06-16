package com.magdaleno.amaysim.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.magdaleno.amaysim.R;
import com.magdaleno.amaysim.json.Collection;

/**
 * Created by John Oliver "Jover" Magdaleno on 6/16/2017.
 */

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 3000;
    private String mResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_act);

        mResponse = getIntent().getStringExtra(DashboardActivity.RESPONSE);

        openNextActivity();
    }

    private void openNextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                intent.putExtra(DashboardActivity.RESPONSE, mResponse);

                SplashActivity.this.startActivity(intent);
                ((Activity) SplashActivity.this).finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
