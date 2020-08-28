package com.example.medabinfinal.teleDoctor;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment
{
    private View RequestFragmentView;
    private RecyclerView myRequestList;
    private DatabaseReference ChatRequestsRef,UsersRef,ContactsRef;
    private FirebaseAuth mAuth;
    private String currentUserId;


    public RequestFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RequestFragmentView = inflater.inflate(R.layout.fragment_request, container, false);


        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");

        ContactsRef = FirebaseDatabase.getInstance().getReference().child("Contacts");
        ChatRequestsRef = FirebaseDatabase.getInstance().getReference().child("Chat Requests");
        myRequestList = (RecyclerView) RequestFragmentView.findViewById(R.id.chatRequestList);
        myRequestList.setLayoutManager(new LinearLayoutManager(getContext()));


        return RequestFragmentView;


    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Contacts> options = new FirebaseRecyclerOptions.Builder<Contacts>()
                .setQuery(ChatRequestsRef.child(currentUserId),Contacts.class)
                .build();

        FirebaseRecyclerAdapter<Contacts,RequestsViewHolder> adapter =
                new FirebaseRecyclerAdapter<Contacts, RequestsViewHolder>(options ) {
                    @Override
                    protected void onBindViewHolder(@NonNull final RequestsViewHolder requestsViewHolder, int i, @NonNull Contacts contacts)
                    {
                        requestsViewHolder.itemView.findViewById(R.id.requestAcceptButton).setVisibility(View.VISIBLE);
                        requestsViewHolder.itemView.findViewById(R.id.requestCancelButton).setVisibility(View.VISIBLE);

                        final String list_user_id = getRef(i).getKey();
                        //get the position key and they we will check inside from that node in the firebase

                        final DatabaseReference getTypeRef = getRef(i).child("request_type").getRef();

                        getTypeRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                               //get the value
                               if(dataSnapshot.exists())
                               {
                                   String type = dataSnapshot.getValue().toString();

                                   if(type.equals("received"))
                                   {
                                       //repeat the process for all user
                                       UsersRef.child(list_user_id).addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                           {
                                                if(dataSnapshot.hasChild("image"))
                                                {
                                                    final String RequestuserImage = dataSnapshot.child("image").getValue().toString();

                                                    Picasso.get().load(RequestuserImage).placeholder(R.drawable.profile_image).into(requestsViewHolder.profileImage);
                                                }

                                               final String RequestprofileName = dataSnapshot.child("name").getValue().toString();
                                               final String RequestprofileStatus = dataSnapshot.child("status").getValue().toString();

                                               requestsViewHolder.userName.setText(RequestprofileName);
                                               requestsViewHolder.userStatus.setText(RequestprofileStatus);

                                                requestsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {

                                                        CharSequence options[] = new CharSequence[]
                                                                {
                                                                        "Accept",
                                                                        "Cancel"
                                                                };

                                                         AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                        builder.setTitle(RequestprofileName +"Chat Request");

                                                        builder.setItems(options, new DialogInterface.OnClickListener(){
                                                            @Override
                                                            public void onClick(DialogInterface dialogInterface, int i)
                                                            {

                                                                if(i==0)
                                                                {
                                                                    //current user id = senderuserid and list_user_id = receiver user id
                                                                    ContactsRef.child(currentUserId).child(list_user_id).child("Contact")
                                                                            .setValue("Saved").addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task)
                                                                        {
                                                                            if(task.isSuccessful())
                                                                            {
                                                                                ContactsRef.child(list_user_id).child(currentUserId).child("Contact")
                                                                                        .setValue("Saved").addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                    @Override
                                                                                    public void onComplete(@NonNull Task<Void> task)
                                                                                    {
                                                                                        if(task.isSuccessful())
                                                                                        {
                                                                                            ChatRequestsRef.child(currentUserId).child(list_user_id)
                                                                                                    .removeValue()
                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                        @Override
                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                            if(task.isSuccessful())
                                                                                                            {
                                                                                                                ChatRequestsRef.child(list_user_id).child(currentUserId)
                                                                                                                        .removeValue()
                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                            @Override
                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                if(task.isSuccessful())
                                                                                                                                {
                                                                                                                                    Toast.makeText(getContext(), "New contact added", Toast.LENGTH_SHORT).show();

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
                                                                    });
                                                                }
                                                                if(i==1)
                                                                {
                                                                    ChatRequestsRef.child(currentUserId).child(list_user_id)
                                                                            .removeValue()
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if(task.isSuccessful())
                                                                                    {
                                                                                        ChatRequestsRef.child(list_user_id).child(currentUserId)
                                                                                                .removeValue()
                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                    @Override
                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                        if(task.isSuccessful())
                                                                                                        {
                                                                                                            Toast.makeText(getContext(), "Contacts removed", Toast.LENGTH_SHORT).show();

                                                                                                        }
                                                                                                    }
                                                                                                });
                                                                                    }
                                                                                }
                                                                            });
                                                                }


                                                            }
                                                        });
                                                        builder.show();
                                                    }
                                                });
                                           }


                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });
                                   }

                                   else if(type.equals("sent"))
                                   {
                                       Button request_sent_btn = requestsViewHolder.itemView.findViewById(R.id.requestAcceptButton);
                                       request_sent_btn.setText("Request Sent");
                                       request_sent_btn.setBackgroundResource(R.drawable.teledoctor_button3);
                                       request_sent_btn.setTextColor(Color.WHITE);

                                       requestsViewHolder.itemView.findViewById(R.id.requestCancelButton).setVisibility(View.INVISIBLE);

                                       UsersRef.child(list_user_id).addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                           {
                                               if(dataSnapshot.hasChild("image"))
                                               {
                                                   final String RequestuserImage = dataSnapshot.child("image").getValue().toString();

                                                   Picasso.get().load(RequestuserImage).placeholder(R.drawable.profile_image).into(requestsViewHolder.profileImage);
                                               }

                                               final String RequestprofileName = dataSnapshot.child("name").getValue().toString();
                                               final String RequestprofileStatus = dataSnapshot.child("status").getValue().toString();

                                               requestsViewHolder.userName.setText(RequestprofileName);
                                               requestsViewHolder.userStatus.setText(RequestprofileStatus+"\nYou have sent request");

                                               requestsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {

                                                       CharSequence options[] = new CharSequence[]
                                                               {
                                                                       "Cancel Chat Request"
                                                               };

                                                       AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                       builder.setTitle("Already sent request");

                                                       builder.setItems(options, new DialogInterface.OnClickListener(){
                                                           @Override
                                                           public void onClick(DialogInterface dialogInterface, int i)
                                                           {


                                                               if(i==0)
                                                               {
                                                                   ChatRequestsRef.child(currentUserId).child(list_user_id)
                                                                           .removeValue()
                                                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                               @Override
                                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                                   if(task.isSuccessful())
                                                                                   {
                                                                                       ChatRequestsRef.child(list_user_id).child(currentUserId)
                                                                                               .removeValue()
                                                                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                   @Override
                                                                                                   public void onComplete(@NonNull Task<Void> task) {
                                                                                                       if(task.isSuccessful())
                                                                                                       {
                                                                                                           Toast.makeText(getContext(), "You have cancelled the chat request", Toast.LENGTH_SHORT).show();

                                                                                                       }
                                                                                                   }
                                                                                               });
                                                                                   }
                                                                               }
                                                                           });
                                                               }


                                                           }
                                                       });
                                                       builder.show();
                                                   }
                                               });
                                           }


                                           @Override
                                           public void onCancelled(@NonNull DatabaseError databaseError) {

                                           }
                                       });

                                   }

                               }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    @NonNull
                    @Override
                    public RequestsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        //pass the view

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_display_layout,parent,false);
                        RequestsViewHolder holder = new RequestsViewHolder(view);
                        return holder;
                    }
                };
            myRequestList.setAdapter(adapter);
            adapter.startListening();
    }

    //access user display layout
    public static class RequestsViewHolder extends RecyclerView.ViewHolder
    {
        TextView userName,userStatus;
        CircleImageView profileImage;
        Button AcceptButton,CancelButton;

        public RequestsViewHolder(@NonNull View itemView)
        {

            super(itemView);

            userName = itemView.findViewById(R.id.user_profile_name);
            userStatus = itemView.findViewById(R.id.user_status);
            profileImage = itemView.findViewById(R.id.profile_image);
            AcceptButton = itemView.findViewById(R.id.requestAcceptButton);
            CancelButton = itemView.findViewById(R.id.requestCancelButton);


        }


    }
}
