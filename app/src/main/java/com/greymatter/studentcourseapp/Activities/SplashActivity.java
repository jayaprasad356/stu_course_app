package com.greymatter.studentcourseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

public class SplashActivity extends AppCompatActivity {
    Session session;
    Activity activity;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler = new Handler();
        activity = SplashActivity.this;
        session =new Session(activity);
        GotoActivity();
    }
    private void GotoActivity()
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!session.getData(Constant.EMAIL).equals("")){
                    if (session.getData(Constant.TYPE).equals("Student")){
                        Intent intent=new Intent(activity,StudentDashboardActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Intent intent=new Intent(activity,ProfessorActivity.class);
                        startActivity(intent);
                    }

                    finish();

                }else{
                    Intent intent=new Intent(activity,MainActivity.class);
                    startActivity(intent);
                    finish();

                }



            }
        },2000);
    }
}