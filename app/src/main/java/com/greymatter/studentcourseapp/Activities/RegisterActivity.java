package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.greymatter.studentcourseapp.R;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etMobile, etEmail, etPassword;
    Button btnReg;
    ImageView backbtn;
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
        backbtn=findViewById(R.id.backbtnn);
        activity=RegisterActivity.this;
        backbtn.setOnClickListener(view->{
                Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        });
        btnReg.setOnClickListener(view-> {
            if (etName.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show();
            }else if (etName.getText().toString().length() <4 ){
                Toast.makeText(this, "Enter full Name", Toast.LENGTH_SHORT).show();

            } else if (etMobile.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Mobile Number", Toast.LENGTH_SHORT).show();
            }else  if (etEmail.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show();
            } else if (etPassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            }

        });

    }
}