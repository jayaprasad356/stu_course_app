package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.greymatter.studentcourseapp.Activities.AddQuestionActivity;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.Model.ViewCourseList;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;


public class ViewCourseListAdapter extends FirebaseRecyclerAdapter<ViewCourseList, ViewCourseListAdapter.ExploreItemHolder> {
    Activity activity;
    Session session;


    public ViewCourseListAdapter(FirebaseRecyclerOptions<ViewCourseList> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ExploreItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_details, parent, false);
        return new ExploreItemHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExploreItemHolder holder, int position, @NonNull ViewCourseList model) {
        session = new Session(activity);

        holder.course_name.setText(model.getFirstName());
        holder.description.setText(model.getLastName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddQuestionActivity.class);
                activity.startActivity(intent);

            }
        });
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView course_name, description;

        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            description = itemView.findViewById(R.id.description);
        }
    }
}

