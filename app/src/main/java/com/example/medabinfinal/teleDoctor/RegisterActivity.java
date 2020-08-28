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


public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText UserMail,UserPsd,CnfPsd;
    private TextView AlreadyHaveAnAccount;
    private FirebaseAuth mAuth;

    private DatabaseReference RootRef;

    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();

        IntializeFields();
        AlreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendUserToLoginActivity();
            }
        });


        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewAccount();
            }
        });
    }

    private void CreateNewAccount() {

        String email=UserMail.getText().toString();
        String pswd=UserPsd.getText().toString();
        String cnf_pswd=CnfPsd.getText().toString();


        if(pswd.equals(cnf_pswd) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(pswd)){

            loadingBar.setTitle("Creating New account");
            loadingBar.setMessage("Please wait, while we are creating new account for you...");
            loadingBar.setCanceledOnTouchOutside(true);//if the user touch on the scree it will not cancel until the database is created
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,pswd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                String currentUserId = mAuth.getCurrentUser().getUid();
                                //Toast.makeText(RegisterActivity.this,"userid"+currentUserId,Toast.LENGTH_SHORT).show();
                                RootRef.child("Users").child(currentUserId).setValue("");

                                RootRef.child("Users").child(currentUserId).child("device_token")
                                        .setValue(deviceToken);

                                SendUserToMainActivity();
                                Toast.makeText(RegisterActivity.this,"Account Create Succesfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                String message=task.getException().toString();
                                Toast.makeText(RegisterActivity.this,"Error :" + message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        else{
            if(!pswd.equals(cnf_pswd)) {
                Toast.makeText(RegisterActivity.this, "Password not matched", Toast.LENGTH_SHORT).show();
            }
            if(TextUtils.isEmpty(email)) {
                Toast.makeText(RegisterActivity.this, "Error in mail", Toast.LENGTH_SHORT).show();
            }

            if(TextUtils.isEmpty(pswd)) {
                Toast.makeText(RegisterActivity.this, "Error in password", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void SendUserToMainActivity() {

        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);//going from login activity to main activity
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//it is for no exit on pressing backbutton
        //it means that if uder is logged in he cannot go back to log in he have to sign out first
        startActivity (mainIntent);
        finish();

    }

    private void IntializeFields() {
        CreateAccountButton =(Button)findViewById(R.id.register_button);
        UserMail =(EditText)findViewById(R.id.register_email);
        UserPsd =(EditText) findViewById(R.id.register_password);
        CnfPsd =(EditText) findViewById(R.id.cnf_password);
        AlreadyHaveAnAccount =(TextView) findViewById(R.id.already_have_an_account);

        loadingBar = new ProgressDialog(this);

    }

    private void SendUserToLoginActivity() {
        Intent LoginIntent = new Intent(RegisterActivity.this,LoginActivity.class);//going from login activity to main activity
        startActivity (LoginIntent);

    }
}
