package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.greymatter.studentcourseapp.R;

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
        etMobile = findViewById(R.id.mobile_num);
        etPassword = findViewById(R.id.et_password);
        activity = MainActivity.this;
        gotoLogin.setOnClickListener(view -> {
            Intent intent = new Intent(activity, RegisterActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(view->{

            if (etMobile.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Phone number", Toast.LENGTH_SHORT).show();
            }else if (etMobile.getText().toString().length() != 10){
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show();
            }else if (etPassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter Your passwword", Toast.LENGTH_SHORT).show();
            }else {

                Intent intent = new Intent(activity, ProfessorActivity.class);
                startActivity(intent);
            }



   });

    }

    private void btnclick() {

    }


}