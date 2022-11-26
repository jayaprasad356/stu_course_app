package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.studentcourseapp.Activities.AddQuestionActivity;
import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.Model.ViewCourseList;
import com.greymatter.studentcourseapp.R;

import java.util.ArrayList;


public class ViewCourseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<ViewCourseList> viewCourseLists;

    public ViewCourseListAdapter(Activity activity, ArrayList<ViewCourseList> viewCourseLists) {
        this.activity = activity;
        this.viewCourseLists = viewCourseLists;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.course_details, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final ViewCourseList viewCourseList = viewCourseLists.get(position);

        holder.course_name.setText(viewCourseList.getCourse());
        holder.description.setText(viewCourseList.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, AddQuestionActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewCourseLists.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView course_name,description;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            description = itemView.findViewById(R.id.description);
        }
    }
}

