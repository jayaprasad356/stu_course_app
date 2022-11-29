package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.content.Intent;
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
import com.google.firebase.database.ValueEventListener;
import com.greymatter.studentcourseapp.Activities.ProfessorActivity;
import com.greymatter.studentcourseapp.Activities.StudentDashboardActivity;
import com.greymatter.studentcourseapp.Activities.TestActivity;
import com.greymatter.studentcourseapp.Constant;
import com.greymatter.studentcourseapp.Model.Course;
import com.greymatter.studentcourseapp.R;
import com.greymatter.studentcourseapp.Session;

import java.util.HashMap;
import java.util.Map;

public class StudentCoursesAdapter extends FirebaseRecyclerAdapter<Course, StudentCoursesAdapter.ViewHolder> {
    Activity activity;
    Session session;

    public StudentCoursesAdapter(FirebaseRecyclerOptions<Course> options, Activity activity) {
        super(options);
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.dashboard_list, parent, false);
        return new ViewHolder(listItem);

    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Course model) {
        session = new Session(activity);
        holder.title.setText("Course Name : "+model.getName());
        holder.description.setText(model.getDescription());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.STUDENT_ENROLL_COURSES_ID).child(model.getCourse_id());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()){
                    holder.btnEnrollNow.setVisibility(View.VISIBLE);


                    holder.btnEnrollNow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            enrollCourse(model.getCourse_id(),model.getName(),model.getDescription());

                        }
                    });

                }else {
                    holder.btnEnrollNow.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    private void enrollCourse(String course_id, String name, String description) {
        String currentTime = System.currentTimeMillis()/1000 + "";
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(Constant.STUDENT_ENROLL_COURSES).child(currentTime);
        Map<String, Object> map = new HashMap<>();
        map.put(Constant.EMAIL, session.getData(Constant.EMAIL));
        map.put(Constant.COURSE_ENROLL_ID, currentTime);
        map.put(Constant.COURSE_ID, course_id);
        map.put(Constant.NAME, name);
        map.put(Constant.DESCRIPTION, description);
        databaseReference.updateChildren(map);
        DatabaseReference dR2 = FirebaseDatabase.getInstance().getReference().child(Constant.STUDENT_ENROLL_COURSES_ID).child(course_id);
        dR2.updateChildren(map);
        Intent intent = new Intent(activity, StudentDashboardActivity.class);
        activity.startActivity(intent);
        Toast.makeText(activity, "Course  Enroll Successfully", Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public Button btnEnrollNow;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.course_name);
            this.description = itemView.findViewById(R.id.description);
            this.btnEnrollNow = itemView.findViewById(R.id.btnEnrollNow);

        }
    }
}
