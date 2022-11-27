package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

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


        activity=AddCourseActivity.this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfessorActivity.class);
                startActivity(intent);
            }
        });

        addDetails.setOnClickListener(view->{
            if (etCourse.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter Title", Toast.LENGTH_SHORT).show();
            }else if (etDesc.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter description", Toast.LENGTH_SHORT).show();
            }else {
                course = etCourse.getText().toString();
                description = etDesc.getText().toString();

                Course[] courses = new Course[]{
                        new Course(course, description, startDate, startTime, endDate, endTime)
                };






            }
        });
    }
}