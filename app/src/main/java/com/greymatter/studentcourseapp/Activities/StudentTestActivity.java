package com.greymatter.studentcourseapp.Activities;

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
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.TestAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;

public class StudentTestActivity extends AppCompatActivity {


    TextView tvTitle;
    Activity activity;

    RecyclerView recyclerView;
    ImageView imgBack;
    TestAdapter testAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        testAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        testAdapter.stopListening();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);
        activity = StudentTestActivity.this;

        tvTitle = findViewById(R.id.tvTitle);

        String name = getIntent().getExtras().getString(Constant.NAME);

        tvTitle.setText(name);
        recyclerView = findViewById(R.id.recycler_view);
        imgBack = findViewById(R.id.imgBack);



        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        loadTestCards();
    }

    private void loadTestCards() {


        FirebaseRecyclerOptions<Test> options
                = new FirebaseRecyclerOptions.Builder<Test>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(Constant.TESTS), Test.class)
                .build();
        testAdapter = new TestAdapter(options, activity);
        recyclerView.setAdapter(testAdapter);

    }
}