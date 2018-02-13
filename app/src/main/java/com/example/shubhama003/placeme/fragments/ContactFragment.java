package com.example.shubhama003.placeme.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shubhama003.placeme.R;

public class ContactFragment extends Fragment {

    Button b1,b2,b3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_contact, container, false);
        b1 = v.findViewById(R.id.call1);
        b2 = v.findViewById(R.id.call2);
        b3 = v.findViewById(R.id.mailme);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:9161784660"));
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:7233081023"));
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mailto:"));
                        String to[] = {"shubhama003@gmail.com", "amrendrajha65@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL, to);
                        intent.putExtra(Intent.EXTRA_SUBJECT, "Regarding Placements");
                        intent.setType("message/rfc822");
                        Intent chooser = Intent.createChooser(intent, "Send Mail");
                        startActivity(chooser);
            }
        });

        return v;
    }

}
