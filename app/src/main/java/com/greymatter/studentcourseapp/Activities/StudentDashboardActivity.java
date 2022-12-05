package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.StudentCoursesAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class StudentDashboardActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

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

    Button btnViewCource, upCommingTest;
    Session session;

    ImageView imgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        activity = StudentDashboardActivity.this;
        session = new Session(activity);



        recycler_view = findViewById(R.id.recycler_view);
        btnViewCource = findViewById(R.id.btnViewCource);
        upCommingTest = findViewById(R.id.upcomming_test);
        imgMenu = findViewById(R.id.imgMenu);


        btnViewCource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, EnrolledCourses.class);
                startActivity(intent);

            }
        });
        upCommingTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UpcomingExamActivity.class);
                startActivity(intent);

            }
        });



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        loadCourseDetails();
        recycler_view.setLayoutManager(linearLayoutManager);

        recycler_view.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopup(view);
            }
        });


    }

    public void showpopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }


    private void loadCourseDetails() {
        FirebaseRecyclerOptions<Course> options
                = new FirebaseRecyclerOptions.Builder<Course>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(Constant.COURSES), Course.class)
                .build();
        studentCoursesAdapter = new StudentCoursesAdapter(options, activity);
        recycler_view.setAdapter(studentCoursesAdapter);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.logoutitem) {
            session.logoutUser(activity);
        }
        return false;
    }


}