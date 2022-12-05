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
    String option, correct_option;
    String text, question, fq, sq, tq, forthq;
    Activity activity;
    RadioButton r1,r2,r3,r4;
    ProgressDisplay progressDisplay;
    int score;

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
        activity = EditQuestionActivity.this;
        session = new Session(activity);
        progressDisplay = new ProgressDisplay(activity);
        progressDisplay.showProgress();

//        btnFinish.setOnClickListener(view -> {
//            Intent intent = new Intent(activity,StudentTestActivity.class);
//            startActivity(intent);
//            finish();
//
//        });
        session = new Session(this);
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
        //loadQuestions();
    }

    private void loadQuestions() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.TESTS).child(courseId).child(testID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Iterator<DataSnapshot> dataSnapshots = snapshot.getChildren().iterator();
                    while (dataSnapshots.hasNext()) {
                        DataSnapshot dataSnapshotChild = dataSnapshots.next();
                        System.out.println(dataSnapshotChild);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void next() {
        if (option.equals(correct_option)) {
            score = session.getInt(Constant.SCORES) + 1;

        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.SCORES).child(session.getData(Constant.COURSE_ID)).child(session.getData(Constant.TEST_ID)).child(Constant.removedot(session.getData(Constant.EMAIL)));

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    databaseReference.child(Constant.SCORES).setValue(score);

                } else {
                    Map<String, Object> map = new HashMap<>();
                    map.put(Constant.SCORES, score + "");
                    map.put(Constant.EMAIL, Constant.removedot(session.getData(Constant.EMAIL)));
                    databaseReference.updateChildren(map);

                }

                session.setInt(Constant.QUESTION_COUNT, session.getInt(Constant.QUESTION_COUNT) + 1);
                session.setInt(Constant.SCORES, score + session.getInt(Constant.SCORES));
                Intent intent = new Intent(activity, QuestionViewActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}