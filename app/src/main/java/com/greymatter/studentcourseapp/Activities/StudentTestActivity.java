package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Adapter.TestAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.ProgressDisplay;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class StudentTestActivity extends AppCompatActivity {


    TextView tvTitle;
    Activity activity;

    RecyclerView recyclerView;
    ImageView imgBack;
    TestAdapter testAdapter;
    Session session;
    ProgressDisplay progressDisplay;

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
        //testAdapter.stopListening();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);
        activity = StudentTestActivity.this;
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
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.TESTS).child(session.getData(Constant.COURSE_ID));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDisplay.hideProgress();
                if (snapshot.exists()){
                    FirebaseRecyclerOptions<Test> options
                            = new FirebaseRecyclerOptions.Builder<Test>()
                            .setQuery(databaseReference, Test.class)
                            .build();
                    testAdapter = new TestAdapter(options, activity,"student");
                    recyclerView.setAdapter(testAdapter);
                    testAdapter.startListening();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}