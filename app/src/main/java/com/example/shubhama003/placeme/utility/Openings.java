package com.example.shubhama003.placeme.utility;

import android.util.Log;

import com.example.shubhama003.placeme.utility.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by shubhama003 on 28/8/17.
 */

public class Openings {

    String companyName;
    String eligibility;
    List<String> branches;
    String salary;
    String profile;
    String location;
    String PPO;
    String website;
    String logoUrl;
    String deadline;

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }




    public Openings(String companyName, String eligibility, List<String> branches, String salary, String profile, String location, String PPO, String logur,String web ,String dead) {
        this.companyName = companyName;
        this.eligibility = eligibility;
        this.branches = branches;
        this.salary = salary;
        this.profile = profile;
        this.location = location;
        this.PPO = PPO;
        website = web;
        logoUrl = logur;
        deadline = dead;

    }
    public Openings()
    {
        this.companyName = "";
        this.eligibility = "";
        this.branches = null;
        this.salary = "";
        this.profile = "";
        this.location = "";
        this.PPO = "";
        this.website="";
        this.logoUrl="";
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getEligibility() {
        return eligibility;
    }

    public List<String> getBranches() {
        return branches;
    }

    public String getSalary() {
        return salary;
    }

    public String getProfile() {
        return profile;
    }

    public String getLocation() {
        return location;
    }

    public String getPPO() {
        return PPO;
    }

    public boolean isEligible(){

        if(Utility.us.getRegistration().equals("Admin"))
            return false;

        if(Double.parseDouble(Utility.us.getCPI())>=Double.parseDouble(this.eligibility)
                && branches.contains(Utility.us.getBranch()) && !Utility.us.getStatus().equals("0")
                 && (!Utility.us.getCredits().equals("0"))){
            return true;
        }
        else
            return false;
    }

    public boolean inTime(String deadline) throws ParseException {

        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String DateToStr = format.format(curDate);
        int year = Integer.valueOf(DateToStr.substring(0,4));
        int month = Integer.valueOf(DateToStr.substring(5,7));
        int date = Integer.valueOf(DateToStr.substring(8,10));
        int year2 = Integer.valueOf(deadline.substring(0,4));
        int month2 = Integer.valueOf(deadline.substring(5,7));
        int date2 = Integer.valueOf(deadline.substring(8,10));
        if(year<year2 ){
            return true;
        }
        else if(year == year2){
            if(month<month2){
                return true;
            }
            else if(month== month2){
                if(date<=date2){
                    return true;
                }
            }
        }
        Log.d("dateTimeNow",DateToStr);
        return false;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPPO(String PPO) {
        this.PPO = PPO;
    }

    public static Openings Search(String cname){
        for(Openings o:Utility.op){
            if(o.getCompanyName().equals(cname))
                return o;
        }
        return null;
    }
}
