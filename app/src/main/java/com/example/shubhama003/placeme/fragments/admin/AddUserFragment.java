package com.example.shubhama003.placeme.fragments.admin;

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
import com.example.shubhama003.placeme.utility.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddUserFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private EditText name, regno, cpi, branch, s1, s2, s3, s4, s5, s6, s7, s8;
    private Button submit;
    private User x;
    private String sname, sregno, scpi, sbranch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_add_user, container, false);
        name = v.findViewById(R.id.add_name);
        regno = v.findViewById(R.id.add_regno);
        cpi = v.findViewById(R.id.add_cpi);
        branch = v.findViewById(R.id.add_branch);
        submit = v.findViewById(R.id.add_submit);
        s1 = v.findViewById(R.id.add_sem1);
        s2 = v.findViewById(R.id.add_sem2);
        s3 = v.findViewById(R.id.add_sem3);
        s4 = v.findViewById(R.id.add_sem4);
        s5 = v.findViewById(R.id.add_sem5);
        s6 = v.findViewById(R.id.add_sem6);
        s7 = v.findViewById(R.id.add_sem7);
        s8 = v.findViewById(R.id.add_sem8);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> a = new ArrayList<String>();

              /*  if(!s1.getText().toString().isEmpty()&&!s2.getText().toString().isEmpty()&&!s3.getText().toString().isEmpty()&&!s4.getText().toString().isEmpty()&&!s5.getText().toString().isEmpty()&&!s6.getText().toString().isEmpty()&&!s7.getText().toString().isEmpty()&&!s8.getText().toString().isEmpty()) {
                    a.add(s1.getText().toString());
                    a.add(s2.getText().toString());
                    a.add(s3.getText().toString());
                    a.add(s4.getText().toString());
                    a.add(s5.getText().toString());
                    a.add(s6.getText().toString());
                    a.add(s7.getText().toString());
                    a.add(s8.getText().toString());
                } */
               /* else
                {
                    Toast.makeText(getContext(),"All entries are compulsory",Toast.LENGTH_SHORT).show();
                    return;
                } */

                User user = new User();
                sname = name.getText().toString();
                sregno = regno.getText().toString();
                scpi = cpi.getText().toString();
                sbranch = branch.getText().toString();
                if (!sname.isEmpty() && !sregno.isEmpty() && !scpi.isEmpty() && !sbranch.isEmpty() && !s1.getText().toString().isEmpty() && !s2.getText().toString().isEmpty() && !s3.getText().toString().isEmpty() && !s4.getText().toString().isEmpty() && !s5.getText().toString().isEmpty() && !s6.getText().toString().isEmpty() && !s7.getText().toString().isEmpty() && !s8.getText().toString().isEmpty()) {
                    a.add(s1.getText().toString());
                    a.add(s2.getText().toString());
                    a.add(s3.getText().toString());
                    a.add(s4.getText().toString());
                    a.add(s5.getText().toString());
                    a.add(s6.getText().toString());
                    a.add(s7.getText().toString());
                    a.add(s8.getText().toString());
                    user.setBtech(a);
                    user.setName(sname);
                    user.setRegistration(sregno);
                    user.setCPI(scpi);
                    user.setBranch(sbranch);
                    user.setPassword("1");
                    user.setCredits("10");
                    user.setStatus("0");
                    FirebaseDatabase.getInstance().getReference().child("Users").child(sregno).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            x = dataSnapshot.getValue(User.class);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    if (x == null) {
                        Utility.total.set(sbranch);
                        FirebaseDatabase.getInstance().getReference().child("Stat").child("total").setValue(Utility.total).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                        FirebaseDatabase.getInstance().getReference().child("Users").child(sregno).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Sucsessfully submitted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.d("submission personal", "Failed");
                                }
                            }
                        });
                    }
                    else{
                            Toast.makeText(getContext(), "User Already Registered", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return v;
    }

}
