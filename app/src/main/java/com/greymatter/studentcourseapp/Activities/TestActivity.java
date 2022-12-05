package com.greymatter.studentcourseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Adapter.TestAdapter;
import com.greymatter.studentcourseapp.Session;

public class TestActivity extends AppCompatActivity {
    Button addTest;
    Activity activity;
    RecyclerView recyclerView;
    ImageView imgBack;
    TestAdapter testAdapter;
    Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        addTest = findViewById(R.id.add_test);
        recyclerView = findViewById(R.id.recycler_view);
        imgBack = findViewById(R.id.imgBack);
        activity = TestActivity.this;
        session = new Session(activity);


        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AddTestActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        loadTestCards();
    }

    private void loadTestCards() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.TESTS);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    FirebaseRecyclerOptions<Test> options
                            = new FirebaseRecyclerOptions.Builder<Test>()
                            .setQuery(databaseReference.orderByChild(Constant.COURSE_ID).equalTo(session.getData(Constant.COURSE_ID)), Test.class)
                            .build();
                    testAdapter = new TestAdapter(options, activity,"professor");
                    recyclerView.setAdapter(testAdapter);
                    testAdapter.startListening();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}