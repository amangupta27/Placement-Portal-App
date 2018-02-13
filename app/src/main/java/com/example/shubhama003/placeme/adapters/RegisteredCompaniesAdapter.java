package com.example.shubhama003.placeme.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.example.shubhama003.placeme.utility.Registration;
import com.example.shubhama003.placeme.utility.Openings;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


/**
 * Created by shubhama003 on 29/8/17.
 */

public class RegisteredCompaniesAdapter extends RecyclerView.Adapter<RegisteredCompaniesAdapter.MyViewHolder> {

    private Context mcontext;
    public List<Openings> list = Utility.rc;

    public RegisteredCompaniesAdapter(Context context) {
        mcontext = context;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tname, tstipend, tbranch, tlocation, tcpi, deadLine, dtime;
        public Button visit;
        public ImageButton button;
        public ImageView logo;
        public android.support.v7.widget.CardView ll;
        public boolean expanded = false;

        public MyViewHolder(View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.compname2);
            tstipend = itemView.findViewById(R.id.compstpend2);
            tbranch = itemView.findViewById(R.id.compbranch2);
            button = itemView.findViewById(R.id.button22);
            ll = itemView.findViewById(R.id.hide2);
            logo = itemView.findViewById(R.id.compimage2);
            tlocation = itemView.findViewById(R.id.location2);
            tcpi = itemView.findViewById(R.id.cpi2);
            deadLine = itemView.findViewById(R.id.deadline2);
            visit = itemView.findViewById(R.id.visit2);
            dtime = itemView.findViewById(R.id.dtime);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.registeredop, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Openings openings = list.get(position);

        holder.tname.setText(openings.getCompanyName());
        holder.tcpi.setText("CPI Cutoff: " + openings.getEligibility());
        holder.tlocation.setText("Location: " + openings.getLocation());
        holder.deadLine.setText("Deadline: " + openings.getDeadline());

        holder.dtime.setText(Registration.search(Utility.us.getRegistered(), openings.getCompanyName()).getDateTime());

        String ans = "";
        if (!openings.getLogoUrl().equals("")) {
            Picasso.with(mcontext)
                    .load(openings.getLogoUrl())
                    .placeholder(R.drawable.logomnnit)
                    .resize(100, 100)
                    .centerInside()
                    .transform(new CropCircleTransformation())
                    .into(holder.logo);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.expanded) {
                    holder.ll.setVisibility(View.GONE);
                    holder.expanded = false;
                    holder.button.setBackgroundResource(R.drawable.down_arrow);
                } else {
                    holder.ll.setVisibility(View.VISIBLE);
                    holder.expanded = true;
                    holder.button.setBackgroundResource(R.drawable.up_arrow);
                }
            }
        });
        for (String s : openings.getBranches()) {
            ans += ", " + s;
        }
        ans = ans.substring(2);

        holder.tbranch.setText("Branches Allowed: " + ans);
        holder.tstipend.setText("Salary :" + openings.getSalary());
        holder.visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse(openings.getWebsite()));
                mcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}