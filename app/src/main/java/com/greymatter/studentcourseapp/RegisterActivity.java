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

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etMobile, etEmail, etPassword;
    Button btnReg;
    RadioButton rbStudent,rbProfessor;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile);
        etPassword = findViewById(R.id.et_password);
        etName = findViewById(R.id.et_name);
        btnReg = findViewById(R.id.btn_reg);
        rbProfessor=findViewById(R.id.rb_professor);
        rbStudent=findViewById(R.id.rb_student);
        activity=RegisterActivity.this;
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}