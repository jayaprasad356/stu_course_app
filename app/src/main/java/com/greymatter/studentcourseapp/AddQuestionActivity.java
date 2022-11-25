package com.greymatter.studentcourseapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddQuestionActivity extends AppCompatActivity {
    Button nextQuestion, finish;
    ImageView imgback;
    EditText firstOption, secOption, thirdOption, fourthOption;
    RadioGroup radioGroup;
    String option;
    String text;
    Activity activity;

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
        thirdOption = findViewById(R.id.et_third);
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
                Toast.makeText(activity, "Please Select Option", Toast.LENGTH_SHORT).show();
            }

        });
        imgback.setOnClickListener(view -> onBackPressed());
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}