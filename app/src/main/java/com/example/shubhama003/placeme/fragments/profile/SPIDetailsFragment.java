package com.example.shubhama003.placeme.fragments.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;

import java.util.ArrayList;


public class SPIDetailsFragment extends Fragment {

    TextView t1,t2,t3,t4,t5,t6,t7,t8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_b_tech, container, false);

        t1 = v.findViewById(R.id.sp1);
        t2 = v.findViewById(R.id.sp2);
        t3 = v.findViewById(R.id.sp3);
        t4 = v.findViewById(R.id.sp4);t5 = v.findViewById(R.id.sp5);
        t6 = v.findViewById(R.id.sp6);
        t7 = v.findViewById(R.id.sp7);
        t8 = v.findViewById(R.id.sp8);

        ArrayList<String> ar = Utility.x2.getBtech();
        t1.setText(ar.get(0));
        t2.setText(ar.get(1));
        t3.setText(ar.get(2));
        t4.setText(ar.get(3));
        t5.setText(ar.get(4));
        t6.setText(ar.get(5));
        t7.setText(ar.get(6));
        t8.setText(ar.get(7));

        return v;
    }

}
