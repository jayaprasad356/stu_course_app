package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.DashBoardListAdapter;
import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Adapter.TestAdapter;

public class TestActivity extends AppCompatActivity {
    Button addTest;
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
        setContentView(R.layout.activity_test);
        addTest = findViewById(R.id.add_test);
        recyclerView = findViewById(R.id.recycler_view);
        imgBack = findViewById(R.id.imgBack);
        activity = TestActivity.this;


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        Test[] tests = new Test[]{
//                new Test("tamil","worlds first language","11","11/11/12","11:15","11/25/25","10:25"),
//                new Test("English","Common Language","12","11/11/12","11:15","11/25/25","10:25")
//        };
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddTestActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        loadTestCards();
    }

    private void loadTestCards() {
        FirebaseRecyclerOptions<Test> options
                = new FirebaseRecyclerOptions.Builder<Test>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("AddnewTest"), Test.class)
                .build();
        testAdapter = new TestAdapter(options, activity);
        recyclerView.setAdapter(testAdapter);
    }
}