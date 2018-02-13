package com.example.shubhama003.placeme.fragments.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;


public class PersonalDetailsFragment extends Fragment {

   private EditText name1,regno1,fname,mname,dob,emailid,phoneno,addcur,addper,country,cour,gend,categ,physchall;
    private Spinner course,gender,category,ph;
    private Button button;
    private String sname,sregno,sfname,smname,sdob,semailid,sphoneno,saddcur,saddper,scountry,scourse,sgender,scategory,sph;
    private View coursev,genderv,categoryv,physchallv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.personal, container, false);
        name1 = v.findViewById(R.id.Name1);
        regno1 = v.findViewById(R.id.Regno2);
        fname = v.findViewById(R.id.Fname);
        mname = v.findViewById(R.id.Mname);
        dob = v.findViewById(R.id.dob);
        emailid = v.findViewById(R.id.Email);
        phoneno = v.findViewById(R.id.phone);
        addcur = v.findViewById(R.id.Curadd);
        addper = v.findViewById(R.id.peradd);
        country = v.findViewById(R.id.country);
        course = v.findViewById(R.id.s1);
        gender = v.findViewById(R.id.s2);
        category = v.findViewById(R.id.s3);
        ph = v.findViewById(R.id.s4);
        cour = v.findViewById(R.id.Course);
        gend=v.findViewById(R.id.gender);
        categ=v.findViewById(R.id.category);
        physchall=v.findViewById(R.id.physchall);
        coursev = v.findViewById(R.id.coursev);
        genderv=v.findViewById(R.id.genderv);
        categoryv=v.findViewById(R.id.categoryv);
        physchallv=v.findViewById(R.id.physchallv);


        button = v.findViewById(R.id.submit2);
        if(!(Utility.x2.getName()==null)){
            name1.setText(Utility.x2.getName());
            name1.setEnabled(false);
        }
        if(Utility.x2.getRegistration()!=null){
            regno1.setText(Utility.x2.getRegistration());
            regno1.setEnabled(false);
        }
        if(Utility.us.getRegistration().equals("Admin") &&(Utility.x2.getFathername()==null)
                    &&(Utility.x2.getMothername()==null)&&(Utility.x2.getDob()==null)&&(Utility.x2.getPhone()==null)&&(Utility.x2.getEmailid()==null)&&(Utility.x2.getAddCurr()==null)&&(Utility.x2.getAddPerm()==null)&&(Utility.x2.getCountry()==null)&&(Utility.x2.getCourse()==null)&&(Utility.x2.getGender()==null)&&(Utility.x2.getCategory()==null)&&(Utility.x2.getPhyschall()==null)){
            fname.setEnabled(false);
            mname.setEnabled(false);
            dob.setEnabled(false);
            phoneno.setEnabled(false);
            emailid.setEnabled(false);
            addcur.setEnabled(false);
            addper.setEnabled(false);
            country.setEnabled(false);
            course.setVisibility(View.GONE);
            coursev.setVisibility(View.GONE);
            cour.setVisibility(View.VISIBLE);
            categ.setVisibility(View.VISIBLE);
            cour.setText("NA");
            cour.setEnabled(false);
            gender.setVisibility(View.GONE);
            genderv.setVisibility(View.GONE);
            gend.setVisibility(View.VISIBLE);
            gend.setText("NA");
            gend.setEnabled(false);
            category.setVisibility(View.GONE);
            categoryv.setVisibility(View.GONE);
            categ.setText("NA");
            categ.setEnabled(false);
            ph.setVisibility(View.GONE);
            physchallv.setVisibility(View.GONE);
            physchall.setVisibility(View.VISIBLE);
            physchall.setText("NA");
            physchall.setEnabled(false);
            button.setVisibility(View.GONE);
        }
        if(!(Utility.x2.getName()==null)&&(Utility.x2.getRegistration()!=null)&&(Utility.x2.getFathername()!=null)&&(Utility.x2.getMothername()!=null)&&(Utility.x2.getDob()!=null)&&(Utility.x2.getPhone()!=null)&&(Utility.x2.getEmailid()!=null)&&(Utility.x2.getAddCurr()!=null)&&(Utility.x2.getAddPerm()!=null)&&(Utility.x2.getCountry()!=null)&&(Utility.x2.getCourse()!=null)&&(Utility.x2.getGender()!=null)&&(Utility.x2.getCategory()!=null)&&(Utility.x2.getPhyschall()!=null)) {

            fname.setText(Utility.x2.getFathername());
            fname.setEnabled(false);
            mname.setText(Utility.x2.getMothername());
            mname.setEnabled(false);
            dob.setText(Utility.x2.getDob());
            dob.setEnabled(false);
            phoneno.setText(Utility.x2.getPhone());
            phoneno.setEnabled(false);
            emailid.setText(Utility.x2.getEmailid());
            emailid.setEnabled(false);
            addcur.setText(Utility.x2.getAddCurr());
            addcur.setEnabled(false);
            addper.setText(Utility.x2.getAddPerm());
            addper.setEnabled(false);
            country.setText(Utility.x2.getCountry());
            country.setEnabled(false);
            course.setVisibility(View.GONE);
            coursev.setVisibility(View.GONE);
            cour.setVisibility(View.VISIBLE);
            cour.setText(Utility.x2.getCourse());
            cour.setEnabled(false);
            gender.setVisibility(View.GONE);
            genderv.setVisibility(View.GONE);
            gend.setVisibility(View.VISIBLE);
            gend.setText(Utility.x2.getGender());
            gend.setEnabled(false);
            category.setVisibility(View.GONE);
            categoryv.setVisibility(View.GONE);
            categ.setVisibility(View.VISIBLE);
            categ.setText(Utility.x2.getCategory());
            categ.setEnabled(false);
            ph.setVisibility(View.GONE);
            physchallv.setVisibility(View.GONE);
            physchall.setVisibility(View.VISIBLE);
            physchall.setText(Utility.x2.getPhyschall());
            physchall.setEnabled(false);
            button.setVisibility(View.GONE);
        }

        course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                scourse = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                sgender = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                scategory = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ph.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=0)
                sph = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
        return v;
    }


     private void submit()
     {
         sname = name1.getText().toString();
         sregno = regno1.getText().toString();
         sfname = fname.getText().toString();
         smname = mname.getText().toString();
         sdob = dob.getText().toString();
         semailid = emailid.getText().toString();
         sphoneno = phoneno.getText().toString();
         saddcur = addcur.getText().toString();
         saddper = addper.getText().toString();
         scountry = country.getText().toString();


         if((!sname.isEmpty())&&(!sregno.isEmpty())&&(!sfname.isEmpty())&&(!smname.isEmpty())&&(!sfname.isEmpty())&&(!sdob.isEmpty())&&(!semailid.isEmpty())&&(!sphoneno.isEmpty())&&(!saddcur.isEmpty())&&(!saddper.isEmpty())&&(!scountry.isEmpty()))
         {
             Utility.x2.setAddCurr(saddcur);
             Utility.x2.setAddPerm(saddper);
             Utility.x2.setFathername(sfname);
             Utility.x2.setMothername(smname);
             Utility.x2.setCountry(scountry);
             Utility.x2.setDob(sdob);
             Utility.x2.setEmailid(semailid);
             Utility.x2.setPhone(sphoneno);



             if(Utility.x2.getPhyschall()==null)
                 Utility.x2.setPhyschall(sph);
             if(Utility.x2.getCourse()==null)
                 Utility.x2.setGender(sgender);
             if(Utility.x2.getCourse()==null)
                 Utility.x2.setCourse(scourse);
             if(Utility.x2.getCategory()==null)
                 Utility.x2.setCategory(scategory);

             if(Utility.x2.isFilled())
             {
                 Utility.x2.setStatus("1");
             }

             FirebaseDatabase.getInstance().getReference().child("Users").child(sregno).setValue(Utility.x2).addOnCompleteListener(
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
