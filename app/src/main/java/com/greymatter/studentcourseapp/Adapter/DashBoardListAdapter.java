package com.greymatter.studentcourseapp.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.R;

import java.util.ArrayList;


public class DashBoardListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<DashBoardList> dashBoardLists;

    public DashBoardListAdapter(Activity activity, ArrayList<DashBoardList> dashBoardLists) {
        this.activity = activity;
        this.dashBoardLists = dashBoardLists;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.dashboard_list, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final DashBoardList dashBoardList = dashBoardLists.get(position);

        holder.course_name.setText(dashBoardList.getCourse());
        holder.description.setText(dashBoardList.getDescription());

    }

    @Override
    public int getItemCount() {
        return dashBoardLists.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView course_name,description;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            description = itemView.findViewById(R.id.description);


        }
    }
}

