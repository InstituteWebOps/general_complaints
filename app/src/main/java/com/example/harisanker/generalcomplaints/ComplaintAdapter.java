package com.example.harisanker.generalcomplaints;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by harisanker on 22/6/17.
 */

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {
    private List<Complaints> mDataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ViewHolder(View v) {
            super(v);
            view = v;
        }
    }

    public ComplaintAdapter(List<Complaints> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ComplaintAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTextView.setText(mDataset[position]);
        TextView name = (TextView) holder.view.findViewById(R.id.tv_name);
        TextView hostel = (TextView) holder.view.findViewById(R.id.tv_hostel);
        TextView resolved = (TextView) holder.view.findViewById(R.id.tv_is_resolved);
        TextView title = (TextView) holder.view.findViewById(R.id.tv_title);
        TextView subject = (TextView) holder.view.findViewById(R.id.tv_subject);
        TextView description = (TextView) holder.view.findViewById(R.id.tv_description);


        name.setText(mDataset.get(position).name);
        hostel.setText(mDataset.get(position).hostel);
        resolved.setText(mDataset.get(position).resolved?"Resolved":"Unresolved");
        title.setText(mDataset.get(position).title);
        subject.setText(mDataset.get(position).subject);
        description.setText(mDataset.get(position).description);






    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

