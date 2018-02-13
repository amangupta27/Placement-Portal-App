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


public class AcademicDetailsFragment extends Fragment {

    private Button bsubmit;
    private EditText sch10, sch12, board10, board12, per10, per12, year10, year12;
    String schol10, schol12, brd10, brd12, perc10, perc12, yar10, yar12;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.academic, container, false);
        bsubmit = v.findViewById(R.id.submit3);
        sch10 = v.findViewById(R.id.school10);
        board10 = v.findViewById(R.id.board10);
        per10 = v.findViewById(R.id.percent10);
        year10 = v.findViewById(R.id.year10);
        sch12 = v.findViewById(R.id.school12);
        board12 = v.findViewById(R.id.board12);
        per12 = v.findViewById(R.id.percent12);
        year12 = v.findViewById(R.id.year12);
        if (Utility.us.getRegistration().equals("Admin") &&
                ((Utility.x2.getSchool10() == null) && ((Utility.x2.getSchool12() == null)) && ((Utility.x2.getPercent10() == null))
                        && ((Utility.x2.getPercent12() == null)) && ((Utility.x2.getBoard10() == null)) && ((Utility.x2.getBoard12() == null)) && ((Utility.x2.getPass10() == null)) && ((Utility.x2.getPass12() == null)))
                )
        {
            sch10.setEnabled(false);
            sch12.setEnabled(false);
            per10.setEnabled(false);
            per12.setEnabled(false);
            board10.setEnabled(false);
            board12.setEnabled(false);
            year10.setEnabled(false);
            year12.setEnabled(false);
            bsubmit.setVisibility(View.GONE);

        }

            if (!(Utility.x2.getSchool10() == null) && (!(Utility.x2.getSchool12() == null)) && (!(Utility.x2.getPercent10() == null)) && (!(Utility.x2.getPercent12() == null)) && (!(Utility.x2.getBoard10() == null)) && (!(Utility.x2.getBoard12() == null)) && (!(Utility.x2.getPass10() == null)) && (!(Utility.x2.getPass12() == null))) {
                sch10.setText(Utility.x2.getSchool10());
                sch10.setEnabled(false);
                sch12.setText(Utility.x2.getSchool12());
                sch12.setEnabled(false);
                per10.setText(Utility.x2.getPercent10());
                per10.setEnabled(false);
                per12.setText(Utility.x2.getPercent12());
                per12.setEnabled(false);
                board10.setText(Utility.x2.getBoard10());
                board10.setEnabled(false);
                board12.setText(Utility.x2.getBoard12());
                board12.setEnabled(false);
                year10.setText(Utility.x2.getPass10());
                year10.setEnabled(false);
                year12.setText(Utility.x2.getPass12());
                year12.setEnabled(false);
                bsubmit.setVisibility(View.GONE);

            }
      /*  if(!Utility.x2.getSchool12().isEmpty())
        {
            sch12.setText(Utility.x2.getSchool12());
            sch12.setEnabled(false);
        }
        if(!Utility.x2.getPercent10().isEmpty())
        {
            per10.setText(Utility.x2.getPercent10());
            per10.setEnabled(false);
        }

        if(!Utility.x2.getPercent12().isEmpty())
        {
            per12.setText(Utility.x2.getPercent12());
            per12.setEnabled(false);
        }
        if(!Utility.x2.getBoard10().isEmpty())
        {
            board10.setText(Utility.x2.getBoard10());
            board10.setEnabled(false);
        }
        if(!Utility.x2.getBoard12().isEmpty())
        {
            board12.setText(Utility.x2.getBoard12());
            board12.setEnabled(false);
        }

        if(!Utility.x2.getPercent10().isEmpty())
        {
            per10.setText(Utility.x2.getPercent10());
            per10.setEnabled(false);
        }
        if(!Utility.x2.getPercent12().isEmpty())
        {
            per12.setText(Utility.x2.getPercent12());
            per12.setEnabled(false);
        }
 */
        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });


        return v;
    }

    private void submit() {

        schol10 = sch10.getText().toString();
        schol12 = sch12.getText().toString();
        brd10 = board10.getText().toString();
        brd12 = board12.getText().toString();
        perc10 = per10.getText().toString();
        perc12 = per12.getText().toString();
        yar10 = year10.getText().toString();
        yar12 = year12.getText().toString();

        if ((!schol10.isEmpty()) && (!schol12.isEmpty()) && (!brd10.isEmpty()) && (!brd12.isEmpty()) && (!perc10.isEmpty()) && (!perc12.isEmpty()) && (!yar10.isEmpty()) && (!yar12.isEmpty())) {
            Utility.x2.setSchool10(schol10);
            Utility.x2.setSchool12(schol12);
            Utility.x2.setBoard10(brd10);
            Utility.x2.setBoard12(brd12);
            Utility.x2.setPercent10(perc10);
            Utility.x2.setPercent12(perc12);
            Utility.x2.setPass10(yar10);
            Utility.x2.setPass12(yar12);

            if (Utility.x2.isFilled()) {
                Utility.x2.setStatus("1");
            }

            FirebaseDatabase.getInstance().getReference().child("Users").child(Utility.x2.getRegistration()).setValue(Utility.x2).addOnCompleteListener(
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Sucsessfully submitted", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d("submission academic", "Failed");
                            }
                        }
                    });
        } else {
            Toast.makeText(getContext(), "All Entries are Compulsory", Toast.LENGTH_SHORT).show();
        }

    }


}
