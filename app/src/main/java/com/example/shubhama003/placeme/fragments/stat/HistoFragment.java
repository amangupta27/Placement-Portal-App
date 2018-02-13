package com.example.shubhama003.placeme.fragments.stat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class HistoFragment extends Fragment {

    LinearLayout ll;
    BarChart bc;
    Float bt1,civ1,ee1,me1,cs1,ece1,pie1,it1,che1,bt2,civ2,ee2,me2,cs2,ece2,pie2,it2,che2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_histo, container, false);
        ll = v.findViewById(R.id.ll2);
        bc = v.findViewById(R.id.barchart);

        bt1 = Float.valueOf(Utility.placed.getBT());
        civ1 = Float.valueOf(Utility.placed.getCIV());
        ee1 = Float.valueOf(Utility.placed.getEE());
        me1 = Float.valueOf(Utility.placed.getME());
        cs1 = Float.valueOf(Utility.placed.getCSE());
        ece1 = Float.valueOf(Utility.placed.getECE());
        pie1 = Float.valueOf(Utility.placed.getPIE());
        it1 = Float.valueOf(Utility.placed.getIT());
        che1 = Float.valueOf(Utility.placed.getCHE());


        bt2 = Float.valueOf(Utility.total.getBT());
        civ2 = Float.valueOf(Utility.total.getCIV());
        ee2 = Float.valueOf(Utility.total.getEE());
        me2 = Float.valueOf(Utility.total.getME());
        cs2 = Float.valueOf(Utility.total.getCSE());
        ece2 = Float.valueOf(Utility.total.getECE());
        pie2 = Float.valueOf(Utility.total.getPIE());
        it2 = Float.valueOf(Utility.total.getIT());
        che2 = Float.valueOf(Utility.total.getCHE());

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, bt1*100/bt2));
        entries.add(new BarEntry(1f, civ1*100/civ2));
        entries.add(new BarEntry(2f, ee1*100/ee2));
        entries.add(new BarEntry(3f, me1*100/me2));
        entries.add(new BarEntry(4f, cs1*100/cs2));
        entries.add(new BarEntry(5f, ece1*100/ece2));
        entries.add(new BarEntry(6f, pie1*100/pie2));
        entries.add(new BarEntry(7f, it1*100/it2));
        entries.add(new BarEntry(8f, che1*100/che2));

        BarDataSet dataset = new BarDataSet(entries, "% of students");
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("BT");
        labels.add("CE");
        labels.add("EE");
        labels.add("ME");
        labels.add("CSE");
        labels.add("ECE");
        labels.add("PIE");
        labels.add("IT");
        labels.add("CHE");


        Description description = new Description();
        description.setText("% of students placed");


        //  BarChart chart = new BarChart(getContext());
        // ll.addView(chart);

        BarData data = new BarData( dataset);
        bc.setData(data);
        //xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        XAxis xAxis = bc.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        bc.setDescription(description);
        bc.animateY(100);
        bc.animateX(100);
        return v;

    }
}
