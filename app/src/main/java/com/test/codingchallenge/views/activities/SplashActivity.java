package com.test.codingchallenge.views.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.crashlytics.android.Crashlytics;
import com.test.codingchallenge.R;

//import butterknife.BindView;
//import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

/*
* Purpose:
* Intro for user
* Later requirement of initial state fetching of user or app
*/
public class SplashActivity extends AppCompatActivity {

//    @BindView(R.id.iv_splash_logo)
    View viewSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);

//        // calling ButterKnife right after setting content view
//        // binding ButterKnife for views
//        ButterKnife.bind(this);

        // small animation
        startInitialAnimation();
    }

    private void startInitialAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        moveToHomeScreen();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        viewSplash.startAnimation(alphaAnimation);
    }

    private void moveToHomeScreen() {

        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
