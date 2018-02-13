package com.example.shubhama003.placeme.utility;

import java.util.ArrayList;

/**
 * Created by shubhama003 on 31/8/17.
 */

public class Registration {
    String CompanyName;
    String DateTime;
    public Registration(){}

    public Registration(String companyName, String dateTime) {
        CompanyName = companyName;
        DateTime = dateTime;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public static ArrayList<String> getCompanyList(ArrayList<Registration> reg){
        ArrayList<String>s = new ArrayList<String>();
        for(Registration r: reg){
            s.add(r.getCompanyName());
        }
        return s;
    }

    public static Registration search(ArrayList<Registration> registered, String companyName) {
        for(Registration a: registered){
            if(a.getCompanyName().equals(companyName))
                return a;
        }
        return null;
    }
}