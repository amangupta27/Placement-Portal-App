package com.example.shubhama003.placeme.fragments.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.example.shubhama003.placeme.utility.Openings;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class HomeFragment extends Fragment {

    private ImageView photo;
    private TextView name,status;
    private TextView regno;
    private ImageView tick;
    private TextView branch;
    private TextView credits;
    private TextView cpi;
    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        tick = (ImageView)v.findViewById(R.id.tick);
        photo = (ImageView) v.findViewById(R.id.p_photo);
        name = (TextView) v.findViewById(R.id.p_name);
        regno = (TextView) v.findViewById(R.id.p_regno);
        branch = (TextView) v.findViewById(R.id.p_branch);
        credits = (TextView) v.findViewById(R.id.credits);
        status = v.findViewById(R.id.status);
        cpi = (TextView) v.findViewById(R.id.cpis);
        if((Utility.us.getUrl()!=null) && (!Utility.us.getUrl().equals("")) ){
            Picasso.with(getContext())
                    .load(Utility.us.getUrl())
                    .placeholder(R.drawable.ic_menu_camera21)
                    .resize(250,250)
                    .centerCrop()
                    .transform(new CropCircleTransformation())
                    .into(photo);
        }
        credits.setText(Utility.us.getCredits());
        if(Utility.us.getVerified()!=null && Utility.us.getVerified().equals("1")&&!Utility.us.getVerified().isEmpty())
        {
            tick.setVisibility(View.VISIBLE);
        }
        name.setText(Utility.us.getName());
        regno.setText(Utility.us.getRegistration());
        branch.setText(Utility.us.getBranch());
        cpi.setText(Utility.us.getCPI());
        if(Utility.us.getCname()!=null && !Utility.us.getStatus().isEmpty()){
            if(Utility.us.getCname()!=null && Openings.Search(Utility.us.getCname())!=null) {

                String s = "Congratulations You are placed in " + Utility.us.getCname() +
                        " at " + Openings.Search(Utility.us.getCname()).getSalary() + ".";
                status.setText(s);
            }
        }
        //new DownloadTask(getActivity(), Utility.us.getResurl());
        return v;
    }

}
