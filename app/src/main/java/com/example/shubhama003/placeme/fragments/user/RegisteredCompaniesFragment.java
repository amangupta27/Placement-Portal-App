package com.example.shubhama003.placeme.fragments.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.example.shubhama003.placeme.adapters.RegisteredCompaniesAdapter;

public class RegisteredCompaniesFragment extends Fragment {

    public RegisteredCompaniesFragment() {
        // Required empty public constructor
    }
    private RecyclerView recyclerView;
    private RegisteredCompaniesAdapter adapter;
    private TextView unRegistered;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_rc, container, false);
        unRegistered = (TextView) v.findViewById(R.id.regFirst);
        if(Utility.us.getRegistered() != null) {
            unRegistered.setVisibility(View.GONE);
            adapter = new RegisteredCompaniesAdapter(getContext());
            recyclerView = v.findViewById(R.id.recycler2);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
        else{
            unRegistered.setVisibility(View.VISIBLE);
        }
        return v;
    }
}
