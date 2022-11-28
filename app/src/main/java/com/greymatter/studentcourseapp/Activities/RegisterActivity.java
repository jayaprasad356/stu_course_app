package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Model.AddQuestion;
import com.greymatter.studentcourseapp.Model.Users;
import com.greymatter.studentcourseapp.R;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etMobile, etEmail, etPassword;
    Button btnReg;
    ImageView backbtn;
    RadioButton rbStudent,rbProfessor;
    Activity activity;
    String name, phonenumber, email, password;
    FirebaseDatabase db;
    DatabaseReference reference;
    ProgressDialog dialog;

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
        dialog= new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        backbtn.setOnClickListener(view->{
                Intent intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
        });
        btnReg.setOnClickListener(view-> {
            dialog.show();
            if (etName.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Name", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }else if (etName.getText().toString().length() <4 ){
                Toast.makeText(this, "Enter full Name", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else if (etMobile.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Mobile Number", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }else  if (etEmail.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Email", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else if (etPassword.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter your Password", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }else {
                savedata();
            }

        });

    }

    private void savedata() {
        name=etName.getText().toString();
        phonenumber=etMobile.getText().toString();
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();

        if (!name.isEmpty() && !phonenumber.isEmpty() && !email.isEmpty() && !password.isEmpty()){

            Users users = new Users(name,phonenumber,email,password);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("NewRegisterUsers");
            reference.child(name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                   etName.setText("");
                    etMobile.setText("");
                    etEmail.setText("");
                  etPassword.setText("");
                    Toast.makeText(RegisterActivity.this,"Successfuly Saved",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent intent = new Intent(activity, MainActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

}