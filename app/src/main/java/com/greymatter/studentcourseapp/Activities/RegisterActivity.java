package com.greymatter.studentcourseapp.Activities;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Registerinfo;
import com.greymatter.studentcourseapp.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etMobile, etEmail, etPassword;
    Button btnReg;
    ImageView backbtn;
    RadioButton rbStudent, rbProfessor;
    Activity activity;
    Registerinfo registerinfo;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerinfo = new Registerinfo();


        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile);
        etPassword = findViewById(R.id.et_password);
        etName = findViewById(R.id.et_name);
        btnReg = findViewById(R.id.btn_reg);
        rbProfessor = findViewById(R.id.rb_professor);
        rbStudent = findViewById(R.id.rb_student);
        rbStudent = findViewById(R.id.rb_student);
        backbtn = findViewById(R.id.backbtnn);
        activity = RegisterActivity.this;
        backbtn.setOnClickListener(view -> {
            Intent intent = new Intent(activity, MainActivity.class);
            startActivity(intent);
        });
        btnReg.setOnClickListener(view -> {
            if (etName.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show();
            } else if (etName.getText().toString().length() < 4) {
                Toast.makeText(this, "Enter full Name", Toast.LENGTH_SHORT).show();

            } else if (etMobile.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your Mobile Number", Toast.LENGTH_SHORT).show();
            } else if (etEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show();
            } else if (etPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
            } else {
                if (rbStudent.isChecked()){
                    type = "Student";
                }
                else {
                    type = "Professor";

                }

                // getting text from our edittext fields.
                String name = etName.getText().toString();
                String phone = etMobile.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                addDatatoFirebase(name, phone, email, password, type);
            }

        });

    }


    private void addDatatoFirebase(String name, String phone, String email, String password, String type) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.REGISTRATION).child(Constant.removedot(email));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    Map<String, Object> map = new HashMap<>();
                    map.put(Constant.NAME, name);
                    map.put(Constant.MOBILE, phone);
                    map.put(Constant.EMAIL, email);
                    map.put(Constant.PASSWORD, password);
                    map.put(Constant.TYPE, type);
                    databaseReference.updateChildren(map);
                    Toast.makeText(activity, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity,MainActivity.class);
                    startActivity(intent);
                    finish();


                }else {
                    Toast.makeText(activity, "Email Already Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}