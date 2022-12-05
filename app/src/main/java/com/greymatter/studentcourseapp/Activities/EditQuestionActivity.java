package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Question;
import com.greymatter.studentcourseapp.ProgressDisplay;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EditQuestionActivity extends AppCompatActivity {
    String courseId, testID;
    Session session;
    Button nextQuestion;
    TextView firstOption, secOption, thirdOption, fourthOption, Ques;
    TextView tvQuesNo;
    RadioGroup radioGroup;
    String option = "", correct_option;
    String text;
    Activity activity;
    RadioButton r1,r2,r3,r4;
    ProgressDisplay progressDisplay;
    int score;
    String question, fq, sq, tq, forthq;
    Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        nextQuestion = findViewById(R.id.nxt_question);
        tvQuesNo = findViewById(R.id.tvQuesNo);
        // btnFinish = findViewById(R.id.btnFinish);
        radioGroup = findViewById(R.id.radioGroup);
        firstOption = findViewById(R.id.et_first);
        secOption = findViewById(R.id.et_second);
        thirdOption = findViewById(R.id.et_third);
        Ques = findViewById(R.id.edQuestion);
        fourthOption = findViewById(R.id.et_four);
        r1=findViewById(R.id.first_option);
        r2=findViewById(R.id.sec_option);
        r3=findViewById(R.id.third_option);
        r4=findViewById(R.id.forth_option);
        finish=findViewById(R.id.finish);
        activity = EditQuestionActivity.this;
        session = new Session(activity);
        progressDisplay = new ProgressDisplay(activity);
        progressDisplay.showProgress();


        tvQuesNo.setText(session.getInt(Constant.QUESTION_COUNT) + "");

        finish.setOnClickListener(view -> {
            Intent intent = new Intent(activity, ProfessorActivity.class);
            startActivity(intent);
            finish();
        });
        session = new Session(this);
        nextQuestion.setOnClickListener(view -> {
            if (r1.isChecked()){
                option = "1";

            }else if (r2.isChecked()){
                option = "2";

            }else if (r3.isChecked()){
                option = "3";

            }else if (r4.isChecked()){
                option = "4";

            }
            if (!option.equals("")) {
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


        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(Constant.QUESTIONS).child(session.getData(Constant.COURSE_ID)).child(session.getData(Constant.TEST_ID)).child(session.getInt(Constant.QUESTION_COUNT) + "").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDisplay.hideProgress();
                if (snapshot.exists()) {
                    Question question = snapshot.getValue(Question.class);
                    assert question != null;
                    if (question.getCorrect_option().equals("1")){
                        r1.setChecked(true);
                    }
                    if (question.getCorrect_option().equals("2")){
                        r2.setChecked(true);
                    }
                    if (question.getCorrect_option().equals("3")){
                        r3.setChecked(true);
                    }
                    if (question.getCorrect_option().equals("4")){
                        r4.setChecked(true);
                    }
                    correct_option = question.getCorrect_option();
                    Ques.setText(question.getQuestion());
                    firstOption.setText(question.getOption_1());
                    secOption.setText(question.getOption_2());
                    thirdOption.setText(question.getOption_3());
                    fourthOption.setText(question.getOption_4());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        courseId = session.getData(Constant.COURSE_ID);
        testID = session.getData(Constant.TEST_ID);
    }

    private void savedata() {
        question = Ques.getText().toString();
        fq = firstOption.getText().toString();
        sq = secOption.getText().toString();
        tq = thirdOption.getText().toString();
        forthq = fourthOption.getText().toString();

        if (!question.isEmpty() && !fq.isEmpty() && !sq.isEmpty()) {
            if (tq.isEmpty() || forthq.isEmpty()) {
                if (option.equals("3")) {
                    Toast.makeText(activity, "Please Select Correct option", Toast.LENGTH_SHORT).show();
                } else if (option.equals("4")) {
                    Toast.makeText(activity, "Please Select Correct option", Toast.LENGTH_SHORT).show();
                } else {
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
                            Toast.makeText(activity, "Question Added", Toast.LENGTH_SHORT).show();
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