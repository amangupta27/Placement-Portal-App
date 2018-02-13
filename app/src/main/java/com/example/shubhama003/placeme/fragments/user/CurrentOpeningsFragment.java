package com.example.shubhama003.placeme.fragments.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.adapters.CurrentOpeningsAdapter;
import com.example.shubhama003.placeme.utility.Openings;

import java.util.List;

public class CurrentOpeningsFragment extends Fragment {

    public CurrentOpeningsFragment() {

    }
    private RecyclerView recyclerView;
    private List<Openings> list;
    private CurrentOpeningsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_co, container, false);
        adapter = new CurrentOpeningsAdapter(getContext());
        recyclerView = v.findViewById(R.id.recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return v;
    }

}