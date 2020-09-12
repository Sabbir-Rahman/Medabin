package com.example.medabinfinal.dashboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.healthTips.healthTips_splashScreen;
import com.example.medabinfinal.hospitalinfo.hospitalInfoVIew;
import com.example.medabinfinal.mediNote.splashScreenMediNote;
import com.example.medabinfinal.medicinePlanner.medicinePlannerDashboard;
import com.example.medabinfinal.splashScreen.update_record_splash_screen;



import com.google.android.material.navigation.NavigationView;

public class UserDashboard extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //Navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
        super.onBackPressed();}
    }

    public void mediNote(View v){
        Toast.makeText(this, "Going To Create Note", Toast.LENGTH_SHORT).show();
        sendUserTomediNote();
    }

    public void remindMe(View v){
        Toast.makeText(this, "Going To Remind Me", Toast.LENGTH_SHORT).show();
    }

    public void medicineplanning(View v){
        Toast.makeText(this, "Going To Medicine Planning", Toast.LENGTH_SHORT).show();
        sendUserToMediplanner();
    }

    public void teleDoctor(View v){
        Toast.makeText(this, "Going To Tele Doctor", Toast.LENGTH_SHORT).show();

    }

    public void updateRecord(View v){
        Toast.makeText(this, "Going To Update Record", Toast.LENGTH_SHORT).show();
        sendUserToUpdateRecord();
    }

    public void giveFeedback(View v){
        Toast.makeText(this, "Going To Give Feedback", Toast.LENGTH_SHORT).show();
    }

    public void hospitalInfo(View v){
        Toast.makeText(this, "Going To Hospital Info", Toast.LENGTH_SHORT).show();
        sendUserToHospitalInfo();
    }

    public void healthTips(View v){
        Toast.makeText(this, "Going To Health Tips", Toast.LENGTH_SHORT).show();
        sendUserToHealthTips();
    }

    public void sendUserToMediplanner(){
        Intent intent = new Intent(UserDashboard.this, medicinePlannerDashboard.class);
        startActivity(intent);
    }

    public void sendUserTomediNote(){
        Intent intent = new Intent(UserDashboard.this, splashScreenMediNote.class);
        startActivity(intent);
    }

    public void sendUserToUpdateRecord(){
        Intent intent = new Intent(UserDashboard.this, update_record_splash_screen.class);
        startActivity(intent);
    }

    public void sendUserToHealthTips()
    {
        Intent intent = new Intent(this, healthTips_splashScreen.class);
        startActivity(intent);
        finish();
    }

    public void sendUserToHospitalInfo()
    {
        Intent intent = new Intent(this, hospitalInfoVIew.class);
        startActivity(intent);

    }





}
