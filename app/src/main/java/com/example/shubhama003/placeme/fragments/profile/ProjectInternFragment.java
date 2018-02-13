package com.example.shubhama003.placeme.fragments.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;


public class ProjectInternFragment extends Fragment {

   private EditText ptitle,pdescription,ititle,idescription;
    private String prtitle,prdescription,intitle,indescription;
    private Button subf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.project_intern, container, false);
        ptitle = v.findViewById(R.id.projecttitle);
        pdescription = v.findViewById(R.id.projectdescription);
        ititle = v.findViewById(R.id.interntitle);
        idescription = v.findViewById(R.id.interndescription);
        subf = v.findViewById(R.id.submit5);


        if(Utility.us.getRegistration().equals("Admin") &&
                (((Utility.x2.getPtitle()==null)&&((Utility.x2.getItitle()==null))&&((Utility.x2.getIdesc()==null))&&((Utility.x2.getPdesc()==null))))){

            ptitle.setEnabled(false);
            ititle.setEnabled(false);
            pdescription.setEnabled(false);
            idescription.setEnabled(false);
            subf.setVisibility(View.GONE);
        }
        if(!(Utility.x2.getPtitle()==null)&&(!(Utility.x2.getItitle()==null))&&(!(Utility.x2.getIdesc()==null))&&(!(Utility.x2.getPdesc()==null)))
        {
            ptitle.setText(Utility.x2.getPtitle());
            ptitle.setEnabled(false);
            ititle.setText(Utility.x2.getItitle());
            ititle.setEnabled(false);
            pdescription.setText(Utility.x2.getPdesc());
            pdescription.setEnabled(false);
            idescription.setText(Utility.x2.getIdesc());
            idescription.setEnabled(false);
            subf.setVisibility(View.GONE);
        }
        else
        {
            ptitle.setText(Utility.x2.getPtitle());
            ititle.setText(Utility.x2.getItitle());
            pdescription.setText(Utility.x2.getPdesc());
            idescription.setText(Utility.x2.getIdesc());
        }


        subf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qwerty();
            }
        });
        return  v;
    }

    private void qwerty() {
        prtitle = ptitle.getText().toString();
        prdescription = pdescription.getText().toString();
        intitle = ititle.getText().toString();
        indescription = idescription.getText().toString();

        if(!prtitle.isEmpty()&&(!prdescription.isEmpty())&&(!intitle.isEmpty())&&(!indescription.isEmpty()))
        {
            Utility.x2.setPtitle(prtitle);
            Utility.x2.setPdesc(prdescription);
            Utility.x2.setItitle(intitle);
            Utility.x2.setIdesc(indescription);
            if(Utility.x2.isFilled())
            {
                Utility.x2.setStatus("1");
            }
            FirebaseDatabase.getInstance().getReference().child("Users").child(Utility.x2.getRegistration()).setValue(Utility.x2).addOnCompleteListener(
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(),"Sucsessfully submitted",Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("submission personal","Failed");
                            }
                        }
                    });


        }
        else
        {
            Toast.makeText(getContext(),"All Entries are Compulsory",Toast.LENGTH_SHORT).show();
        }
    }


}
