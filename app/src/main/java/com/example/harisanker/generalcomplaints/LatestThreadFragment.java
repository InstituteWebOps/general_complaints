package com.example.harisanker.generalcomplaints;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class LatestThreadFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public LatestThreadFragment() {
        // Required empty public constructor
    }
    List <Complaints> compalintList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        compalintList.add(new Complaints("Godav",
                "Heisenberg",
                "BLAH",
                "Random",
                "Title",
                "Subject",
                null,
                "Description",
                null,
                25,
                1,
                1,
                true,
                null));
        compalintList.add(new Complaints("Cauvery",
                "Javier",
                "BLAH",
                "Random",
                "Random",
                "Fugazi",
                null,
                "Stuff",
                null,
                25,
                1,
                1,
                false,
                null));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_latest_thread, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.general_complaint_recycler);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ComplaintAdapter(compalintList);
        mRecyclerView.setAdapter(mAdapter);


        return view;
    }
}
