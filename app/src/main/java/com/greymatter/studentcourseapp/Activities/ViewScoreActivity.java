package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.EnrolledCoursesAdapter;
import com.greymatter.studentcourseapp.Adapter.ScoreAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.Model.Score;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class ViewScoreActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    ScoreAdapter scoreAdapter;
    Activity activity;
    ImageView imgBack;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_score);

        activity = ViewScoreActivity.this;
        session = new Session(activity);



        recycler_view = findViewById(R.id.recycler_view);
        imgBack = findViewById(R.id.imgBack);


        imgBack.setOnClickListener(v -> onBackPressed());
        recycler_view.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));


        sourcelist();

    }

    private void sourcelist() {
        FirebaseRecyclerOptions<Score> options
                = new FirebaseRecyclerOptions.Builder<Score>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(Constant.SCORES).child(session.getData(Constant.COURSE_ID)).child(session.getData(Constant.TEST_ID)), Score.class)
                .build();
        scoreAdapter = new ScoreAdapter(options, activity);
        recycler_view.setAdapter(scoreAdapter);
        scoreAdapter.startListening();



    }
}