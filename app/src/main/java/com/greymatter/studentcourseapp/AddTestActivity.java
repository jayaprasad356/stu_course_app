package com.greymatter.studentcourseapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddTestActivity extends AppCompatActivity {
    ImageView imgBack;
    Button addQuestion;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);
        imgBack=findViewById(R.id.imgBack);
        addQuestion=findViewById(R.id.add_question);
        activity=AddTestActivity.this;
        Objects.requireNonNull(getSupportActionBar()).hide();
        imgBack.setOnClickListener(view -> onBackPressed());
        addQuestion.setOnClickListener(view -> {
            Intent intent = new Intent(activity, AddQuestionActivity.class);
            startActivity(intent);
        });
    }
}