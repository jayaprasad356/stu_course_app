package com.greymatter.studentcourseapp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Model.AddQuestion;
import com.greymatter.studentcourseapp.Model.Users;
import com.greymatter.studentcourseapp.R;

public class AddQuestionActivity extends AppCompatActivity {
    Button nextQuestion, finish;
    ImageView imgback;
    EditText firstOption, secOption, thirdOption, fourthOption,Ques;
    RadioGroup radioGroup;
    String option;
    String text,question,fq,sq,tq,forthq;
    Activity activity;
    FirebaseDatabase db;
    DatabaseReference reference;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        nextQuestion = findViewById(R.id.nxt_question);
        finish = findViewById(R.id.finish);
        imgback = findViewById(R.id.imgBack);
        radioGroup = findViewById(R.id.radioGroup);
        firstOption = findViewById(R.id.et_first);
        secOption = findViewById(R.id.et_second);
        dialog= new ProgressDialog(this);
        dialog.setMessage("Please Wait...");
        thirdOption = findViewById(R.id.et_third);
        Ques=findViewById(R.id.question);
        fourthOption = findViewById(R.id.et_four);
        activity = AddQuestionActivity.this;
        radioGroup.setOnCheckedChangeListener((radioGroup, radioButtonID) -> {
            switch (radioButtonID) {
                case R.id.first_option:
                    option = "1";
                    break;
                case R.id.sec_option:
                    option = "2";
                    break;
                case R.id.third_option:
                    option = "3";
                    break;
                case R.id.forth_option:
                    option = "4";
                    break;
            }
        });
        finish.setOnClickListener(view -> {
            dialog.show();
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId != -1) {
                switch (option) {
                    case "1":
                        text = firstOption.getText().toString();
                        break;
                    case "2":
                        text = secOption.getText().toString();
                        break;
                    case "3":
                        text = thirdOption.getText().toString();
                        break;
                    case "4":
                        text = fourthOption.getText().toString();
                        break;
                }
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            } else {
                dialog.dismiss();
                Toast.makeText(activity, "Please Select Option", Toast.LENGTH_SHORT).show();
                savedata();
            }

        });
        imgback.setOnClickListener(view -> onBackPressed());
    }
    private void savedata() {
        question=Ques.getText().toString();
        fq=firstOption.getText().toString();
        sq=secOption.getText().toString();
        tq=thirdOption.getText().toString();
        forthq=fourthOption.getText().toString();

        if (!question.isEmpty() && !fq.isEmpty() && !sq.isEmpty() && !tq.isEmpty() && !forthq.isEmpty()){

            AddQuestion users = new AddQuestion(question,fq,sq,tq,forthq);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("NewRegisterUsers");
            reference.child(question).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    Toast.makeText(AddQuestionActivity.this,"Successfuly Saved",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    Intent intent = new Intent(activity, MainActivity.class);
                    startActivity(intent);
                }
            });

        }
    }

}