package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greymatter.studentcourseapp.Adapter.DashBoardListAdapter;
import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.R;

import java.util.ArrayList;

public class StudentDashboardActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    DashBoardListAdapter dashBoardListAdapter;
    Activity activity;
    Button btnViewCource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        activity = StudentDashboardActivity.this;



        recycler_view = findViewById(R.id.recycler_view);
        btnViewCource = findViewById(R.id.btnViewCource);


        btnViewCource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity,ViewCourseActivity.class);
                startActivity(intent);

            }
        });

        recycler_view.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        
        
        dashboarlist();



    }

    private void dashboarlist() {

        ArrayList<DashBoardList> dashBoardLists = new ArrayList<>();
        DashBoardList rings1 = new DashBoardList("tamil","worlds first language");
        DashBoardList rings2 = new DashBoardList("English","Common Language");


        dashBoardLists.add(rings1);
        dashBoardLists.add(rings2);



        dashBoardListAdapter = new DashBoardListAdapter(activity,dashBoardLists);
        recycler_view.setAdapter(dashBoardListAdapter);
    }
}