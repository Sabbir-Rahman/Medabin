package com.example.medabinfinal.teleDoctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.medabinfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {



    private Toolbar mToolbar;
    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsAccessorAdapter myTabsAccessorAdapter;



    private FirebaseAuth myAuth;
    private DatabaseReference RootRef;
    private String currentUserId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        myAuth = FirebaseAuth.getInstance();


        RootRef = FirebaseDatabase.getInstance().getReference();





        mToolbar =(Toolbar)findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
        TextView toolbar_title=mToolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getSupportActionBar().getTitle());
        getSupportActionBar().setTitle("");

        myViewPager = (ViewPager) findViewById(R.id.main_tabs_pager);
        myTabsAccessorAdapter = new TabsAccessorAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsAccessorAdapter);

        myTabLayout = (TabLayout) findViewById(R.id.main_tab);
        myTabLayout.setupWithViewPager(myViewPager);


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseUser currentUser = myAuth.getCurrentUser();

        //if user is not registered go back to login page
        if(currentUser==null)
        {
            SendUserToLoginActivity();//not logged in go to the logon page
        }
        else
        {
            updateUserStatus("online");

            VerifyUserExistance();
        }


    }
    //if the app is stop
    @Override
    protected void onStop()
    {
        super.onStop();
        FirebaseUser currentUser = myAuth.getCurrentUser();

        if(currentUser != null)
        {
            updateUserStatus("offline");

        }

    }

    //if the app is crashed
    @Override
    protected void onDestroy()
    {

        super.onDestroy();

        FirebaseUser currentUser = myAuth.getCurrentUser();
        if(currentUser != null)
        {
            updateUserStatus("offline");

        }

    }

    private void VerifyUserExistance()
    {
        String currentUserId = myAuth.getCurrentUser().getUid();
        //String currentUserId = myAuth.getCurrentUser().getUid();

        RootRef.child("Users").child(currentUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if((dataSnapshot.child("name").exists()))//if the user already create an account can edit username and photo
                //if(true)//if the user already create an account can edit username and photo
                {
                    Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();
                    //SendUserToSettingActivity();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Dont try to go back", Toast.LENGTH_SHORT).show();

                    SendUserToSettingActivity();


                    //username and pro pic is not set yet he is a new user
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.option_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.menu_logout)
        {
            updateUserStatus("offline");

            myAuth.signOut();
            SendUserToLoginActivity();
        }

        if(item.getItemId() == R.id.menu_find_doctor)
        {
            SendUserToFindDoctorsActivity();
        }

        if(item.getItemId() == R.id.menu_settings)
        {
            //Toast.makeText(MainActivity.this,"Joto jhamela settings",Toast.LENGTH_SHORT).show();

            //SendUserToMainActivity();
            SendUserToSettingActivity();
        }
        if(item.getItemId() == R.id.menu_create_groups)
        {
            RequestNewGroup();
        }
        return true;
    }

    private void RequestNewGroup()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.AlertDialog);
        builder.setTitle("Enter Group name");

        final EditText groupNameField = new EditText(MainActivity.this);
        groupNameField.setHint("e.g medicine specialist");
        builder.setView(groupNameField);

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String groupName= groupNameField.getText().toString();
                if(TextUtils.isEmpty(groupName)){
                    Toast.makeText(MainActivity.this,"Please write Group name", Toast.LENGTH_SHORT);

                }
                else
                {
                    CreateNewGroup(groupName);
                }

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

                dialogInterface.cancel();

            }
        });
        builder.show();

    }

    private void CreateNewGroup(final String groupName)
    {
        RootRef.child("Groups").child(groupName).setValue("")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this,groupName+" group created suceesfully ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void SendUserToLoginActivity() {

        Intent logIntent = new Intent(MainActivity.this,LoginActivity.class);
        logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//it is for no exit on pressing backbutton
        startActivity (logIntent);
        finish();
    }
    private void SendUserToSettingActivity() {

        Intent settingIntent = new Intent(MainActivity.this,SettingsActivity.class);
       //it is for no exit on pressing backbutton without providing username and pro image user cannot access main activity
        startActivity (settingIntent);

    }
    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(MainActivity.this,MainActivity.class);//going from login activity to main activity
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//it is for no exit on pressing backbutton
        //it means that if uder is logged in he cannot go back to log in he have to sign out first
        startActivity (mainIntent);
        finish();

    }
    private void SendUserToFindDoctorsActivity() {

        Intent FindDoctorIntent = new Intent(MainActivity.this,TeleDoctorFindDoctorsActivity.class);
        //it is for no exit on pressing backbutton without providing username and pro image user cannot access main activity
        startActivity (FindDoctorIntent);

    }

    private void updateUserStatus(String state)
    {
        String saveCurrentTime, savecurrentDate;

        Calendar calendar = Calendar.getInstance();
        //saving the date in month/day/year formate
        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, yyyy");
        savecurrentDate = currentdate.format(calendar.getTime());

        //saving the time 12 hours format
        SimpleDateFormat currentTime = new SimpleDateFormat("hh:mm a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        HashMap<String, Object> onlinestateMap = new HashMap<>();
        onlinestateMap.put("time",saveCurrentTime);
        onlinestateMap.put("date",savecurrentDate);
        onlinestateMap.put("state",state);

        currentUserId = myAuth.getCurrentUser().getUid();
        //first going to rootref then going to Users node then going to the currentuserId then creTE A NEW NODE
        //userstte and the pass the hashmap
        RootRef.child("Users").child(currentUserId).child("userState")
                .updateChildren(onlinestateMap);


    }
}
