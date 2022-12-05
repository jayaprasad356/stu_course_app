package com.greymatter.studentcourseapp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Adapter.CoursesAdapter;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.ProgressDisplay;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class ProfessorActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    RecyclerView recyclerView;
    Button addCourse;
    Activity activity;
    CoursesAdapter coursesAdapter;
    ImageView imgMenu;
    Session session;
    ProgressDisplay progressDisplay;

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
        session  = new Session(activity);
        recyclerView = findViewById(R.id.recycler_view);
        addCourse = findViewById(R.id.add_course);
        imgMenu = findViewById(R.id.imgMenu);

        progressDisplay = new ProgressDisplay(activity);
        addCourse.setOnClickListener(view -> addNewCourse());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);

        progressDisplay.showProgress();
        loadCourseDetails();
        recyclerView.setLayoutManager(linearLayoutManager);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpopup(view);
            }
        });


    }
    public void showpopup(View v){
        PopupMenu popup = new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    private void loadCourseDetails() {

            FirebaseRecyclerOptions<Course> options
                    = new FirebaseRecyclerOptions.Builder<Course>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child(Constant.COURSES).orderByChild(Constant.EMAIL).equalTo(session.getData(Constant.EMAIL)), Course.class)
                    .build();
            coursesAdapter = new CoursesAdapter(options, activity);
            recyclerView.setAdapter(coursesAdapter);
            progressDisplay.hideProgress();

    }


    private void addNewCourse() {
        Intent intent = new Intent(activity, AddCourseActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.logoutitem){
            session.logoutUser(activity);
        }
        return false;
    }

}