package com.example.medabinfinal.updateRecord.personalRecord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medabinfinal.R;
import com.example.medabinfinal.updateRecord.personalRecord.Database.DailyTimeModel;
import com.example.medabinfinal.updateRecord.personalRecord.Database.HeightModel;

import java.util.ArrayList;
import java.util.List;

public class DailyRoutineAdapter extends RecyclerView.Adapter<DailyRoutineAdapter.DailyRoutineViewHolder> {

    private Context context;

    List<DailyTimeModel> dailyTimeModels = new ArrayList<>();

    public DailyRoutineAdapter(Context context, List<DailyTimeModel> dailyTimeModels) {
        this.context = context;
        this.dailyTimeModels = dailyTimeModels;
    }

    @NonNull
    @Override
    public DailyRoutineAdapter.DailyRoutineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DailyRoutineAdapter.DailyRoutineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_personal_record_daily_routinr_view,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull DailyRoutineAdapter.DailyRoutineViewHolder holder, int position) {

        holder.dateDailyRoutine.setText(dailyTimeModels.get(position).getDate());
        holder.sleep.setText(String.valueOf(dailyTimeModels.get(position).getSleepTime()));
        holder.read.setText(String.valueOf(dailyTimeModels.get(position).getReadTime()));
        holder.work.setText(String.valueOf(dailyTimeModels.get(position).getWorkingTime()));
        holder.exercise.setText(String.valueOf(dailyTimeModels.get(position).getExerciseTime()));
        holder.others.setText(String.valueOf(dailyTimeModels.get(position).getOthers()));
        holder.unknown.setText(String.valueOf(dailyTimeModels.get(position).getUnknown()));

    }

    @Override
    public int getItemCount() {
        return dailyTimeModels.size();
    }


    public class DailyRoutineViewHolder extends  RecyclerView.ViewHolder{

        public TextView dateDailyRoutine,sleep,read,work,exercise,others,unknown;


        public DailyRoutineViewHolder(@NonNull View itemView) {
            super(itemView);

            dateDailyRoutine = itemView.findViewById(R.id.daily_routine_textDate);
            sleep = itemView.findViewById(R.id.daily_routine_view_sleep_time);
            read = itemView.findViewById(R.id.daily_routine_view_read_time);
            work = itemView.findViewById(R.id.daily_routine_view_working_time);
            exercise = itemView.findViewById(R.id.daily_routine_view_exercise_time);
            others = itemView.findViewById(R.id.daily_routine_view_others_time);
            unknown = itemView.findViewById(R.id.daily_routine_view_unknown_time);


        }


    }

}
