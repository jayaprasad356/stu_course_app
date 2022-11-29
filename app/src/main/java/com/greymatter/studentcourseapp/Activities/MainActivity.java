package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
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
import com.greymatter.studentcourseapp.Model.Users;
import com.greymatter.studentcourseapp.ProgressDisplay;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edEmail, edPassword;
    Button btnLogin;
    Activity activity;
    TextView gotoLogin;
    ProgressDisplay progressDisplay;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btn_login);
        gotoLogin = findViewById(R.id.go_register);
        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        activity = MainActivity.this;
        session = new Session(activity);
        progressDisplay = new ProgressDisplay(activity);
        gotoLogin.setOnClickListener(view -> {
            Intent intent = new Intent(activity, RegisterActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(view -> {

            if (edEmail.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            } else if (edPassword.getText().toString().isEmpty()) {
                Toast.makeText(this, "Enter Passwword", Toast.LENGTH_SHORT).show();
            } else {

                progressDisplay.showProgress();
                loginUserAccount();
            }


        });

    }
    private void loginUserAccount() {
        String email, password;
        email = edEmail.getText().toString();
        password = edPassword.getText().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.REGISTRATION).child(Constant.removedot(email));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDisplay.hideProgress();
                if (snapshot.exists()){
                    Users users = snapshot.getValue(Users.class);

                    if (users.getPassword().equals(password)){
                        Toast.makeText(activity, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                        Intent intent;
                        session.setData(Constant.EMAIL,users.getEmail());
                        session.setData(Constant.TYPE,users.getType());
                        if (users.getType().equals("Student")){
                            intent = new Intent(activity,StudentDashboardActivity.class);

                        }
                        else {
                            intent = new Intent(activity,ProfessorActivity.class);
                        }
                        startActivity(intent);
                        finish();

                    }else {
                        Toast.makeText(activity, "Password Wrong", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(activity, "Email Not Registered", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}