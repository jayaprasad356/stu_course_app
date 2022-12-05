package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Adapter.TestAdapter;
import com.greymatter.studentcourseapp.Adapter.upcomingExamAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.ProgressDisplay;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.ArrayList;
import java.util.Arrays;

public class UpcomingExamActivity extends AppCompatActivity {
    TextView tvTitle;
    Activity activity;

    RecyclerView recyclerView;
    ImageView imgBack;
    upcomingExamAdapter upcomingExamAdapter;
    Session session;
    ProgressDisplay progressDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_exam);
        activity = UpcomingExamActivity.this;
        session = new Session(activity);
        progressDisplay = new ProgressDisplay(activity);
        tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText("Test");
        recyclerView = findViewById(R.id.recycler_view);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        progressDisplay.showProgress();

        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        loadTestCards();
    }
    private void loadTestCards() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDisplay.hideProgress();
                if (snapshot.exists()){

                    ArrayList<String> enrollid = new ArrayList<>();
                    for (DataSnapshot child : snapshot.child(Constant.STUDENT_ENROLL_COURSES_ID).getChildren()) {
                        Test post = child.getValue(Test.class);
                        if(post != null){
                            enrollid.add(post.getCourse_id());
                        }

                    }
                    ArrayList<Test> tests = new ArrayList<>();
                    for (DataSnapshot child : snapshot.child(Constant.TESTS).getChildren()) {
                        Test post = child.getValue(Test.class);
                        if(post != null){
                            if(enrollid.contains(post.getCourse_id())){
                                tests.add(post);
                            }
                        }
                    }
                    upcomingExamAdapter = new upcomingExamAdapter(tests, activity,"student");
                    recyclerView.setAdapter(upcomingExamAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}