package com.example.medabinfinal.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.dashboard.UserDashboard;
import com.google.android.material.textfield.TextInputLayout;

public class ForgetPassword extends AppCompatActivity {

    TextInputLayout petName,userName;
    Button login;
    TextView goBackMainLogin,register;
    LoginDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        db = new LoginDatabaseHelper(this);

        petName = findViewById(R.id.forgetPasswordPetName);
        login = findViewById(R.id.userLoginForgetPassword);
        goBackMainLogin = findViewById(R.id.forgetPassGoBack);
        register = findViewById(R.id.userRegisterForgetPassword);
        userName = findViewById(R.id.userNameForgetPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getEditText().getText().toString().trim();
                String pet = petName.getEditText().getText().toString();

                Toast.makeText(ForgetPassword.this, "name"+name+"  pet name"+petName, Toast.LENGTH_SHORT).show();

                Boolean res =db.checkPet(name,pet);
                if(res==true)
                {
                    Toast.makeText(ForgetPassword.this,"Welcome to medabin",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ForgetPassword.this,UserDashboard.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ForgetPassword.this,"Login Failed try again",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void sendUserToMainLogin(View v)
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }

    public void sendUserToSignUp(View v)
    {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
        finish();

    }


}