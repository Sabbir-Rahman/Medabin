package com.example.medabinfinal.teleDoctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medabinfinal.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
{
    private String receiverUserId,Current_state,senderUserId;

    private CircleImageView userProfileImage;
    private TextView userProfileName,userProfileStatus;
    private Button sendMessageReqquestButton,DeclineMessageButton;

    private DatabaseReference UserRef,ChatRequestRef,ContactsRef,NotificationRef;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();

        UserRef = FirebaseDatabase.getInstance().getReference().child("Users");
        ChatRequestRef = FirebaseDatabase.getInstance().getReference().child("Chat Requests");
        ContactsRef = FirebaseDatabase.getInstance().getReference().child("Contacts");
        NotificationRef = FirebaseDatabase.getInstance().getReference().child("Notifications");

        receiverUserId = getIntent().getExtras().get("visit_user_id").toString();
        senderUserId = mAuth.getCurrentUser().getUid();
        //Toast.makeText(ProfileActivity.this,"User Id: " +receiverUserId ,Toast.LENGTH_SHORT).show();

        userProfileImage =(CircleImageView) findViewById(R.id.visit_profile_image);
        userProfileName = (TextView) findViewById(R.id.visit_profile_name);
        userProfileStatus = (TextView) findViewById(R.id.visit_profile_status);
        sendMessageReqquestButton =(Button) findViewById(R.id.send_message_request_button);
        DeclineMessageButton = (Button) findViewById(R.id.decline_message_request_button);
        Current_state = "new";

        RetriveUserInfo();

    }

    private void RetriveUserInfo()
    {
        UserRef.child(receiverUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists() && (dataSnapshot.hasChild("image")))
                {
                    String userImage = dataSnapshot.child("image").getValue().toString();
                    String userName = dataSnapshot.child("name").getValue().toString();
                    String userStatus = dataSnapshot.child("status").getValue().toString();

                    Picasso.get().load(userImage).placeholder(R.drawable.profile_image).into(userProfileImage);
                    userProfileName.setText(userName);
                    userProfileStatus.setText(userStatus);

                    ManageChatRequest();
                }
                else
                {
                    String userName = dataSnapshot.child("name").getValue().toString();
                    String userStatus = dataSnapshot.child("status").getValue().toString();

                    userProfileName.setText(userName);
                    userProfileStatus.setText(userStatus);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void ManageChatRequest()
    {
    ChatRequestRef.child(senderUserId)
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(receiverUserId))
                    {
                        String requestType = dataSnapshot.child(receiverUserId).child("request_type").getValue().toString();
                        if(requestType.equals("sent"))
                        {
                            Current_state = "request_sent";
                            sendMessageReqquestButton.setText("Cancel Chat request");//change the button text
                        }
                        else if(requestType.equals("received"))
                        {
                            Current_state= "request_received";
                            sendMessageReqquestButton.setText("Accept Chat Request");
                            DeclineMessageButton.setVisibility(View.VISIBLE);
                            DeclineMessageButton.setEnabled(true);

                            DeclineMessageButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    CancelChatRequest();
                                }
                            });

                        }
                    }
                    else 
                    {
                        ContactsRef.child(senderUserId)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.hasChild(receiverUserId))
                                        {
                                            Current_state = "connected";
                                            sendMessageReqquestButton.setText("Remove this contact");
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        if(!senderUserId.equals(receiverUserId))//this is other user
        {
            sendMessageReqquestButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    sendMessageReqquestButton.setEnabled(false);
                    if(Current_state.equals("new"))
                    {
                       SendChatRequest();
                    }
                    if(Current_state.equals("request_sent"))
                    {
                        CancelChatRequest();
                    }
                    if(Current_state.equals("request_received"))
                    {
                        AcceptChatRequest();
                    }
                    if(Current_state.equals("connected"))
                    {
                        RemoveSpecificContact();
                    }
                }
            });
        }
        else
        {
            sendMessageReqquestButton.setVisibility(View.INVISIBLE);//cant send request for me
        }
    }

    private void RemoveSpecificContact()
    {
        ContactsRef.child(senderUserId).child(receiverUserId)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            ContactsRef.child(receiverUserId).child(senderUserId)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                            {
                                                sendMessageReqquestButton.setEnabled(true);
                                                Current_state = "new";
                                                sendMessageReqquestButton.setText("Send Message request");//once the sender cancel

                                                DeclineMessageButton.setVisibility(View.INVISIBLE);
                                                DeclineMessageButton.setEnabled(false);
                                            }
                                        }
                                    });

                        }
                    }
                });
    }


    private void SendChatRequest()
    {
        ChatRequestRef.child(senderUserId).child(receiverUserId)//for sender it is sent
                .child("request_type").setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                       if(task.isSuccessful())
                       {
                           ChatRequestRef.child(receiverUserId).child(senderUserId)//for receiver it is received
                                   .child("request_type").setValue("received")
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task)
                                       {
                                           if(task.isSuccessful())
                                           {
                                               HashMap<String, String> chatNotificationMap = new HashMap<>();
                                               chatNotificationMap.put("from",senderUserId);
                                               chatNotificationMap.put("type","request");


                                               NotificationRef.child(receiverUserId).push()
                                                       .setValue(chatNotificationMap)
                                                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                           @Override
                                                           public void onComplete(@NonNull Task<Void> task)
                                                           {
                                                             if(task.isSuccessful())
                                                             {
                                                                 sendMessageReqquestButton.setEnabled(true);
                                                                 Current_state="request_sent";
                                                                 sendMessageReqquestButton.setText("Cancel Chat Request");
                                                             }
                                                           }
                                                       });


                                              //DeclineMessageButton.setVisibility(View.INVISIBLE);
                                              //DeclineMessageButton.setEnabled(false);
                                           }
                                       }
                                   });
                       }
                    }
                });
    }

    private void CancelChatRequest()
    {
        ChatRequestRef.child(senderUserId).child(receiverUserId)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            ChatRequestRef.child(receiverUserId).child(senderUserId)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                sendMessageReqquestButton.setEnabled(true);
                                                Current_state = "new";
                                                sendMessageReqquestButton.setText("Send Message request");//once the sender cancel

                                                DeclineMessageButton.setVisibility(View.INVISIBLE);
                                                DeclineMessageButton.setEnabled(false);
                                            }
                                        }
                                    });

                        }
                    }
                });
    }
    private void AcceptChatRequest() 
    {
        ContactsRef.child(senderUserId).child(receiverUserId)
                .child("Contacts").setValue("Saved")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                          if(task.isSuccessful())
                          {
                              ContactsRef.child(receiverUserId).child(senderUserId)
                                      .child("Contacts").setValue("Saved")
                                      .addOnCompleteListener(new OnCompleteListener<Void>() {
                                          @Override
                                          public void onComplete(@NonNull Task<Void> task)
                                          {
                                              if(task.isSuccessful())
                                              {
                                                  //we have to remove it now from sender find doctor or user list because they are both now in contact
                                                    ChatRequestRef.child(senderUserId).child(receiverUserId)
                                                            .removeValue()
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task)
                                                                {
                                                                    if(task.isSuccessful())
                                                                    {
                                                                        //we have to remove it now from receiver find doctor or user list because they are both now in contact
                                                                        ChatRequestRef.child(receiverUserId).child(senderUserId)
                                                                                .removeValue()
                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task)
                                                                                    {
                                                                                        sendMessageReqquestButton.setEnabled(true);
                                                                                        Current_state = "connected";
                                                                                        sendMessageReqquestButton.setText("Remove this contact");
                                                                                        DeclineMessageButton.setVisibility(View.INVISIBLE);
                                                                                        DeclineMessageButton.setEnabled(false);
                                                                                        

                                                                                    }
                                                                                });
                                                                    }
                                                                    
                                                                }
                                                            });
                                              }
                                          }
                                      });
                          }
                    }
                });
    }
}
