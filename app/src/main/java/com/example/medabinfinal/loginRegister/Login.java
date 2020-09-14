package com.example.medabinfinal.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medabinfinal.R;
import com.example.medabinfinal.dashboard.UserDashboard;
import com.example.medabinfinal.splashScreen.SplashScreenMedabin;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputLayout mUsername;
    TextInputLayout mPassword;
    Button mButtonLogin;
    TextView mRegister,forgetPass;
    LoginDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        db = new LoginDatabaseHelper(this);
        mUsername = findViewById(R.id.userName);
        mPassword = findViewById(R.id.userPassword);
        mButtonLogin = (Button) findViewById(R.id.userLogin);
        mRegister = (TextView) findViewById(R.id.userRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(Login.this, SignUp.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=mUsername.getEditText().getText().toString().trim();
                String pswd=mPassword.getEditText().getText().toString().trim();
                Boolean res =db.checkUser(user,pswd);
                if(res==true)
                {
                    Toast.makeText(Login.this,"Welcome to medabin",Toast.LENGTH_SHORT).show();
                    Intent registerIntent=new Intent(Login.this, UserDashboard.class);
                    startActivity(registerIntent);
                }
                else
                {
                    Toast.makeText(Login.this,"Login Failed try again",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    public void signup(View view) {
        startActivity(new Intent(this, SignUp.class));
        finish();
    }

    public void sendUserToForgetPassword(View v)
    {
        Intent intent = new Intent(this,ForgetPassword.class);
        startActivity(intent);
        finish();
    }
}
