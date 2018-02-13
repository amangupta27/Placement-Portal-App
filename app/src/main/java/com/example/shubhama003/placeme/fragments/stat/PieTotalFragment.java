package com.example.shubhama003.placeme.fragments.stat;

/**
 * Created by shubhama003 on 13/10/17.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieTotalFragment extends Fragment {
    Float bt1,civ1,ee1,me1,cs1,ece1,pie1,it1,che1,bt2,civ2,ee2,me2,cs2,ece2,pie2,it2,che2;
    LinearLayout ll;
    PieChart pc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pie_total, container, false);
        pc = v.findViewById(R.id.piechart);

        bt1 = Float.valueOf(Utility.total.getBT());
        civ1 = Float.valueOf(Utility.total.getCIV());
        ee1 = Float.valueOf(Utility.total.getEE());
        me1 = Float.valueOf(Utility.total.getME());
        cs1 = Float.valueOf(Utility.total.getCSE());
        ece1 = Float.valueOf(Utility.total.getECE());
        pie1 = Float.valueOf(Utility.total.getPIE());
        it1 = Float.valueOf(Utility.total.getIT());
        che1 = Float.valueOf(Utility.total.getCHE());


        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(civ1,"CE"));
        entries.add(new PieEntry(ee1,"EE"));
        entries.add(new PieEntry(me1,"ME"));
        entries.add(new PieEntry(che1,"CHE"));
        entries.add(new PieEntry(cs1,"CSE"));
        entries.add(new PieEntry(bt1,"BioTech"));
        entries.add(new PieEntry(ece1,"ECE"));
        entries.add(new PieEntry(pie1,"PIE"));
        entries.add(new PieEntry(it1,"IT"));


        PieDataSet dataset = new PieDataSet(entries,"Number of total Students");
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("BioTech");
        labels.add("Civil");
        labels.add("Elec");
        labels.add("Mech");
        labels.add("CSE");
        labels.add("ECE");
        labels.add("PIE");
        labels.add("IT");
        labels.add("Chem");

        Description description = new Description();
        description.setText("% of students total");


        //  BarChart chart = new BarChart(getContext());
        // ll.addView(chart);

        PieData data = new PieData(dataset);
        pc.setData(data);

        data.setValueTextSize(15f);
        // legend.setCustom(ColorTemplate.COLORFUL_COLORS,new String[]{"fdksd","b"});
        //xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        /*XAxis xAxis = b.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        bc.setDescription(description); */
        pc.animateY(100);
        pc.animateX(100);
        pc.invalidate();

        return v;

    }
}
