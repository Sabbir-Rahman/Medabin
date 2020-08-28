package com.example.medabinfinal.mediNote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medabinfinal.R;

public class splashScreenMediNote extends AppCompatActivity {
    Animation topAnim,bottomAnim,sideAnim;
    ImageView image;
    TextView logo,slogan;
    Button makeNote;
    private static int SPLASH_SCREEN = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_medi_note);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_logo_anim);
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim_logo);

        //Hooks
        image = findViewById(R.id.imageViewMediNote);
        logo = findViewById(R.id.mediNoteAppName);
        slogan=findViewById(R.id.medinoteSlogan);
        makeNote = findViewById(R.id.buttonMediNote);

        image.setAnimation(bottomAnim);
        logo.setAnimation(topAnim);
        slogan.setAnimation(sideAnim);
        makeNote.setAnimation(sideAnim);


        makeNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendUserToMediNoteMenu();
            }
        });
    }

    private void SendUserToMediNoteMenu() {
        Intent intent = new Intent(this,mainActivityMediNote.class);
        startActivity(intent);
        finish();
    }
}