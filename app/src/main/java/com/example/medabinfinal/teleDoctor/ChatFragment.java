package com.example.medabinfinal.teleDoctor;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
public class ChatFragment extends Fragment {

    private View PrivateChatView;
    private RecyclerView ChatList;
    private DatabaseReference chatRef,usersRef;
    private FirebaseAuth mAuth;
    private String currentUserId;
    private String retImage = "default_image";


    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PrivateChatView = inflater.inflate(R.layout.fragment_chat, container, false);

        chatRef = FirebaseDatabase.getInstance().getReference().child("Contacts");
        usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
        mAuth = FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        ChatList = (RecyclerView) PrivateChatView.findViewById(R.id.chat_list);

        ChatList.setLayoutManager(new LinearLayoutManager(getContext()));
        return PrivateChatView;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Contacts> options =
                new FirebaseRecyclerOptions.Builder<Contacts>()
                .setQuery(chatRef,Contacts.class)
                .build();

        FirebaseRecyclerAdapter<Contacts,ChatsViewHolder> adapter =
                new FirebaseRecyclerAdapter<Contacts, ChatsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull final ChatsViewHolder chatsViewHolder, int i, @NonNull Contacts contacts)
                    {
                        //get each id line by line
                        final String usersId = getRef(i).getKey();

                        usersRef.child(usersId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                               if(dataSnapshot.exists())//validation
                               {
                                   if(dataSnapshot.hasChild("image"))
                                   {
                                        retImage = dataSnapshot.child("image").getValue().toString();
                                       Picasso.get().load(retImage).into(chatsViewHolder.profileImage);
                                   }

                                   final String retName = dataSnapshot.child("name").getValue().toString();
                                   final String retStatus = dataSnapshot.child("status").getValue().toString();

                                   chatsViewHolder.userName.setText(retName);


                                   //retrieving the state online or offline
                                   if(dataSnapshot.child("userState").hasChild("state"))
                                   {
                                       //retreiving the state,date and time
                                       String state = dataSnapshot.child("userState").child("state").getValue().toString();
                                       String date = dataSnapshot.child("userState").child("date").getValue().toString();
                                       String time = dataSnapshot.child("userState").child("time").getValue().toString();

                                       if(state.equals("online"))
                                       {
                                           chatsViewHolder.userStatus.setText("online");

                                       }
                                       else if(state.equals("offline"))
                                       {
                                           chatsViewHolder.userStatus.setText("Last Seen: "+ date + " " +time);

                                       }
                                   }
                                   else
                                   {
                                       chatsViewHolder.userStatus.setText("offline");

                                   }



                                   chatsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view)
                                       {
                                           Intent chatIntent = new Intent(getContext(),ChatActivity.class);

                                           //passing id and name to the chat activity
                                           chatIntent.putExtra("visit_user_id",usersId);
                                           chatIntent.putExtra("visit_user_name",retName);
                                           chatIntent.putExtra("visit_image",retImage);

                                           startActivity(chatIntent);
                                       }
                                   });
                               }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                    @NonNull
                    @Override
                    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_display_layout,parent,false);

                        return new ChatsViewHolder(view);
                    }
                };

        ChatList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class ChatsViewHolder extends  RecyclerView.ViewHolder
    {

        TextView userName,userStatus;
        CircleImageView profileImage;



        public ChatsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            userName = itemView.findViewById(R.id.user_profile_name);
            userStatus = itemView.findViewById(R.id.user_status);
            profileImage = itemView.findViewById(R.id.profile_image);

        }
    }
}
