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

public class AddPlacedStudentFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private EditText Regno, Comp;
    private Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_placed, container, false);
        Regno = v.findViewById(R.id.add_regno);
        Comp = v.findViewById(R.id.add_comp);
        submit = v.findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Regno.getText().toString().isEmpty() || Comp.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "No fields can be left blank", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(Regno.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User u = dataSnapshot.getValue(User.class);
                            if(u==null){
                                Toast.makeText(getContext(),"No such user",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if(u.getCname()==null || u.getCname().isEmpty()) {
                                Log.d("QA","yes");
                                Utility.placed.set(u.getBranch());
                                Log.d("QA",u.getBranch()+" "+Utility.placed.getCSE());
                                FirebaseDatabase.getInstance().getReference().child("Stat").child("placed").setValue(Utility.placed).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(Regno.getText().toString()).child("cname").setValue(Comp.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getContext(),"User Placed",Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getContext(),"User Already Placed",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        return v;
    }
}

