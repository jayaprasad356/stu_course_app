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
import com.greymatter.studentcourseapp.Model.Registerinfo;
import com.greymatter.studentcourseapp.R;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etMobile, etEmail, etPassword;
    Button btnReg;
    ImageView backbtn;
    RadioButton rbStudent, rbProfessor;
    RadioGroup radioGroup;
    Activity activity;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Registerinfo registerinfo;
    String value;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("register");

        registerinfo = new Registerinfo();


        etEmail = findViewById(R.id.et_email);
        etMobile = findViewById(R.id.et_mobile);
        etPassword = findViewById(R.id.et_password);
        etName = findViewById(R.id.et_name);
        btnReg = findViewById(R.id.btn_reg);
        rbProfessor = findViewById(R.id.rb_professor);
        rbStudent = findViewById(R.id.rb_student);
        rbStudent = findViewById(R.id.rb_student);
        radioGroup = findViewById(R.id.radioGroup);
        backbtn = findViewById(R.id.backbtnn);
        progressbar = findViewById(R.id.progressbar);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        value = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });


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

                // getting text from our edittext fields.
                String name = etName.getText().toString();
                String phone = etMobile.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String category = value;
                createUserFirebase(email, password, name, phone, category);


            }

        });

    }

    private void createUserFirebase(String email, String password, String name, String phone, String category) {
        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressbar.setVisibility(View.GONE);
                            Toast.makeText(activity, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            addDatatoFirebase(name, phone, email, password, category);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            progressbar.setVisibility(View.GONE);

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void addDatatoFirebase(String name, String phone, String email, String password, String category) {
        // below 3 lines of code is used to set
        // data in our object class.
        registerinfo.setName(name);
        registerinfo.setMobile(phone);
        registerinfo.setEmail(email);
        registerinfo.setCategory(category);
        registerinfo.setPassword(password);

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.child(phone).setValue(registerinfo);

                // after adding this data we are showing toast message.
                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(RegisterActivity.this, "Registration Failed  " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}