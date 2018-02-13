package com.example.shubhama003.placeme.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;


public class PasswordChangeFragment extends Fragment {

    EditText cpass,npass,rpass;
    Button submit;
    TextView complete;
    LinearLayout ll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_password, container, false);
        cpass = v.findViewById(R.id.cpasswordy);
        npass = v.findViewById(R.id.npasswordy);
        complete = v.findViewById(R.id.pcomplete);
        ll = v.findViewById(R.id.linear);
        rpass  = v.findViewById(R.id.rpasswordy);
        submit = v.findViewById(R.id.submitit);

        if(Utility.us.getRegistration().equals("Admin")||Utility.us.getStatus().equals("1"))
        {
            complete.setVisibility(View.GONE);
            ll.setVisibility(View.VISIBLE);
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cpass.getText().toString().equals(Utility.us.getPassword()))
                {
                    if((npass.getText().toString().equals(rpass.getText().toString()))&&(!npass.getText().toString().equals("")))
                    {
                        FirebaseDatabase.getInstance().getReference().child("Users").child(Utility.us.getRegistration()).child("PasswordChangeFragment").setValue(rpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getContext(),"Password Change Succesful",Toast.LENGTH_SHORT).show();
                                }
                                else {

                                    Toast.makeText(getContext(),"Failed",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                    }
                }

            }
        });
        return v;
    }

}
