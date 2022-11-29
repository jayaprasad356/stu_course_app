package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.EnrolledCoursesAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

public class EnrolledCourses extends AppCompatActivity {


    RecyclerView recycler_view;
    EnrolledCoursesAdapter enrolledCoursesAdapter;
    Activity activity;
    Button btnViewCource;
    ImageView imgBack;


    @Override
    protected void onStart() {
        super.onStart();
        enrolledCoursesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        enrolledCoursesAdapter.startListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrolled_course);


        activity = EnrolledCourses.this;



        recycler_view = findViewById(R.id.recycler_view);
        imgBack = findViewById(R.id.imgBack);


        imgBack.setOnClickListener(v -> onBackPressed());


        recycler_view.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));


        courselist();


    }

    private void courselist() {
        FirebaseRecyclerOptions<Course> options
                = new FirebaseRecyclerOptions.Builder<Course>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(Constant.STUDENT_ENROLL_COURSES), Course.class)
                .build();
        enrolledCoursesAdapter = new EnrolledCoursesAdapter(options, activity);
        recycler_view.setAdapter(enrolledCoursesAdapter);




    }
}