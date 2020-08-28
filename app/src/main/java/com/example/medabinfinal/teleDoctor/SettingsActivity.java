package com.example.medabinfinal.teleDoctor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.medabinfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsActivity extends AppCompatActivity
{
    private Button UpdateAccountSettings;
    private EditText userName,userStatus;
    private CircleImageView userProfileImage;
    private String currentUserId;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private Toolbar SettingsToolBar;
    private ProgressDialog loadingBar;
    private String photoUrl;
    private String photoUrl1;


    private static final int GalleryPick =1;
    private StorageReference UserProfileImageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();
        UserProfileImageRef = FirebaseStorage.getInstance().getReference().child("Profile Images");
        InitializeFields();
        userName.setVisibility(View.INVISIBLE);

        UpdateAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateSettings();
            }
        });

        RetrieveUserInfo();

        userProfileImage.setOnClickListener(new View.OnClickListener()//send to gallary
        {
            @Override
            public void onClick(View view) {
                Intent gallaryIntent = new Intent();
                gallaryIntent.setAction(Intent.ACTION_GET_CONTENT);
                gallaryIntent.setType("image/*");
                startActivityForResult(gallaryIntent,GalleryPick);
            }
        });
    }





    private void InitializeFields()
    {
        UpdateAccountSettings =(Button) findViewById(R.id.update_setting);
        userName =(EditText) findViewById(R.id.user_name);
        userStatus =(EditText) findViewById(R.id.profile_status);
        userProfileImage =(CircleImageView) findViewById(R.id.profile_image);
        SettingsToolBar = (Toolbar) findViewById(R.id.menu_settings);
        loadingBar = new ProgressDialog(this);
        setSupportActionBar(SettingsToolBar);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)//get the image
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            Uri ImageUri = data.getData();


            // start picker to get image for cropping and then use the image in cropping activity
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if(resultCode == RESULT_OK) {
                loadingBar.setTitle("Set Profile Image");
                loadingBar.setMessage("Please wait your profile image is updating...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                final Uri resultUri = result.getUri();
                final StorageReference filePath = UserProfileImageRef.child(currentUserId + ".jpg");
                filePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                final String downloadUrl = uri.toString();
                                photoUrl=downloadUrl;
                                photoUrl1=downloadUrl;

                                RootRef.child("Users").child(currentUserId).child("image").setValue(downloadUrl)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(SettingsActivity.this, "Profile image stored to firebase database successfully.", Toast.LENGTH_SHORT).show();
                                                    loadingBar.dismiss();
                                                } else {
                                                    String message = task.getException().getMessage();
                                                    Toast.makeText(SettingsActivity.this, "Error Occurred..." + message, Toast.LENGTH_SHORT).show();
                                                    loadingBar.dismiss();
                                                }
                                            }
                                        });
                            }
                        });
                    }

                });

            }

        }
    }



   private void UpdateSettings(){
       String setUsername = userName.getText().toString();
       String setUserStatus = userStatus.getText().toString();



       if (TextUtils.isEmpty(setUsername)) {
           Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
       }
       if (TextUtils.isEmpty(setUserStatus)) {
           Toast.makeText(this, "Please enter your status", Toast.LENGTH_SHORT).show();
       } else {

           Toast.makeText(this, "Current user"+currentUserId, Toast.LENGTH_SHORT).show();

           HashMap<String, Object> profileMap = new HashMap<>();
           profileMap.put("uid", currentUserId);
           profileMap.put("name", setUsername);
           profileMap.put("status", setUserStatus);
           if(!TextUtils.isEmpty(photoUrl1))
           {
               Toast.makeText(this, "again entry", Toast.LENGTH_SHORT).show();
               profileMap.put("image",photoUrl);
               photoUrl=photoUrl1;

           }
           RootRef.child("Users").child( mAuth.getCurrentUser().getUid()).updateChildren(profileMap)
                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete( Task<Void> task) {
                           if (task.isSuccessful()) {
                               SendUserToMainActivity();
                               Toast.makeText(SettingsActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();


                           } else {
                               String message = task.getException().toString();
                               Toast.makeText(SettingsActivity.this, "Error" + message, Toast.LENGTH_SHORT).show();
                           }

                       }
                   });
           SendUserToMainActivity();

       }
   }




    private void RetrieveUserInfo() {






        RootRef.child("Users").child( mAuth.getCurrentUser().getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("name") && (dataSnapshot.hasChild("image")))) {
                            String retriveUserName = dataSnapshot.child("name").getValue(String.class);
                            String retriveStatus = dataSnapshot.child("status").getValue(String.class);
                            String retriveProfileImage = dataSnapshot.child("image").getValue(String.class);

                            userName.setText(retriveUserName);
                            userStatus.setText(retriveStatus);

                            Picasso.get().load(retriveProfileImage).into(userProfileImage);


                        } else if ((dataSnapshot.exists()) && (dataSnapshot.hasChild("name")&& (dataSnapshot.hasChild("status")))) {

                            String retriveUserName = dataSnapshot.child("name").getValue(String.class);
                            String retriveStatus = dataSnapshot.child("status").getValue(String.class);


                            userName.setText(retriveUserName);
                            userStatus.setText(retriveStatus);

                        } else {
                            userName.setVisibility(View.VISIBLE);
                            Toast.makeText(SettingsActivity.this, "Please set and update your profile information", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
    private void SendUserToMainActivity() {
        Intent mainIntent = new Intent(SettingsActivity.this,MainActivity.class);//going from login activity to main activity
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//it is for no exit on pressing backbutton
        //it means that if uder is logged in he cannot go back to log in he have to sign out first
        startActivity (mainIntent);
        finish();

    }
    private void SendUserToLoginActivity() {

    Intent logIntent = new Intent(SettingsActivity.this,LoginActivity.class);
    logIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//it is for no exit on pressing backbutton
    startActivity (logIntent);
    finish();
}




}
