package com.example.medabinfinal.teleDoctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medabinfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    private ProgressDialog loadingBar;
    private Button LoginButton,PhoneLoginButton;
    private EditText UserEmail,UserPassword;
    private TextView NeedAnAccountLink, ForgetPasswordLink;
    private DatabaseReference Usersref;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teledoctor_activity_login);

        myAuth = FirebaseAuth.getInstance();
        Usersref = FirebaseDatabase.getInstance().getReference().child("Users");


        //if we dont remove this app may be stuck in future
        InitializeField();

        NeedAnAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToRegisterActivity();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllowUserToLogin();
            }
        });

        PhoneLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent phoneLoginIntent = new Intent(LoginActivity.this,TeleDoctorPhoneLoginActivity.class);
                startActivity(phoneLoginIntent);
            }
        });
    }


    private void AllowUserToLogin() {

        String email=UserEmail.getText().toString();
        String pswd=UserPassword.getText().toString();
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(LoginActivity.this, "Error in mail", Toast.LENGTH_SHORT).show();
        }

        if(TextUtils.isEmpty(pswd)) {
            Toast.makeText(LoginActivity.this, "Error in password", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login......");
            loadingBar.setMessage("Please wait, until log in id finished...");
            loadingBar.setCanceledOnTouchOutside(true);//if the user touch on the scree it will not cancel until the database is created
            loadingBar.show();
            myAuth.signInWithEmailAndPassword(email,pswd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                String currentUserId = myAuth.getCurrentUser().getUid();
                                String deviceToken = FirebaseInstanceId.getInstance().getToken();
                                Usersref.child(currentUserId).child("device_token")
                                        .setValue(deviceToken)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task)
                                            {
                                                if(task.isSuccessful())
                                                {
                                                    SendUserToMainActivity();
                                                    Toast.makeText(LoginActivity.this,"Logon succesful", Toast.LENGTH_SHORT).show();
                                                    loadingBar.dismiss();
                                                }
                                            }
                                        });


                            }
                            else
                            {
                                String message=task.getException().toString();
                                Toast.makeText(LoginActivity.this,"Error in login", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void InitializeField() {

        LoginButton =(Button)findViewById(R.id.login_button);
        PhoneLoginButton =(Button)findViewById(R.id.login_phone_button);
        UserEmail =(EditText) findViewById(R.id.login_email);
        UserPassword =(EditText) findViewById(R.id.login_password);
        NeedAnAccountLink =(TextView) findViewById(R.id.need_a_account_link);
        ForgetPasswordLink =(TextView) findViewById(R.id.forget_password_link);
        loadingBar = new ProgressDialog(this);
    }

    //check user is already exist?
    //@Override
   // protected void onStart() {
        //super.onStart();
        //if (currentUser != null) {
            //SendUserToMainActivity();
       // }
    //}

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);//going from login activity to main activity
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//it is for no exit on pressing backbutton
        //it means that if uder is logged in he cannot go back to log in he have to sign out first
        startActivity (mainIntent);
        finish();

    }

    private void SendUserToRegisterActivity() {
        Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);//going from login activity to main activity
        startActivity (registerIntent);

    }

}
