package com.example.shubhama003.placeme.utility;

import com.example.shubhama003.placeme.activities.LoginActivity;

import java.util.ArrayList;

/**
 * Created by shubhama003 on 13/10/17.
 */

public class Utility {

    public static User us = null, x2 = null;
    public static Stat total = null, placed = null;
    public static ArrayList<Openings> op;
    public static ArrayList<Openings> rc;

    public static boolean isAdmin(){
        return us.getRegistration().equals("Admin");
    }
}
