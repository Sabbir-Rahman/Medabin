package com.example.medabinfinal.healthTips;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medabinfinal.R;

public class healthTips_splashScreen extends AppCompatActivity {

    //variables
    Animation topAnim,bottomAnim,sideAnim;
    ImageView image;
    TextView logo,slogan;
    Button showTips;
    private static int SPLASH_SCREEN = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_logo_anim);
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim_logo);

        //Hooks
        image = findViewById(R.id.image_care);
        logo = findViewById(R.id.textViewLogo);
        slogan=findViewById(R.id.textViewSlogan);
        showTips = findViewById(R.id.button_tips);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(sideAnim);

        showTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToHealthTips();
            }
        });
    }

    private void SendUserToHealthTips() {
        Intent intent = new Intent(this,health_tips_show.class);
        startActivity(intent);
        finish();

    }
}