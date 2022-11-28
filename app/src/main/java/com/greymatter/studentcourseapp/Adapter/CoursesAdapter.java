package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.greymatter.studentcourseapp.Activities.TestActivity;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

public class CoursesAdapter extends FirebaseRecyclerAdapter<Course, CoursesAdapter.ViewHolder> {
    Activity activity;

    public CoursesAdapter(FirebaseRecyclerOptions<Course> options, Activity activity) {
        super(options);
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.course_details, parent, false);
        return new ViewHolder(listItem);

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Course model) {
        holder.title.setText(model.getFirstName());
        holder.description.setText(model.getLastName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TestActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, startDate, startTime, endDate, endTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.course_name);
            this.description = itemView.findViewById(R.id.description);
            this.startDate = itemView.findViewById(R.id.start_date);
            this.startTime = itemView.findViewById(R.id.start_time);
            this.endDate = itemView.findViewById(R.id.end_date);
            this.endTime = itemView.findViewById(R.id.end_time);
        }
    }
}
