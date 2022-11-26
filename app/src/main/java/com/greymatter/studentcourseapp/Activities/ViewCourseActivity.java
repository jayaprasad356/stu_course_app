package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.greymatter.studentcourseapp.Adapter.DashBoardListAdapter;
import com.greymatter.studentcourseapp.Adapter.ViewCourseListAdapter;
import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.Model.ViewCourseList;
import com.greymatter.studentcourseapp.R;

import java.util.ArrayList;

public class ViewCourseActivity extends AppCompatActivity {


    RecyclerView recycler_view;
    ViewCourseListAdapter viewCourseListAdapter;
    Activity activity;
    Button btnViewCource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);


        activity = ViewCourseActivity.this;



        recycler_view = findViewById(R.id.recycler_view);


        recycler_view.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false));


        courselist();


    }

    private void courselist() {


        ArrayList<ViewCourseList> viewCourseLists = new ArrayList<>();
        ViewCourseList rings1 = new ViewCourseList("tamil","worlds first language");
        ViewCourseList rings2 = new ViewCourseList("English","Common Language");


        viewCourseLists.add(rings1);
        viewCourseLists.add(rings2);



        viewCourseListAdapter = new ViewCourseListAdapter(activity,viewCourseLists);
        recycler_view.setAdapter(viewCourseListAdapter);
    }
}