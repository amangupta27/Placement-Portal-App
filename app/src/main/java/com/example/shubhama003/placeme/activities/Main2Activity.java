package com.example.shubhama003.placeme.activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shubhama003.placeme.fragments.admin.AddCompanyFragment;
import com.example.shubhama003.placeme.fragments.admin.AddPlacedStudentFragment;
import com.example.shubhama003.placeme.fragments.admin.AddUserFragment;
import com.example.shubhama003.placeme.fragments.ContactFragment;
import com.example.shubhama003.placeme.fragments.user.CurrentOpeningsFragment;
import com.example.shubhama003.placeme.fragments.stat.StatFragment;
import com.example.shubhama003.placeme.fragments.user.HomeFragment;
import com.example.shubhama003.placeme.fragments.PasswordChangeFragment;
import com.example.shubhama003.placeme.services.MyService;
import com.example.shubhama003.placeme.R;
import com.example.shubhama003.placeme.fragments.user.RegisteredCompaniesFragment;
import com.example.shubhama003.placeme.fragments.profile.ProfileFragment;
import com.example.shubhama003.placeme.utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    private ImageView photo;
    private TextView nam;
    private DrawerLayout drawer;
    private View navHeader;
    private TextView reg;
    private NavigationView navview;
    private static int navItemIndex = 0;
    private Toolbar toolbar;
    private boolean admin = false;
    private static String TAG_HOME;
    private static final String TAG_UPDATE = "Update profile";
    private static final String TAG_CO = "Current openings";
    private static final String TAG_RC = "Registered companies";
    private static final String TAG_VIEW = "View Profile";
    private static final String TAG_PASS = "Change Password";
    private static final String TAG_GRAPH = "Stastics";
    private static final String TAG_CONTACT = "ContactFragment";
    private Handler mHandler;
    public static String CURRENT_TAG;
    private Menu Navmenu;
    private MenuItem nav_1, nav_2, nav_3, nav_4,nav_5,nav_6;
    public static DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        startService( new Intent(getApplicationContext(), MyService.class));
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String DateToStr = format.format(curDate);
        Log.d("dateTimeNow", DateToStr);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navview = (NavigationView) findViewById(R.id.nav_view);
        navHeader = navview.getHeaderView(0);
        Navmenu = navview.getMenu();
        nav_1 = Navmenu.findItem(R.id.m_home);
        nav_2 = Navmenu.findItem(R.id.m_update);
        nav_3 = Navmenu.findItem(R.id.m_co);
        nav_4 = Navmenu.findItem(R.id.m_rc);
        int x = Utility.us.getName().indexOf("");

        downloadManager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        if (x != (-1))
            TAG_HOME = "Welcome " + Utility.us.getName().substring(0, x);
        if (Utility.us.getRegistration().equals("Admin")) {
            admin = true;
            CURRENT_TAG = TAG_VIEW;
            nav_2.setTitle("Add user");
            nav_1.setTitle("View profile");
            nav_3.setTitle("Add opening");
            nav_4.setTitle("Add placed");
        } else
            CURRENT_TAG = TAG_HOME;
        nam = (TextView) navHeader.findViewById(R.id.name);
        reg = (TextView) navHeader.findViewById(R.id.regno);
        photo = (ImageView) navHeader.findViewById(R.id.imageView);

        setUpNavigationView();
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void loadHomeFragment() {
        //Toast.makeText(Main2Activity.this,"LOAD "+String.valueOf(navItemIndex)+" "+CURRENT_TAG,Toast.LENGTH_SHORT).show();
        selectNavMenu();
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            //Toast.makeText(Main2Activity.this,"NOT NULL"+String.valueOf(navItemIndex)+" "+CURRENT_TAG,Toast.LENGTH_SHORT).show();
            drawer.closeDrawers();
            return;
        }
        Fragment fragment = getHomeFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.flContent, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
        drawer.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                if (!admin) {
                    //Toast.makeText(getApplicationContext(),Utility.us.getRegistration(),Toast.LENGTH_SHORT).show();
                    HomeFragment homeFragment = new HomeFragment();
                    return homeFragment;
                } else {
                    //Toast.makeText(getApplicationContext(),"askks",Toast.LENGTH_SHORT).show();
                    ProfileFragment profileFragment = new ProfileFragment();
                    return profileFragment;
                }
            case 1:
                if(!admin) {
                    ProfileFragment profileFragment = new ProfileFragment();
                    return profileFragment;
                }
                else{
                    AddUserFragment addUserFragment = new AddUserFragment();
                    return addUserFragment;
                }
            case 2:
                PasswordChangeFragment passfrag = new PasswordChangeFragment();
                return passfrag;

            case 3:
                if(!admin) {
                    CurrentOpeningsFragment COFragment = new CurrentOpeningsFragment();
                    return COFragment;
                }
                else{
                    AddCompanyFragment openingFragment= new AddCompanyFragment();
                    return openingFragment;
                }

            case 4:
                if(!admin) {
                    RegisteredCompaniesFragment RCFragment = new RegisteredCompaniesFragment();
                    return RCFragment;
                }
                else{
                    AddPlacedStudentFragment placedFragment = new AddPlacedStudentFragment();
                    return placedFragment;
                }
            case 5:
                StatFragment gf = new StatFragment();
                return gf;
            case 6:
                ContactFragment cme = new ContactFragment();
                return cme;
            default:
                return new HomeFragment();
        }
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(CURRENT_TAG);
    }

    private void selectNavMenu() {
        navview.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.m_home:
                        navItemIndex = 0;
                        if (!Utility.us.getRegistration().equals("Admin"))
                            CURRENT_TAG = TAG_HOME;
                        else
                            CURRENT_TAG = "Add User";
                        break;
                    case R.id.m_update:
                        navItemIndex = 1;
                        if(admin)
                            CURRENT_TAG = "User Profile";
                        else
                            CURRENT_TAG = "Profile";
                        break;

                    case R.id.m_co:
                        navItemIndex = 3;
                        if(!admin)
                            CURRENT_TAG = TAG_CO;
                        else
                            CURRENT_TAG="Add Opening";
                        break;
                    case R.id.m_rc:
                        navItemIndex = 4;
                        if(!admin)
                        CURRENT_TAG = TAG_RC;
                        else
                            CURRENT_TAG="Add Placed";
                        break;
                    case R.id.m_contact:
                        navItemIndex = 6;
                        CURRENT_TAG=TAG_CONTACT;
                        break;
                    case R.id.passwordchange:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_PASS;
                        break;
                    case R.id.stats:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_GRAPH;
                        break;
                    default:
                        navItemIndex = 0;
                }
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadHomeFragment();
                return true;

            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

        };

        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("Regnum", "#");
            editor.putString("Pass", "#");
            editor.commit();
            startActivity(new Intent(Main2Activity.this, LoginActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}