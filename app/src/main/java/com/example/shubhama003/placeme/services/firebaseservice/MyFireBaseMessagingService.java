package com.example.shubhama003.placeme.services.firebaseservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.activities.Main2Activity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by shubhama003 on 27/9/17.
 */

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "Myfirebase";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        NotificationCompat.Builder not = new NotificationCompat.Builder(this);
        not.setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("This is Ticker")
                .setContentTitle("Place Me")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());

        Intent intent  = new Intent(this, Main2Activity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        not.setContentIntent(pi);
        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0,not.build());
    }

    }

