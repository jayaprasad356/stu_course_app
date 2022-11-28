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
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class CoursesAdapter extends FirebaseRecyclerAdapter<Course, CoursesAdapter.ViewHolder> {
    Activity activity;
    Session session;

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
        session = new Session(activity);
        holder.title.setText("Course Name : "+model.getName());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TestActivity.class);
                session.setData(Constant.COURSE_ID,model.getCourse_id());
                activity.startActivity(intent);
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.course_name);
            this.description = itemView.findViewById(R.id.description);

        }
    }
}
