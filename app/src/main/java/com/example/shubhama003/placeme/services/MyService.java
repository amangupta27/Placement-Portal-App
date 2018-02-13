package com.example.shubhama003.placeme.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.activities.LoginActivity;
import com.example.shubhama003.placeme.activities.Main2Activity;
import com.example.shubhama003.placeme.utility.Openings;
import com.example.shubhama003.placeme.utility.User;
import com.example.shubhama003.placeme.utility.Utility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Created by shubhama003 on 12/10/17.
 */

public class MyService extends Service {
    DatabaseReference ref;
    public static User x;
    private Openings op_e;
    public static ArrayList<Openings> op;
    private ArrayList<Openings>sop;
    @Override
    public void onCreate(){
        Log.d("QA","x1");
        op = new ArrayList<>();
        sop = new ArrayList<>();
        op = Utility.op;
        ref = FirebaseDatabase.getInstance().getReference();
    }
    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("QA","x2");
        ref.child("openings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("QA","x3");
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren()
                        .iterator();
                Toast.makeText(getApplicationContext(),"q",Toast.LENGTH_SHORT).show();

                sop.clear();

                while (dataSnapshots.hasNext()) {
                    Log.d("QA","x4");
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    Openings openings = dataSnapshotChild.getValue(Openings.class);
                    if(!op.contains(openings)){
                        op_e = openings;
                    }
                    sop.add(openings);
                }
                Log.d("qa",String.valueOf(sop.size()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(sop.size() > op.size()){
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.drawable.check);
            mBuilder.setContentTitle("new company added");
            mBuilder.setContentText(op_e.getCompanyName());
            Intent resultIntent = new Intent(this, Main2Activity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(Main2Activity.class);

// Adds the Intent that starts the Activity to the top of the stack
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
            mNotificationManager.notify(0, mBuilder.build());
            op=sop;
        }
        Log.d("QA","x5");
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    @Override
    public void onDestroy(){
        Log.d("QA","TEST");
    }
}
