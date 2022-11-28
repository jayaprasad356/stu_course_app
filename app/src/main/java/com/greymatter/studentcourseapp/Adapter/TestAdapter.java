
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
import com.greymatter.studentcourseapp.Activities.AddTestActivity;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;


public class TestAdapter extends FirebaseRecyclerAdapter<Test, TestAdapter.ViewHolder> {

    Activity activity;

    public TestAdapter(FirebaseRecyclerOptions<Test> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.test_views, parent, false);
        return new ViewHolder(listItem);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Test model) {
        holder.title.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.noOfQuestion.setText(model.getNoOfQuestion());
        holder.startDate.setText("Start Date: " + model.getStartdate());
        holder.startTime.setText("Start Time: " + model.getStarttime());
        holder.endDate.setText("End Date: " + model.getEnddate());
        holder.endTime.setText("End Time: " + model.getEndtime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddTestActivity.class);
                activity.startActivity(intent);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, noOfQuestion, startDate, startTime, endDate, endTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
            this.noOfQuestion = itemView.findViewById(R.id.no_of_question);
            this.startDate = itemView.findViewById(R.id.start_date);
            this.startTime = itemView.findViewById(R.id.start_time);
            this.endDate = itemView.findViewById(R.id.end_date);
            this.endTime = itemView.findViewById(R.id.end_time);

        }
    }
}
