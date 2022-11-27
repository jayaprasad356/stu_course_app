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
import com.greymatter.studentcourseapp.R;

public class MainActivity extends AppCompatActivity {
    EditText etMobile, etPassword;
    Button btnLogin;
    Activity activity;

    RadioButton rbProfessor, rbStudent;
    TextView gotoLogin;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;



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
        progressbar = findViewById(R.id.progressbar);
        activity = MainActivity.this;

        mAuth = FirebaseAuth.getInstance();

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


                loginUserAccount();

//                Intent intent = new Intent(activity, ProfessorActivity.class);
//                startActivity(intent);
            }



   });

    }

    private void loginUserAccount()
    {

        // show the visibility of progress bar to show loading
        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String mobile, password;
        mobile = etMobile.getText().toString();
        password = etPassword.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter  Mobile no!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(mobile, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(MainActivity.this,
                                            StudentDashboardActivity.class);
                                    startActivity(intent);
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }

}