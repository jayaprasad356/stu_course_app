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

import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Adapter.TestAdapter;

public class TestActivity extends AppCompatActivity {
    Button addTest;
    Activity activity;
    RecyclerView recyclerView;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        addTest=findViewById(R.id.add_test);
        recyclerView=findViewById(R.id.recycler_view);
        imgBack=findViewById(R.id.imgBack);
        activity=TestActivity.this;


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Test[] tests = new Test[]{
                new Test("tamil","worlds first language","11","11/11/12","11:15","11/25/25","10:25"),
                new Test("English","Common Language","12","11/11/12","11:15","11/25/25","10:25")
        };
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddTestActivity.class);
                startActivity(intent);
            }
        });
        TestAdapter adapter = new TestAdapter(tests, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}