package com.example.shubhama003.placeme.activities;
//Commented by Aman
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhama003.placeme.services.MyService;
import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Openings;
import com.example.shubhama003.placeme.utility.Registration;
import com.example.shubhama003.placeme.utility.Stat;
import com.example.shubhama003.placeme.utility.User;
import com.example.shubhama003.placeme.utility.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {

    private EditText regNo;
    private EditText password;
    private Button login;
    private SharedPreferences.Editor editor;
    SharedPreferences pref;
    private String s1;
    private String pass;
    private ProgressBar progressBar;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ll = (LinearLayout) findViewById(R.id.ll1);

        FirebaseDatabase.getInstance().getReference().child("stat").child("placed").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Utility.placed = dataSnapshot.getValue(Stat.class);
                if (Utility.placed != null)
                    Utility.placed = Utility.placed.initZero(Utility.placed);
                else
                {Utility.placed = new Stat("0","0","0","0","0","0","0","0","0");
                    //Toast.makeText(LoginActivity.this,"Placed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("stat").child("total").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Utility.total = dataSnapshot.getValue(Stat.class);
                if (Utility.total != null)
                    Utility.total = Utility.total.initZero(Utility.total);
                else
                    Utility.total = new Stat("0","0","0","0","0","0","0","0","0");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Utility.op = new ArrayList<Openings>();
        Utility.rc = new ArrayList<Openings>();
        FirebaseDatabase.getInstance().getReference().child("openings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren()
                        .iterator();
                Utility.op.clear();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    Openings openings = dataSnapshotChild.getValue(Openings.class);
                    try {
                        if(openings!=null && openings.inTime(openings.getDeadline()))
                            Utility.op.add(openings);
                    } catch (ParseException e) {
                        Log.d("QA","a");
                        e.printStackTrace();
                    }
                }
                if (Utility.us != null) {
                    Utility.rc.clear();
                    for (Openings o : Utility.op) {
                        if (Utility.us.getRegistered() != null && Registration.getCompanyList(Utility.us.getRegistered()).contains(o.getCompanyName())) {
                            Utility.rc.add(o);
                        }
                    }
                }
                MyService.op = Utility.op;
                // Toast.makeText(LoginActivity.this, op.get(0).getBranches().get(2), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        regNo = (EditText) findViewById(R.id.RegistNo);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        progressBar.setVisibility(View.INVISIBLE);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        if ((!pref.getString("Regnum", "#").equals("#"))
                && (!pref.getString("Pass", "#").equals("#"))) {
            progressBar.setVisibility(View.VISIBLE);
            login.setEnabled(false);
            login.setText("Please Wait...");
            s1 = pref.getString("Regnum", "#");
            regNo.setText(s1);
            pass = pref.getString("Pass", "#");
            password.setText(pass);
            Log.d("LOGINDETAILS", s1 + " " + pass);


            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if ((activeNetworkInfo != null) && (activeNetworkInfo.isConnected())) {
                //progressBar.setVisibility(View.VISIBLE);
                //s1 = regNo.getText().toString();
                //pass = PasswordChangeFragment.getText().toString();

                login();
            } else
            //Toast.makeText(LoginActivity.this,"No Network Connection",Toast.LENGTH_SHORT).show();
            {
                progressBar.setVisibility(View.GONE);
                login.setText("LOGIN");
                login.setEnabled(true);
                Snackbar snack = Snackbar.make(ll, "No internet Connection", Snackbar.LENGTH_SHORT);
                View qview = snack.getView();
                TextView tv = qview.findViewById(android.support.design.R.id.snackbar_text);
                tv.setTextColor(Color.WHITE);
                snack.show();

            }

            //login();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager
                        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if ((activeNetworkInfo != null) && (activeNetworkInfo.isConnected())) {
                    progressBar.setVisibility(View.VISIBLE);
                    s1 = regNo.getText().toString();
                    pass = password.getText().toString();

                    login();
                } else
                //Toast.makeText(LoginActivity.this,"No Network Connection",Toast.LENGTH_SHORT).show();
                {
                    Snackbar snack = Snackbar.make(ll, "No internet Connection", Snackbar.LENGTH_SHORT);
                    View qview = snack.getView();
                    TextView tv = qview.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    snack.show();
                }
            }
        });

    }

    private void login() {

        //Toast.makeText(LoginActivity.this,"Succesfull"+" "+s1+" "+pass,Toast.LENGTH_SHORT).show();
        FirebaseDatabase.getInstance().getReference().child("Users").child(s1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Utility.us = dataSnapshot.getValue(User.class);

                if (Utility.us == null) {

                    //Toast.makeText(LoginActivity.this,"Wrong Registration Number",Toast.LENGTH_SHORT).show();
                    Snackbar snack = Snackbar.make(ll, "Wrong Registration Number", Snackbar.LENGTH_SHORT);
                    View qview = snack.getView();
                    TextView tv = (TextView) qview.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    snack.show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                Utility.x2 = Utility.us;
                if (pass.equals(Utility.us.getPassword())) {
                    editor = pref.edit();
                    editor.putString("Regnum", s1);
                    editor.putString("Pass", pass);
                    editor.commit();
                    Utility.rc.clear();
                    for (Openings o : Utility.op) {
                        if (Utility.us.getRegistered() != null && Registration.getCompanyList(Utility.us.getRegistered()).contains(o.getCompanyName()))
                            Utility.rc.add(o);
                    }
                    progressBar.setVisibility(View.GONE);

                    Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();


                } else {
                    //Toast.makeText(LoginActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                    Snackbar snack = Snackbar.make(ll, "Wrong Password", Snackbar.LENGTH_SHORT);
                    View qview = snack.getView();
                    TextView tv = qview.findViewById(android.support.design.R.id.snackbar_text);
                    tv.setTextColor(Color.WHITE);
                    snack.show();
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
