package com.greymatter.studentcourseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etMobile, etPassword;
    Button btnLogin;
    Activity activity;
    RadioButton rbProfessor, rbStudent;
    TextView gotoLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);
        rbProfessor = findViewById(R.id.rb_professor);
        rbStudent = findViewById(R.id.rb_student);
        gotoLogin = findViewById(R.id.go_register);

        activity = MainActivity.this;
        gotoLogin.setOnClickListener(view -> {
            Intent intent = new Intent(activity, RegisterActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(activity, ProfessorActivity.class);
            startActivity(intent);
        });
    }
}