package com.example.medabinfinal.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.dashboard.UserDashboard;
import com.example.medabinfinal.walkthrough.OnBoarding;
import com.example.medabinfinal.walkthrough.SliderAdapter;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    //variables
    ImageView backgroundImage;
    TextView slogan;

    //Animations

    Animation sideAnim, bottomAnim;

    SharedPreferences onBoardingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //hooks
        backgroundImage = findViewById(R.id.backgroundImage);
        slogan = findViewById(R.id.slogan);


        //Animations
        //context means in  where we want to hook it
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        //set animations on element
        backgroundImage.setAnimation(sideAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //first entering to this app compiler wil create shared preferences with the name of on boarding screen
                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);



                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);


                if(isFirstTime)
                {
                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(),OnBoarding.class);
                    startActivity(intent);
                    finish();
                }

                else {
                    Intent intent = new Intent(getApplicationContext(), SplashScreenMedabin.class);
                    startActivity(intent);
                    finish();
                }

            }
        },SPLASH_TIMER);


    }
}
