
package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Activities.AddQuestionActivity;
import com.greymatter.studentcourseapp.Activities.AddTestActivity;
import com.greymatter.studentcourseapp.Activities.EditQuestionActivity;
import com.greymatter.studentcourseapp.Activities.QuestionViewActivity;
import com.greymatter.studentcourseapp.Activities.ViewScoreActivity;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Test;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;


public class TestAdapter extends FirebaseRecyclerAdapter<Test, TestAdapter.ViewHolder> {

    Activity activity;
    Session session;
    String type;

    public TestAdapter(FirebaseRecyclerOptions<Test> options, Activity activity, String type) {
        super(options);
        this.activity = activity;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.test_views, parent, false);
        return new ViewHolder(listItem);

    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Test model) {
        session = new Session(activity);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(Constant.QUESTIONS).child(session.getData(Constant.COURSE_ID)).child(model.getTest_id()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int total = (int) snapshot.getChildrenCount();
                holder.noOfQuestion.setText("No. of Questions = " + total + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (type.equals("professor")) {
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnScore.setVisibility(View.VISIBLE);
            holder.btnEdit.setVisibility(View.VISIBLE);
        } else {
            String currentTime = System.currentTimeMillis() / 1000 + "";
            if (Long.parseLong(model.getStart_timestamp()) <= Long.parseLong(currentTime) && Long.parseLong(model.getEnd_timestamp()) >= Long.parseLong(currentTime)) {
                holder.btnStart.setVisibility(View.VISIBLE);

            }

        }

        holder.title.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.startDate.setText("Start Date: " + model.getStartdate());
        holder.startTime.setText("Start Time: " + model.getStarttime());
        holder.endDate.setText("End Date: " + model.getEnddate());
        holder.endTime.setText("End Time: " + model.getEndtime());
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, QuestionViewActivity.class);
                activity.startActivity(intent);
                session.setInt(Constant.QUESTION_COUNT, 1);
                session.setData(Constant.TEST_ID, model.getTest_id());
                session.setInt(Constant.SCORES, 0);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.TESTS).child(model.getCourse_id()).child(model.getTest_id());
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            databaseReference.removeValue();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
        holder.btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ViewScoreActivity.class);
                activity.startActivity(intent);
                session.setData(Constant.TEST_ID, model.getTest_id());

            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, EditQuestionActivity.class);
                session.setInt(Constant.QUESTION_COUNT, 1);
                session.setData(Constant.TEST_ID, model.getTest_id());
                session.setData(Constant.COURSE_ID, model.getCourse_id());
                activity.startActivity(intent);
            }
        });
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, noOfQuestion, startDate, startTime, endDate, endTime;
        Button btnDelete, btnStart, btnScore, btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.description = itemView.findViewById(R.id.description);
            this.noOfQuestion = itemView.findViewById(R.id.no_of_question);
            this.startDate = itemView.findViewById(R.id.start_date);
            this.startTime = itemView.findViewById(R.id.start_time);
            this.endDate = itemView.findViewById(R.id.end_date);
            this.endTime = itemView.findViewById(R.id.end_time);
            this.btnDelete = itemView.findViewById(R.id.btnDelete);
            this.btnStart = itemView.findViewById(R.id.btnStart);
            this.btnScore = itemView.findViewById(R.id.btnScore);
            this.btnEdit = itemView.findViewById(R.id.btnEdit);

        }
    }
}
