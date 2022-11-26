package com.greymatter.studentcourseapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Activities.TestActivity;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.ViewHolder> {
    Course[] courses;
    Activity activity;

    public CoursesAdapter(Course[] courses, Activity activity) {
        this.courses = courses;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(courses[position].getCourse());
        holder.description.setText(courses[position].getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TestActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.length;
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
