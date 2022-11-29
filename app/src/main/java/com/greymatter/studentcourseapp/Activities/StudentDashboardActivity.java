package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.StudentCoursesAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

public class StudentDashboardActivity extends AppCompatActivity {

    RecyclerView recycler_view;
    StudentCoursesAdapter studentCoursesAdapter;
    Activity activity;

    @Override
    protected void onStart() {
        super.onStart();
        studentCoursesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        studentCoursesAdapter.startListening();
    }

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

                Intent intent = new Intent(activity, EnrolledCourses.class);
                startActivity(intent);

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        loadCourseDetails();
        recycler_view.setLayoutManager(linearLayoutManager);

        recycler_view.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));
        
        




    }


    private void loadCourseDetails() {

        FirebaseRecyclerOptions<Course> options
                = new FirebaseRecyclerOptions.Builder<Course>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(Constant.COURSES), Course.class)
                .build();
        studentCoursesAdapter = new StudentCoursesAdapter(options, activity);
        recycler_view.setAdapter(studentCoursesAdapter);

    }




}