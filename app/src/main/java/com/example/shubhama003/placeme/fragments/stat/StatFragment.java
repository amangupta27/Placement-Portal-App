package com.example.shubhama003.placeme.fragments.stat;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shubhama003.placeme.R;

import java.util.ArrayList;
import java.util.List;


public class StatFragment extends Fragment {
    /*
    LinearLayout ll;
    BarChart bc;
    Float bt1,civ1,ee1,me1,cs1,ece1,pie1,it1,che1,bt2,civ2,ee2,me2,cs2,ece2,pie2,it2,che2;*/
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_graph, container, false);
        tabLayout = v.findViewById(R.id.tablayout2);
        viewPager = (ViewPager)v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout)v.findViewById(R.id.tablayout2);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        /*
        ll = v.findViewById(R.id.ll2);
        bc = v.findViewById(R.id.barchart);

        bt1 = Float.valueOf(LoginActivity.placed.getBT());
        civ1 = Float.valueOf(LoginActivity.placed.getCIV());
        ee1 = Float.valueOf(LoginActivity.placed.getEE());
        me1 = Float.valueOf(LoginActivity.placed.getME());
        cs1 = Float.valueOf(LoginActivity.placed.getCSE());
        ece1 = Float.valueOf(LoginActivity.placed.getECE());
        pie1 = Float.valueOf(LoginActivity.placed.getPIE());
        it1 = Float.valueOf(LoginActivity.placed.getIT());
        che1 = Float.valueOf(LoginActivity.placed.getCHE());

        bt2 = Float.valueOf(LoginActivity.total.getBT());
        civ2 = Float.valueOf(LoginActivity.total.getCIV());
        ee2 = Float.valueOf(LoginActivity.total.getEE());
        me2 = Float.valueOf(LoginActivity.total.getME());
        cs2 = Float.valueOf(LoginActivity.total.getCSE());
        ece2 = Float.valueOf(LoginActivity.total.getECE());
        pie2 = Float.valueOf(LoginActivity.total.getPIE());
        it2 = Float.valueOf(LoginActivity.total.getIT());
        che2 = Float.valueOf(LoginActivity.total.getCHE());

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



*/

        return v;
    }
    private void setupViewPager(ViewPager viewPager)
    {
        StatFragment.ViewPagerAdapter adapter = new StatFragment.ViewPagerAdapter(getChildFragmentManager()); // see it
        adapter.addFragment(new HistoFragment(),"Placed %");
        adapter.addFragment(new PiePlacedFragment(),"Placed");
        adapter.addFragment(new PieTotalFragment(),"Eligible");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter
    {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmenTitletList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fm, String title)
        {
            mFragmentList.add(fm);
            mFragmenTitletList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmenTitletList.get(position);
        }
    }

}
