package com.example.medabinfinal.teleDoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeleDoctorFindDoctorsActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView FingDoctorsRecyclerList;
    private DatabaseReference UsersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tele_doctor_find_doctors);

        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users");//showing users

        FingDoctorsRecyclerList = (RecyclerView) findViewById(R.id.find_doctors_recycler_list);
        FingDoctorsRecyclerList.setLayoutManager(new LinearLayoutManager(this));

        //mToolbar = (Toolbar) findViewById(R.id.find_doctors_toolbar);
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Find Doctors");

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Contacts> options =
                new FirebaseRecyclerOptions.Builder<Contacts>()
                .setQuery(UsersRef, Contacts.class)//pass the reference of the firebase  //contacts.class is module class
                .build();

        FirebaseRecyclerAdapter<Contacts, TeleDoctorFindDoctorsViewHolder> adapter =
                new FirebaseRecyclerAdapter<Contacts, TeleDoctorFindDoctorsViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull TeleDoctorFindDoctorsViewHolder teleDoctorFindDoctorsViewHolder, final int position, @NonNull Contacts contacts)
                    {
                        teleDoctorFindDoctorsViewHolder.username.setText(contacts.getName());
                        teleDoctorFindDoctorsViewHolder.userStatus.setText(contacts.getStatus());
                        Picasso.get().load(contacts.getImage()).into(teleDoctorFindDoctorsViewHolder.profileImage);

                        teleDoctorFindDoctorsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String visit_user_id = getRef(position).getKey();//this will give details aboutthe profile by clicking username,,status or on image

                                Intent profileIntent = new Intent(TeleDoctorFindDoctorsActivity.this,ProfileActivity.class);
                                profileIntent.putExtra("visit_user_id",visit_user_id);
                                startActivity(profileIntent);


                            }
                        });

                    }

                    @NonNull
                    @Override
                    public TeleDoctorFindDoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_display_layout,parent,false);
                        TeleDoctorFindDoctorsViewHolder viewHolder = new TeleDoctorFindDoctorsViewHolder(view);
                        return viewHolder;
                    }
                };

        FingDoctorsRecyclerList.setAdapter(adapter);
        adapter.startListening();


    }

          public static class TeleDoctorFindDoctorsViewHolder extends RecyclerView.ViewHolder


          {
              TextView username, userStatus;
              CircleImageView profileImage;

              public TeleDoctorFindDoctorsViewHolder(@NonNull View itemView) {
                  super(itemView);

                  username =itemView.findViewById(R.id.user_profile_name);
                  userStatus =itemView.findViewById(R.id.user_status);
                  profileImage =itemView.findViewById(R.id.profile_image);


              }
          }
    }

