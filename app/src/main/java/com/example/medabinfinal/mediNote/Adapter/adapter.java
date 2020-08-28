package com.example.medabinfinal.mediNote.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.mediNote.Database.NoteModel;
import com.example.medabinfinal.mediNote.medinoteDetails;

import java.util.List;

//          class name          widget           classname.anotherclass
public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<NoteModel> allNotes;

    public adapter(Context context, List<NoteModel> notes){
        this.layoutInflater = LayoutInflater.from(context);
        this.allNotes = notes;
    }


    @NonNull
    @Override
    public adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.notes_recycler_list,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull adapter.ViewHolder holder, int position) {
        //getting data from list
        String title = allNotes.get(position).getTitle();
        String date = allNotes.get(position).getDate();
        String time = allNotes.get(position).getTime();

        Log.d("Title","Title -->"+title+"    Date  ->"+date+"   Time-->  "+time);

        //placing data to recycler view by holder
        holder.notesTitle.setText(title);
        holder.notesDate.setText(date);
        holder.notesTime.setText(time);


    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView notesTitle,notesDate,notesTime;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //hook data to holder
            notesTitle = itemView.findViewById(R.id.title);
            notesDate = itemView.findViewById(R.id.textDate);
            notesTime = itemView.findViewById(R.id.textTime);




            //handeling the recycler view click
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(v.getContext(), "Item Clicked", Toast.LENGTH_SHORT).show();


                  Intent intent = new Intent(v.getContext(), medinoteDetails.class);
                  //we have topass data for this we need to get id
                  intent.putExtra("ID",allNotes.get(getAdapterPosition()).getId());
                  //start the activity from current context
                  v.getContext().startActivity(intent);




              }
          });
        }
    }
}
