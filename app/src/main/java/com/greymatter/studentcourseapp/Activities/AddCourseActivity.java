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
import com.greymatter.studentcourseapp.Model.AddCourse;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;

public class AddCourseActivity extends AppCompatActivity {
    Button addDetails;
    ImageView back;
    Activity activity;
    EditText etCourse, etDesc, etStartDate, etEndDate, etStartTime, etEndTime;
    String course,title,description,startDate,endDate,startTime,endTime;
    FirebaseDatabase db;
    DatabaseReference reference;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        addDetails = findViewById(R.id.add_detals);
        back = findViewById(R.id.imgBack);
        etCourse = findViewById(R.id.course_name);
        etDesc = findViewById(R.id.description);

        activity=AddCourseActivity.this;
        dialog= new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        activity=AddCourseActivity.this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ProfessorActivity.class);
                startActivity(intent);
            }
        });

        addDetails.setOnClickListener(view->{
            dialog.show();
            if (etCourse.getText().toString().isEmpty()){
                dialog.dismiss();
                Toast.makeText(this, "Enter Title", Toast.LENGTH_SHORT).show();
            }else if (etDesc.getText().toString().isEmpty()){
                dialog.dismiss();
                Toast.makeText(this, "Enter description", Toast.LENGTH_SHORT).show();
            }else {
                course = etCourse.getText().toString();
                description = etDesc.getText().toString();


               savedata();




            }
        });
    }

    private void savedata() {
        title=etCourse.getText().toString();
        description=etDesc.getText().toString();


        if (!title.isEmpty() && !description.isEmpty()){

            AddCourse users = new AddCourse(title,description);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("AddCourse");
            reference.child(title).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    etCourse.setText("");
                    etDesc.setText("");

                    Toast.makeText(AddCourseActivity.this,"Successfuly Saved",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent intent = new Intent(activity, ProfessorActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

}