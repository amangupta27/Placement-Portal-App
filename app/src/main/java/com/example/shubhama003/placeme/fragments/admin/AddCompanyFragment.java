package com.example.shubhama003.placeme.fragments.admin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Openings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubhama003 on 11/10/17.
 */

public class AddCompanyFragment extends Fragment {

    EditText cname,cloc,csal,ccpi,cdeadline,clogo,curl,ppo,profile;
    CheckBox ccs,cit,cece,cele,cmech,cciv,cpie,cbio,cche;
    Button sb;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_opening, container, false);
        cname = v.findViewById(R.id.add_compname);
        cloc = v.findViewById(R.id.add_location);
        csal = v.findViewById(R.id.add_salary);
        ccpi = v.findViewById(R.id.add_cpi);
        cdeadline = v.findViewById(R.id.add_deadline);
        clogo = v.findViewById(R.id.add_logourl);
        curl = v.findViewById(R.id.add_url);
        ppo = v.findViewById(R.id.add_ppo);
        sb = v.findViewById(R.id.add_submit_op);
        ccs = v.findViewById(R.id.Cs);
        cit = v.findViewById(R.id.It);
        profile = v.findViewById(R.id.add_profile);
        cece = v.findViewById(R.id.Ece);
        cele = v.findViewById(R.id.Ele);
        cmech = v.findViewById(R.id.Mech);
        cciv = v.findViewById(R.id.Civ);
        cpie = v.findViewById(R.id.Prod);
        cbio = v.findViewById(R.id.Bio);
        cche = v.findViewById(R.id.Che);
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subs();
            }
        });
        return v;
    }

    private void subs() {

        Log.d("qwe","Chala");

        if(ccs.isChecked()||cit.isChecked()||cece.isChecked()||cele.isChecked()||cmech.isChecked()||cciv.isChecked()||cpie.isChecked()||cbio.isChecked()||cche.isChecked())
        {
            Log.d("qwe","if");
            Openings opium = new Openings();
            List<String> al = new ArrayList<>();
            if(ccs.isChecked())
                al.add(ccs.getText().toString());
            if(cit.isChecked())
                al.add(cit.getText().toString());
            if(cece.isChecked())
                al.add(cece.getText().toString());
            if(cele.isChecked())
                al.add(cele.getText().toString());
            if(cmech.isChecked())
                al.add(cmech.getText().toString());
            if(cciv.isChecked())
            al.add(cciv.getText().toString());
            if(cbio.isChecked())
                al.add(cbio.getText().toString());
            if(cche.isChecked())
                al.add(cche.getText().toString());
            if(cpie.isChecked())
                al.add(cpie.getText().toString());
            opium.setBranches(al);
            opium.setCompanyName(cname.getText().toString());
            opium.setDeadline(cdeadline.getText().toString());
            opium.setEligibility(ccpi.getText().toString());
            opium.setLocation(cloc.getText().toString());
            opium.setLogoUrl(clogo.getText().toString());
            opium.setPPO(ppo.getText().toString());
            opium.setProfile(profile.getText().toString());
            opium.setSalary(csal.getText().toString());
            opium.setWebsite(curl.getText().toString());


            if((!cname.equals(""))&&(!cdeadline.equals(""))&&(!ccpi.equals(""))&&(!cloc.equals(""))
                    &&(!ppo.equals(""))&&(!profile.equals(""))&&(!csal.equals(""))&&(!curl.equals(""))){
                FirebaseDatabase.getInstance().getReference().child("openings").child(opium.getCompanyName()).setValue(opium).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(getContext(),"Not success",Toast.LENGTH_SHORT).show();


                    }
                });
            }
        }
        else
        {
            Log.d("qwe","else");
        }
    }
}
