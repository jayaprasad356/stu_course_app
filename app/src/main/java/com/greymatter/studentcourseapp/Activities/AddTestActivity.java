package com.greymatter.studentcourseapp.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.greymatter.studentcourseapp.R;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialog;
import com.niwattep.materialslidedatepicker.SlideDatePickerDialogCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTestActivity extends AppCompatActivity implements SlideDatePickerDialogCallback {
    ImageView imgBack;
    Button addQuestion;
    Activity activity;
    int hour, minute;
    EditText etdisc;
    EditText ettitle;
    TextView tvStartDate,tvEndDate,tvStartTime,tvEndtime;
    boolean startdate = false,enddate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_test);


        tvStartDate = findViewById(R.id.tvStartDate);
        tvEndDate = findViewById(R.id.tvEndDate);
        tvStartTime = findViewById(R.id.tvStartTime);
        tvEndtime = findViewById(R.id.tvEndtime);
        imgBack=findViewById(R.id.imgBack);

        ettitle=findViewById(R.id.etTitlee);
        etdisc=findViewById(R.id.tvDisc);

        addQuestion=findViewById(R.id.add_question);
        activity=AddTestActivity.this;
       
        imgBack.setOnClickListener(view -> onBackPressed());
        addQuestion.setOnClickListener(view -> {
            if (ettitle.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter Title", Toast.LENGTH_SHORT).show();
            }else if (etdisc.getText().toString().isEmpty()){
                Toast.makeText(this, "Enter description", Toast.LENGTH_SHORT).show();

            }else {
                Intent intent = new Intent(activity, AddQuestionActivity.class);
                startActivity(intent);
            }

        });


        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar endDate = Calendar.getInstance();
                endDate.set(Calendar.YEAR, 2100);
                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();
                builder.setEndDate(endDate);
                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getSupportFragmentManager(), "Dialog");
                startdate = true;
                enddate = false;
            }
        });
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar endDate = Calendar.getInstance();
                endDate.set(Calendar.YEAR, 2100);
                SlideDatePickerDialog.Builder builder = new SlideDatePickerDialog.Builder();

                builder.setEndDate(endDate);
                SlideDatePickerDialog dialog = builder.build();
                dialog.show(getSupportFragmentManager(), "Dialog");
                startdate = false;
                enddate = true;
            }
        });


        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popStartTimepicker();

            }
        });
        tvEndtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popEndTimepicker();

            }
        });


    }



    private void popEndTimepicker() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                String AM_PM;
                if (selectedHour>=0&&selectedHour<12){
                    AM_PM=" AM";
                }else {
                    AM_PM=" PM";
                }
                // tvEndtime.setText ( selectedHour + ":" + minute+""+AM_PM );
                tvEndtime.setText(String.format(Locale.getDefault(), "%02d:%02d %s", (hour == 12 || hour == 0) ? 12 : hour % 12, minute, AM_PM ));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute,false );

        timePickerDialog.setTitle("End Time");
        timePickerDialog.show();
    }

    private void popStartTimepicker() {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {hour = selectedHour;
                minute = selectedMinute;
                String AM_PM;
                if (selectedHour>=0&&selectedHour<12){
                    AM_PM=" AM";
                }else {
                    AM_PM=" PM";
                }
                // tvEndtime.setText ( selectedHour + ":" + minute+""+AM_PM );
                tvStartTime.setText(String.format(Locale.getDefault(), "%02d:%02d %s", (hour == 12 || hour == 0) ? 12 : hour % 12, minute, AM_PM ));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute,false );

        timePickerDialog.setTitle("Start Time");
        timePickerDialog.show();

    }



    @Override
    public void onPositiveClick(int date, int month, int year, Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat(" dd EEE, MMM", Locale.getDefault());
        if (startdate){
            tvStartDate.setText(format.format(calendar.getTime()));
        }else {
            tvEndDate.setText(format.format(calendar.getTime()));
        }


    }

}