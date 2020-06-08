package com.example.medabinfinal.medicinePlanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medabinfinal.R;

public class medicinePlannerDashboard extends AppCompatActivity {

    //variables
    Animation topAnim,bottomAnim,sideAnim;
    ImageView image;
    TextView logo,slogan;
    Button letsPlan;
    private static int SPLASH_SCREEN = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_planner_dashboard);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_logo_anim);
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim_logo);

        //Hooks
        image = findViewById(R.id.imageViewmediplanner);
        logo = findViewById(R.id.textViewMedipalnner);
        slogan=findViewById(R.id.textViewplanyourmedicine);
        letsPlan = findViewById(R.id.button);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(sideAnim);

        letsPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToMainMenu();
            }
        });


    }

    public void SendUserToMainMenu(){
        Intent intent = new Intent(medicinePlannerDashboard.this,medicinePlannerMenu.class);
        startActivity(intent);
    }
}