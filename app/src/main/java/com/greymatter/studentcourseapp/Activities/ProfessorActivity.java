package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.greymatter.studentcourseapp.Adapter.CoursesAdapter;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

public class ProfessorActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addCourse;
    LinearLayout newCourseLayout;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        activity = ProfessorActivity.this;
        recyclerView = findViewById(R.id.recycler_view);
        addCourse = findViewById(R.id.add_course);
        addCourse.setOnClickListener(view -> addNewCourse());
        Course[] courses = new Course[]{
                new Course("tamil","worlds first language","11/11/12","11:15","11/25/25","10:25"),
                new Course("English","Common Language","11/11/12","11:15","11/25/25","10:25")
                   };

        CoursesAdapter adapter = new CoursesAdapter(courses, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    private void addNewCourse() {
        Intent intent = new Intent(activity, AddCourseActivity.class);
        startActivity(intent);

    }

}