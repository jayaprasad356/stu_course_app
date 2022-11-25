
package com.greymatter.studentcourseapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    Test[] tests;
    Activity activity;

    public TestAdapter(Test[] tests, Activity activity) {
        this.tests = tests;
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
        holder.title.setText(tests[position].getTitle());
        holder.description.setText(tests[position].getDescription());
        holder.noOfQuestion.setText(tests[position].getNoOfQuestion());
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
        public TextView title, description, noOfQuestion;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
            this.noOfQuestion = itemView.findViewById(R.id.no_of_question);

        }
    }
}
