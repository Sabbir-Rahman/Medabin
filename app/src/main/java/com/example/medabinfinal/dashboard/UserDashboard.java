package com.example.medabinfinal.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.giveFeedback.doctor_feedback_add;
import com.example.medabinfinal.giveFeedback.doctor_feedback_view;
import com.example.medabinfinal.giveFeedback.hospital_feedback_view;
import com.example.medabinfinal.giveFeedback.medicine_feedback_view;
import com.example.medabinfinal.giveFeedback.pharmacy_feedback_view;
import com.example.medabinfinal.healthTips.health_tips_show;
import com.example.medabinfinal.mediNote.mainActivityMediNote;
import com.example.medabinfinal.medicinePlanner.plannerActivity;
import com.example.medabinfinal.medicineReminder.AlarmService;
import com.example.medabinfinal.medicineReminder.medicineReminderMenu;
import com.example.medabinfinal.medicineReminder.medicineReminderView;
import com.example.medabinfinal.stayFit.stayFitMenu;
import com.example.medabinfinal.giveFeedback.giveFeedbackMenu;
import com.example.medabinfinal.healthTips.healthTips_splashScreen;
import com.example.medabinfinal.hospitalinfo.hospitalInfoVIew;
import com.example.medabinfinal.mediNote.splashScreenMediNote;
import com.example.medabinfinal.medicinePlanner.medicinePlannerDashboard;
import com.example.medabinfinal.splashScreen.update_record_splash_screen;


import com.example.medabinfinal.updateRecord.medicalReport.view_report;
import com.example.medabinfinal.updateRecord.medicineRecord.update_record_medicine_view;
import com.example.medabinfinal.updateRecord.personalRecord.ShowRecords.personal_record_showRecords_menu;
import com.google.android.material.navigation.NavigationView;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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

        navigationView.setNavigationItemSelectedListener(this);

        //permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.FOREGROUND_SERVICE}, PackageManager.PERMISSION_GRANTED);

        //call service intent
        final Intent intent = new Intent(this, AlarmService.class);
        ServiceCaller(intent);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
        super.onBackPressed();}
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void mediNote(View v){
        Toast.makeText(this, "Going To Create Note", Toast.LENGTH_SHORT).show();
        sendUserTomediNote();
    }

    public void remindMe(View v){
        Toast.makeText(this, "Going To Remind Me", Toast.LENGTH_SHORT).show();
        sendUserToMedicineReminder();
    }

    public void medicineplanning(View v){
        Toast.makeText(this, "Going To Medicine Planning", Toast.LENGTH_SHORT).show();
        sendUserToMediplanner();
    }

    public void dietChart(View v){

        Toast.makeText(this, "Going To Tele Doctor", Toast.LENGTH_SHORT).show();
        sendUserToDietChart();

    }

    public void updateRecord(View v){
        Toast.makeText(this, "Going To Update Record", Toast.LENGTH_SHORT).show();
        sendUserToUpdateRecord();
    }

    public void giveFeedback(View v){
        Toast.makeText(this, "Going To Give Feedback", Toast.LENGTH_SHORT).show();
        sendUserToFeedback();
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

    }

    public void sendUserToHospitalInfo()
    {
        Intent intent = new Intent(this, hospitalInfoVIew.class);
        startActivity(intent);

    }

    public void sendUserToFeedback()
    {
        Intent intent = new Intent(this, giveFeedbackMenu.class);
        startActivity(intent);

    }

    public void sendUserToDietChart()
    {
        Intent intent = new Intent(this, stayFitMenu.class);
        startActivity(intent);

    }

    public void sendUserToMedicineReminder()
    {
        Intent intent = new Intent(this, medicineReminderMenu.class);
        startActivity(intent);
    }

    private void ServiceCaller(Intent intent){

        stopService(intent);

        startService(intent);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_medicine_rank:
                Intent intent = new Intent(this, medicine_feedback_view.class);
                startActivity(intent);
                break;
            case R.id.nav_dr_rank:
                Intent intent1 = new Intent(this, doctor_feedback_view.class);
                startActivity(intent1);
                break;
            case R.id.nav_hospital_rank:
                Intent intent2 = new Intent(this, hospital_feedback_view.class);
                startActivity(intent2);
                break;
            case R.id.nav_pharma_rank:
                Intent intent3 = new Intent(this, pharmacy_feedback_view.class);
                startActivity(intent3);
                break;
            case R.id.nav_note:
                Intent intent4 = new Intent(this, mainActivityMediNote.class);
                startActivity(intent4);
                break;
            case R.id.nav_reminder:
                Intent intent5 = new Intent(this, medicineReminderView.class);
                startActivity(intent5);
                break;

            case R.id.nav_personal_record:
                Intent intent6 = new Intent(this, personal_record_showRecords_menu.class);
                startActivity(intent6);
                break;
            case R.id.nav_show_plan:
                Intent intent7 = new Intent(this, plannerActivity.class);
                startActivity(intent7);
                break;
            case R.id.nav_medicine_record:
                Intent intent8 = new Intent(this, update_record_medicine_view.class);
                startActivity(intent8);
                break;
            case R.id.nav_report:
                Intent intent9 = new Intent(this, view_report.class);
                startActivity(intent9);
                break;

            case R.id.nav_hospital_info:
                Intent intent10 = new Intent(this, hospitalInfoVIew.class);
                startActivity(intent10);
                break;

            case R.id.nav_tips:
                Intent intent11 = new Intent(this, health_tips_show.class);
                startActivity(intent11);
                break;
        }

        return true;
    }
}
