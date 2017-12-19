package com.example.harisanker.generalcomplaints;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by harshitha on 19/12/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private ArrayList<CommentObj> mDataset;

    public CommentsAdapter(ArrayList<CommentObj> dataset) {
        mDataset = dataset;
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);

        CommentsAdapter.ViewHolder vh = new CommentsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CommentsAdapter.ViewHolder holder, int position) {
        TextView name = (TextView) holder.view.findViewById(R.id.ci_name);
        TextView roomNo = (TextView) holder.view.findViewById(R.id.ci_roomNo);
        TextView date = (TextView) holder.view.findViewById(R.id.ci_date);
        TextView comStr = (TextView) holder.view.findViewById(R.id.ci_comStr);

        final CommentObj commentObj = mDataset.get(position);

        name.setText(commentObj.getName());
        roomNo.setText("Room No: " + commentObj.getRoomNo());
        date.setText(commentObj.getDate());
        comStr.setText(commentObj.getCommentStr());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;


        }
    }
}