package com.greymatter.studentcourseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity {
    Button addDetails;
    ImageView back;
    Activity activity;
    EditText etCourse, etDesc, etStartDate, etEndDate, etStartTime, etEndTime;
    String course,description,startDate,endDate,startTime,endTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        addDetails = findViewById(R.id.add_detals);
        back = findViewById(R.id.imgBack);
        etCourse = findViewById(R.id.course_name);
        etDesc = findViewById(R.id.description);
        etStartDate = findViewById(R.id.start_date);
        etEndDate = findViewById(R.id.end_date);
        etStartTime = findViewById(R.id.start_time);
        etEndTime = findViewById(R.id.end_time);
        activity=AddCourseActivity.this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfessorActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().hide();
        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course=etCourse.getText().toString();
                description=etDesc.getText().toString();
                startDate=etStartDate.getText().toString();
                startTime=etStartTime.getText().toString();
                endDate=etEndDate.getText().toString();
                endTime=etEndTime.getText().toString();

                Course[] courses = new Course[]{
                        new Course(course,description,startDate,startTime,endDate,endTime)

                };


            }
        });
    }
}