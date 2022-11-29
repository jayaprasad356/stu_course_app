package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.Model.Question;
import com.greymatter.studentcourseapp.Model.Users;
import com.greymatter.studentcourseapp.ProgressDisplay;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.HashMap;
import java.util.Map;

public class QuestionViewActivity extends AppCompatActivity {
    Button nextQuestion;
    TextView firstOption, secOption, thirdOption, fourthOption,Ques;
    TextView tvQuesNo;
    RadioGroup radioGroup;
    String option,correct_option;
    String text,question,fq,sq,tq,forthq;
    Activity activity;
    Session session;
    ProgressDisplay progressDisplay;
    int score;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_view);

        nextQuestion = findViewById(R.id.nxt_question);
        tvQuesNo = findViewById(R.id.tvQuesNo);
        btnFinish = findViewById(R.id.btnFinish);
        radioGroup = findViewById(R.id.radioGroup);
        firstOption = findViewById(R.id.et_first);
        secOption = findViewById(R.id.et_second);
        thirdOption = findViewById(R.id.et_third);
        Ques=findViewById(R.id.edQuestion);
        fourthOption = findViewById(R.id.et_four);
        activity = QuestionViewActivity.this;
        session = new Session(activity);
        progressDisplay = new ProgressDisplay(activity);
        progressDisplay.showProgress();

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
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(Constant.QUESTIONS).child(session.getData(Constant.COURSE_ID)).child(session.getData(Constant.TEST_ID)).child(session.getInt(Constant.QUESTION_COUNT) + "").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDisplay.hideProgress();
                if (snapshot.exists()){
                    Question question = snapshot.getValue(Question.class);
                    assert question != null;
                    correct_option = question.getCorrect_option();
                    Ques.setText(question.getQuestion());
                    firstOption.setText(question.getOption_1());
                    secOption.setText(question.getOption_2());
                    thirdOption.setText(question.getOption_3());
                    fourthOption.setText(question.getOption_4());

                }
                else {
                    Intent intent = new Intent(activity,StudentTestActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnFinish.setOnClickListener(view -> {
            Intent intent = new Intent(activity,StudentTestActivity.class);
            startActivity(intent);
            finish();

        });

        nextQuestion.setOnClickListener(view -> {
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
                    next();
                } else {
                    Toast.makeText(activity, "Please Select Option", Toast.LENGTH_SHORT).show();
                }



        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
                Intent intent = new Intent(activity,StudentTestActivity.class);
        startActivity(intent);
        finish();
    }

    private void next()
    {
        if (option.equals(correct_option)){
            score = session.getInt(Constant.SCORES) + 1;

        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.SCORES).child(session.getData(Constant.COURSE_ID)).child(session.getData(Constant.TEST_ID)).child(Constant.removedot(session.getData(Constant.EMAIL)));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    databaseReference.child(Constant.SCORES).setValue(score);

                }else {
                    Map<String, Object> map = new HashMap<>();
                    map.put(Constant.SCORES, score+"");
                    map.put(Constant.EMAIL, Constant.removedot(session.getData(Constant.EMAIL)));
                    databaseReference.updateChildren(map);

                }

                session.setInt(Constant.QUESTION_COUNT,session.getInt(Constant.QUESTION_COUNT) + 1);
                session.setInt(Constant.SCORES,score + session.getInt(Constant.SCORES) );
                Intent intent = new Intent(activity,QuestionViewActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}