package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.AddCourse;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddCourseActivity extends AppCompatActivity {
    Button addDetails;
    ImageView back;
    Activity activity;
    EditText etCourse, etDesc;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        addDetails = findViewById(R.id.add_detals);
        back = findViewById(R.id.imgBack);
        etCourse = findViewById(R.id.course_name);
        etDesc = findViewById(R.id.description);
        activity=AddCourseActivity.this;
        session = new Session(activity);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addDetails.setOnClickListener(view->{
            if (etCourse.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter Course Name", Toast.LENGTH_SHORT).show();
            }else if (etDesc.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter Course Description", Toast.LENGTH_SHORT).show();
            }else {
                savedata();




            }
        });
    }

    private void savedata() {

        String currentTime = System.currentTimeMillis()/1000 + "";
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.COURSES).child(currentTime);
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.EMAIL, session.getData(Constant.EMAIL));
        map.put(Constant.NAME, etCourse.getText().toString().trim());
        map.put(Constant.DESCRIPTION, etDesc.getText().toString().trim());
        map.put(Constant.COURSE_ID, currentTime);
        databaseReference.updateChildren(map);
        Toast.makeText(activity, "Course Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity,ProfessorActivity.class);
        startActivity(intent);
        finish();
    }

}