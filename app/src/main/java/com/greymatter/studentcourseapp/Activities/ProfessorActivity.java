package com.greymatter.studentcourseapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.CoursesAdapter;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

public class ProfessorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addCourse;
    LinearLayout newCourseLayout;
    Activity activity;
    CoursesAdapter coursesAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        coursesAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        coursesAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        activity = ProfessorActivity.this;
        recyclerView = findViewById(R.id.recycler_view);
        addCourse = findViewById(R.id.add_course);
        addCourse.setOnClickListener(view -> addNewCourse());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        loadCourseDetails();
        recyclerView.setLayoutManager(linearLayoutManager);


    }

    private void loadCourseDetails() {

            FirebaseRecyclerOptions<Course> options
                    = new FirebaseRecyclerOptions.Builder<Course>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("AddCourse"), Course.class)
                    .build();
            coursesAdapter = new CoursesAdapter(options, activity);
            recyclerView.setAdapter(coursesAdapter);

    }


    private void addNewCourse() {
        Intent intent = new Intent(activity, AddCourseActivity.class);
        startActivity(intent);

    }

}