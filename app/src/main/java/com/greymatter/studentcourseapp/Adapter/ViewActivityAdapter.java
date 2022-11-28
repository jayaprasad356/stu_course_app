package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.studentcourseapp.Activities.AddTestActivity;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;

class ViewActivityAdapterextends extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    Test[] tests;
    Activity activity;

    public void TestAdapter(Test[] tests, Activity activity) {
        this.tests = tests;
        this.activity = activity;

    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.listdesign, parent, false);
        return new TestAdapter.ViewHolder(listItem);

    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {
        holder.title.setText(tests[position].getName());
        holder.description.setText(tests[position].getDescription());
        holder.startDate.setText("Date: " + tests[position].getStartdate());
        holder.startTime.setText("Time: " + tests[position].getStarttime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddTestActivity.class);
                activity.startActivity(intent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return tests.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, noOfQuestion, startDate, startTime, endDate, endTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.titlee);
            this.description = itemView.findViewById(R.id.discriptionn);
            this.startDate = itemView.findViewById(R.id.datee);
            this.startTime = itemView.findViewById(R.id.timee);


        }
    }
}