package com.example.shubhama003.placeme.fragments.profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.utility.Utility;
import com.example.shubhama003.placeme.activities.Main2Activity;
import com.example.shubhama003.placeme.utility.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    private EditText regnosearch;
    private Button fab;
    private Button go;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProgressBar pbar;
    public static User x;
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_update, container, false);
        fab = v.findViewById(R.id.fab);
        x= Utility.us;
        if(x != Utility.x2){
            if(Utility.x2.getVerified()==null || !Utility.x2.getVerified().equals("1")){
                fab.setVisibility(View.VISIBLE);
            }
            viewPager = v.findViewById(R.id.viewpager);
            viewPager.setVisibility(View.VISIBLE);
            AppBarLayout abl = v.findViewById(R.id.abl);
            abl.setVisibility(View.VISIBLE);
        }
        if(!x.getRegistration().equals("Admin")){

            AppBarLayout abl = v.findViewById(R.id.abl);
            abl.setVisibility(View.VISIBLE);
            viewPager = v.findViewById(R.id.viewpager);
            viewPager.setVisibility(View.VISIBLE);
            LinearLayout ll = v.findViewById(R.id.search);
            ll.setVisibility(View.GONE);
        }

        regnosearch = v.findViewById(R.id.searchregno);
        go = v.findViewById(R.id.go);
        tabLayout = v.findViewById(R.id.tablayout2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Utility.x2.getStatus().equals("1")) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(Utility.x2.getRegistration()).child("verified")
                            .setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            Toast.makeText(getContext(),Utility.us.getRegistration()+" ok ",Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getContext(),"Not verified",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(),"Profile not complete",Toast.LENGTH_SHORT).show();
                }
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = regnosearch.getText().toString();
                FirebaseDatabase.getInstance().getReference().child("Users").child(s1).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        User us2 = dataSnapshot.getValue(User.class);
                        if (us2 == null) {
                            Toast.makeText(getContext(),"Wrong Registration Number",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else
                        {
                            Utility.x2 = us2;
                            startActivity(new Intent(getActivity(),Main2Activity.class));
                            getActivity().finish();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        viewPager = (ViewPager)v.findViewById(R.id.viewpager);
        tabLayout = (TabLayout)v.findViewById(R.id.tablayout2);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }
     private void setupViewPager(ViewPager viewPager)
     {
            ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager()); // see it
            adapter.addFragment(new PersonalDetailsFragment(),"Personal");
            adapter.addFragment(new AcademicDetailsFragment(),"Academic");
            adapter.addFragment(new SPIDetailsFragment(),"B.Tech");
            adapter.addFragment(new ProjectInternFragment(),"Project");
            adapter.addFragment(new PhotoResumeFragment(),"Photo/Resume");
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
