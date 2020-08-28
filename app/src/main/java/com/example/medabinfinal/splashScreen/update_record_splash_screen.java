package com.example.medabinfinal.splashScreen;

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
import com.example.medabinfinal.updateRecord.dasboard.updaterecordDashboard;

public class update_record_splash_screen extends AppCompatActivity {

    Animation topAnim,bottomAnim,sideAnim;
    ImageView logo;
    TextView title,subtitle;
    Button updateRecord;

    private static int SPLASH_SCREEN = 4000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.update_record_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_logo_anim);
        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim_logo);

        //Hooks
        title = findViewById(R.id.text_title_update_record);
        logo = findViewById(R.id.update_record_logo);
        subtitle=findViewById(R.id.update_record_subtitle);
        updateRecord = findViewById(R.id.lets_record_btn);

        title.setAnimation(bottomAnim);
        logo.setAnimation(topAnim);
        subtitle.setAnimation(sideAnim);
        updateRecord.setAnimation(sideAnim);

        updateRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToUpdateRecordDashboard();
            }
        });

    }

    public void sendUserToUpdateRecordDashboard()
    {
        Intent intent = new Intent(this, updaterecordDashboard.class);
        startActivity(intent);
    }


}