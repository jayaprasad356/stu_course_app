package com.greymatter.studentcourseapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.greymatter.studentcourseapp.Model.DashBoardList;
import com.greymatter.studentcourseapp.R;


public class DashBoardListAdapter extends FirebaseRecyclerAdapter<DashBoardList, DashBoardListAdapter.ExploreItemHolder> {


    public DashBoardListAdapter(FirebaseRecyclerOptions<DashBoardList> options) {
        super(options);

    }

    @NonNull
    @Override
    public ExploreItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_list, parent, false);
        return new ExploreItemHolder(view);
    }


//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
//        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
//        final DashBoardList dashBoardList = dashBoardLists.get(position);
//
//        holder.course_name.setText(dashBoardList.getCourse());
//        holder.description.setText(dashBoardList.getDescription());
//
//    }


    @Override
    protected void onBindViewHolder(@NonNull ExploreItemHolder holder, int position, @NonNull DashBoardList model) {


        holder.course_name.setText(model.getFirstName());
        holder.description.setText(model.getLastName());

    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView course_name, description;

        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            course_name = itemView.findViewById(R.id.course_name);
            description = itemView.findViewById(R.id.description);


        }
    }
}

