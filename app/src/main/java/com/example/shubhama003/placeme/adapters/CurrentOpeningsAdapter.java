package com.example.shubhama003.placeme.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Registration;
import com.example.shubhama003.placeme.utility.Openings;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import com.example.shubhama003.placeme.utility.Utility;

/**
 * Created by shubhama003 on 29/8/17.
 */

public class CurrentOpeningsAdapter extends RecyclerView.Adapter<CurrentOpeningsAdapter.MyViewHolder> {

    private Context mcontext;
    ArrayList<Registration> li = (ArrayList<Registration>) Utility.us.getRegistered();

    public CurrentOpeningsAdapter(Context context) {
        mcontext = context;
        if (li == null) {
            li = new ArrayList<>();
        }
    }

    public List<Openings> List = Utility.op;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tname, tstipend, tbranch, tlocation, tcpi, deadLine;
        public Button register, visit;
        public ImageButton button;
        public ImageView logo;
        public android.support.v7.widget.CardView ll;
        public boolean expanded = false;

        public MyViewHolder(View itemView) {
            super(itemView);
            register = itemView.findViewById(R.id.register);
            tname = itemView.findViewById(R.id.compname);
            tstipend = itemView.findViewById(R.id.compstpend);
            tbranch = itemView.findViewById(R.id.compbranch);
            button = itemView.findViewById(R.id.button2);
            ll = itemView.findViewById(R.id.hide);
            logo = itemView.findViewById(R.id.compimage);
            tlocation = itemView.findViewById(R.id.location);
            tcpi = itemView.findViewById(R.id.cpi);
            deadLine = itemView.findViewById(R.id.deadline);
            visit = itemView.findViewById(R.id.visit);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.openingelement, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Openings openings = List.get(position);
        holder.tname.setText(openings.getCompanyName());
        holder.tcpi.setText("CPI Cutoff: " + openings.getEligibility());
        holder.tlocation.setText("Location: " + openings.getLocation());
        holder.deadLine.setText("Deadline: " + openings.getDeadline());
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
        holder.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (li != null && Registration.getCompanyList(li).contains(openings.getCompanyName())) {
                    Toast.makeText(mcontext, "You are already registered for this company", Toast.LENGTH_SHORT).show();
                } else {
                    if (openings.isEligible()) {
                        try {
                            if (!openings.inTime(openings.getDeadline())) {
                                Toast.makeText(mcontext, "You missed the deadline", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View mview = inflater.inflate(R.layout.prompts, null);

                        builder.setView(mview);
                        final AlertDialog dialog = builder.create();
                        dialog.show();
                        final EditText regno1 = (EditText) mview.findViewById(R.id.DRNo);
                        final EditText pass1 = (EditText) mview.findViewById(R.id.Dpassword);
                        Button regint = (Button) mview.findViewById(R.id.regin);
                        Button cAancel = (Button) mview.findViewById(R.id.cancel);
                        regint.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String s1 = regno1.getText().toString();
                                String s2 = pass1.getText().toString();
                                if (s1.equals(Utility.us.getRegistration()) && s2.equals(Utility.us.getPassword())) {
                                    Registration reg = new Registration(openings.getCompanyName(), DateFormat.getDateTimeInstance().format(new Date()));
                                    li.add(reg);
                                    Utility.us.setRegistered(li);
                                    Utility.rc.add(openings);
                                    Toast.makeText(mcontext, "You are successfully registered", Toast.LENGTH_SHORT).show();
                                    FirebaseDatabase.getInstance().getReference()
                                            .child("Users").child(Utility.us.getRegistration()).child("registered")
                                            .setValue(Utility.us.getRegistered());
                                } else {
                                    Toast.makeText(mcontext, "Incorrect RegNo or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        cAancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });


                    } else {
                        Toast.makeText(mcontext, "You are not eligible for this company", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }
}