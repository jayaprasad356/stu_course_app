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
import com.greymatter.studentcourseapp.Model.Score;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class ScoreAdapter extends FirebaseRecyclerAdapter<Score, ScoreAdapter.ViewHolder> {
    Activity activity;
    Session session;

    public ScoreAdapter(FirebaseRecyclerOptions<Score> options, Activity activity) {
        super(options);
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.score_view, parent, false);
        return new ViewHolder(listItem);

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Score model) {
        session = new Session(activity);
        holder.tvScore.setText("Score : "+model.getScores());
        holder.tvEmail.setText(model.getEmail());

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvScore, tvEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvScore = itemView.findViewById(R.id.tvScore);
            this.tvEmail = itemView.findViewById(R.id.tvEmail);

        }
    }
}
