package com.greymatter.studentcourseapp.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.AddQuestion;
import com.greymatter.studentcourseapp.Model.Users;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.HashMap;
import java.util.Map;

public class AddQuestionActivity extends AppCompatActivity {
    Button nextQuestion, finish;
    EditText firstOption, secOption, thirdOption, fourthOption, Ques;
    TextView tvQuesNo;
    RadioGroup radioGroup;
    String option;
    String text, question, fq, sq, tq, forthq;
    Activity activity;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        nextQuestion = findViewById(R.id.nxt_question);
        tvQuesNo = findViewById(R.id.tvQuesNo);
        finish = findViewById(R.id.finish);
        radioGroup = findViewById(R.id.radioGroup);
        firstOption = findViewById(R.id.et_first);
        secOption = findViewById(R.id.et_second);
        thirdOption = findViewById(R.id.et_third);
        Ques = findViewById(R.id.edQuestion);
        fourthOption = findViewById(R.id.et_four);
        activity = AddQuestionActivity.this;
        session = new Session(activity);

        tvQuesNo.setText(session.getInt(Constant.QUESTION_COUNT) + "");
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
            Intent intent = new Intent(activity, ProfessorActivity.class);
            startActivity(intent);
            finish();

        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    savedata();
                } else {
                    Toast.makeText(activity, "Please Select Option", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void savedata() {
        question = Ques.getText().toString();
        fq = firstOption.getText().toString();
        sq = secOption.getText().toString();
        tq = thirdOption.getText().toString();
        forthq = fourthOption.getText().toString();

        if (!question.isEmpty() && !fq.isEmpty() && !sq.isEmpty()) {
            if (tq.isEmpty() || forthq.isEmpty()) {
                if (option == "3") {
                    Toast.makeText(activity, "Please Select Correct option", Toast.LENGTH_SHORT).show();
                } else if (option == "4") {
                    Toast.makeText(activity, "Please Select Correct option", Toast.LENGTH_SHORT).show();
                } else {
                    String currentTime = System.currentTimeMillis() / 1000 + "";
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.QUESTIONS).child(session.getData(Constant.COURSE_ID)).child(session.getData(Constant.TEST_ID)).child(session.getInt(Constant.QUESTION_COUNT) + "");
                    Map<String, Object> map = new HashMap<>();
                    map.put(Constant.EMAIL, session.getData(Constant.EMAIL));
                    map.put(Constant.COURSE_ID, session.getData(Constant.COURSE_ID));
                    map.put(Constant.TEST_ID, session.getData(Constant.TEST_ID));
                    map.put(Constant.QUESTION_ID, session.getInt(Constant.QUESTION_COUNT));
                    map.put(Constant.QUESTION, question);
                    map.put(Constant.OPTION_1, fq);
                    map.put(Constant.OPTION_2, sq);
                    map.put(Constant.OPTION_3, tq);
                    map.put(Constant.OPTION_4, forthq);
                    map.put(Constant.CORRECT_OPTION, option);
                    databaseReference.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            session.setInt(Constant.QUESTION_COUNT, session.getInt(Constant.QUESTION_COUNT) + 1);
                            Toast.makeText(AddQuestionActivity.this, "Question Added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity, AddQuestionActivity.class);
                            startActivity(intent);

                        }
                    });
                }
            }
        } else {
            Toast.makeText(activity, "Please provide answers", Toast.LENGTH_SHORT).show();
        }
    }

}