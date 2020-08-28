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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class TeleDoctorPhoneLoginActivity extends AppCompatActivity
{

    private Button SendVeficationCodeButton, VerifyButton;
    private TextView countryCodeWarning;
    private EditText InputPhoneNumber, InputVerificationCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_doctor_phone_login);

        mAuth = FirebaseAuth.getInstance();
        countryCodeWarning = (TextView) findViewById(R.id.country_code);
        SendVeficationCodeButton =(Button) findViewById(R.id.send_verification_code);
        VerifyButton =(Button) findViewById(R.id.verify_phone_login_button);
        InputPhoneNumber =(EditText) findViewById(R.id.phoneNumberLogin);
        InputVerificationCode =(EditText) findViewById(R.id.verification_code_input);
        loadingBar = new ProgressDialog(this);

        SendVeficationCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                //take phone number

                String phoneNumber = InputPhoneNumber.getText().toString();
                if(TextUtils.isEmpty(phoneNumber))
                {
                    Toast.makeText(TeleDoctorPhoneLoginActivity.this,"Please input phone number", Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingBar.setTitle("Phone Verification");
                    loadingBar.setMessage("Please wait,while we are checking your phone number");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,        // Phone number to verify
                            60,                 // Timeout duration
                            TimeUnit.SECONDS,   // Unit of timeout
                            TeleDoctorPhoneLoginActivity.this,               // Activity (for callback binding)
                            callbacks);        // OnVerificationStateChangedCallbacks
                }
            }
        });


        VerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SendVeficationCodeButton.setVisibility(View.INVISIBLE);
                InputPhoneNumber.setVisibility(View.INVISIBLE);
                countryCodeWarning.setVisibility(View.INVISIBLE);

                String verificationCode = InputVerificationCode.getText().toString();

                if(TextUtils.isEmpty(verificationCode))
                {
                    Toast.makeText(TeleDoctorPhoneLoginActivity.this,"Please input your code first", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    loadingBar.setTitle("Code Verification");
                    loadingBar.setMessage("Please wait,while we are verifying verification code");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                loadingBar.dismiss();
                Toast.makeText(TeleDoctorPhoneLoginActivity.this,"Invalid phone number or wrong verification code..", Toast.LENGTH_SHORT).show();

                SendVeficationCodeButton.setVisibility(View.VISIBLE);
                InputPhoneNumber.setVisibility(View.VISIBLE);
                countryCodeWarning.setVisibility(View.VISIBLE);

                VerifyButton.setVisibility(View.INVISIBLE);
                InputVerificationCode.setVisibility(View.INVISIBLE);
            }

            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {


                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                loadingBar.dismiss();

                Toast.makeText(TeleDoctorPhoneLoginActivity.this,"Verification code sent please check", Toast.LENGTH_SHORT).show();

                SendVeficationCodeButton.setVisibility(View.INVISIBLE);
                InputPhoneNumber.setVisibility(View.INVISIBLE);
                countryCodeWarning.setVisibility(View.INVISIBLE);

                VerifyButton.setVisibility(View.VISIBLE);
                InputVerificationCode.setVisibility(View.VISIBLE);

            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            loadingBar.dismiss();
                            Toast.makeText(TeleDoctorPhoneLoginActivity.this,"Welcome you logged in succesfully", Toast.LENGTH_SHORT).show();
                            SendUserToMainActivity();
                        }

                        else
                        {
                            String message = task.getException().toString();
                            Toast.makeText(TeleDoctorPhoneLoginActivity.this,"Error : "+ message, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    private void SendUserToMainActivity()
    {
        Intent mainIntent = new Intent(TeleDoctorPhoneLoginActivity.this,MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
