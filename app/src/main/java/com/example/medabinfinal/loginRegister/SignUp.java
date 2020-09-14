package com.example.medabinfinal.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.dashboard.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    TextInputLayout mFullname;
    TextInputLayout mUsername;
    TextInputLayout mNationality;
    TextInputLayout mAge;
    TextInputLayout mBlood_group;
    TextInputLayout mOccupation;
    TextInputLayout mGender;
    TextInputLayout mPhone;
    TextInputLayout mEmail;
    TextInputLayout mAddress;
    TextInputLayout mEmergencyContact;
    TextInputLayout mPassword;
    TextInputLayout mCnfPassword;
    TextInputLayout petName;
    Button mSignup;
    TextView mDuplicate;

    LoginDatabaseHelper db;
    private LoginDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db=new LoginDatabaseHelper(this);
        dbHelper = new LoginDatabaseHelper(this);

        mFullname=findViewById(R.id.full_name);
        mUsername=findViewById(R.id.sign_user_name);
        mNationality=findViewById(R.id.nationality);
        mAge=findViewById(R.id.age);
        mBlood_group=findViewById(R.id.blood_group);
        mOccupation=findViewById(R.id.occupation);
        mGender=findViewById(R.id.gender);
        mPhone=findViewById(R.id.textTime);
        mEmail=findViewById(R.id.textTime);
        mAddress=findViewById(R.id.address);
        mEmergencyContact=findViewById(R.id.emergency_contact);
        mPassword=findViewById(R.id.Password);
        mCnfPassword=findViewById(R.id.cnf_password);
        mSignup=findViewById(R.id.signButton);
        mDuplicate=findViewById(R.id.duplicate);
        petName = findViewById(R.id.pet_name);




        mDuplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase database = dbHelper.getReadableDatabase();
                String username = mUsername.getEditText().getText().toString();
                TextView txt = findViewById(R.id.duplicate);
                Button btn=findViewById(R.id.signButton);
                if(dbHelper.duplicateUser(username,database)) { //this line crashes the app
                    Toast.makeText(SignUp.this, "this username is unavailable", Toast.LENGTH_SHORT).show();
                    txt.setText("The username is unavailable please change it and again recheck by clicling on me");
                    txt.setTextColor(Color.RED);
                    btn.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(SignUp.this, "Username is available", Toast.LENGTH_SHORT).show();
                    txt.setText("Congratulations!! The user name is available please continue");
                    txt.setTextColor(Color.GREEN);
                    btn.setVisibility(View.VISIBLE);
                }
            }
        });

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mFullname.getEditText().getText().toString().trim();
                String user=mUsername.getEditText().getText().toString().trim();
                String nationality=mNationality.getEditText().getText().toString().trim();
                String age=mAge.getEditText().getText().toString().trim();
                String blood_group=mBlood_group.getEditText().getText().toString().trim();
                String occupation=mOccupation.getEditText().getText().toString().trim();
                String gender=mGender.getEditText().getText().toString().trim();
                String phone=mPhone.getEditText().getText().toString().trim();
                String email=mEmail.getEditText().getText().toString().trim();
                String address=mAddress.getEditText().getText().toString().trim();
                String emergency_contact=mEmergencyContact.getEditText().getText().toString().trim();
                String pswd=mPassword.getEditText().getText().toString().trim();
                String cnf_pswd=mCnfPassword.getEditText().getText().toString().trim();
                String pet = petName.getEditText().getText().toString();

                if(pswd.equals(cnf_pswd)){
                    long value=db.addUser(name,user,nationality,age,blood_group,occupation,gender,phone,email,address,emergency_contact,pswd,pet);
                    if(value>0){
                        Toast.makeText(SignUp.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin=new Intent(SignUp.this,UserDashboard.class);
                        startActivity(moveToLogin);}
                    else
                    {
                        Toast.makeText(SignUp.this,"Unknown Error Try again",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignUp.this,"Passward not matched",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void dashboard(View view) {
        startActivity(new Intent(this, UserDashboard.class));
        finish();

    }
}
