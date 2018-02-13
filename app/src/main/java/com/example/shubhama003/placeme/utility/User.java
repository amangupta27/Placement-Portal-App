package com.example.shubhama003.placeme.utility;

import java.util.ArrayList;

/**
 * Created by shubhama003 on 26/8/17.
 */

public class User {
    String CPI;
    String Credits;
    String status;
    ArrayList<Registration> registered;
    ArrayList<String>Btech;
    String Year;
    String registration;
    String name;
    String fathername;
    String mothername;
    String course;
    String gender;
    String category;
    String dob;
    String physchall;
    String emailid;
    String phone;
    String AddCurr;
    String AddPerm;
    String Country;
    String school10;
    String school12;
    String board10;
    String board12;
    String pass10;
    String percent10;
    String pass12;
    String percent12;
    String ptitle;
    String pdesc;
    String ititle;
    String idesc;
    String resurl;
    String Url;
    String password;
    String Branch;
    String cname;
    String resName;
    String verified;

    public boolean isFilled()
    {
        return check(course)&&check(gender)&&check(category)&&check(fathername)&&check(mothername)&&check(dob)&&check(physchall)&&check(emailid)&&check(phone)&&check(AddCurr)&&check(AddPerm)&&check(Country)&&check(school10)&&check(school12)&&check(board10)&&check(board12)&&check(pass10)&&check(pass12)&&check(percent10)&&check(percent12)&&check(ptitle)&&check(pdesc)&&check(ititle)&&check(idesc)&&check(Url)&&check(resName)&&check(resurl);
    }
    public boolean check(String s)
    {
        return (s!=null)&&(!s.isEmpty());
    }

    public ArrayList<String> getBtech() {
        return Btech;
    }

    public void setBtech(ArrayList<String> btech) {
        Btech = btech;
    }

    public User(ArrayList<String> as, String CPI, String credits, String status, ArrayList<Registration> registered, String year, String registration, String name, String fathername, String mothername, String course, String gender, String category, String dob, String physchall, String emailid, String phone, String addCurr, String addPerm, String country, String school10, String school12, String board10, String board12, String pass10, String percent10, String pass12, String percent12, String ptitle, String pdesc, String ititle
            , String idesc, String resurl, String url, String password, String branch, String cname, String resName, String verified) {
        this.Btech = as;
        this.CPI = CPI;
        Credits = credits;
        this.status = status;
        this.registered = registered;
        Year = year;
        this.registration = registration;
        this.name = name;
        this.fathername = fathername;
        this.mothername = mothername;
        this.course = course;
        this.gender = gender;
        this.category = category;
        this.dob = dob;
        this.physchall = physchall;
        this.emailid = emailid;
        this.phone = phone;
        AddCurr = addCurr;
        AddPerm = addPerm;
        Country = country;
        this.school10 = school10;
        this.school12 = school12;
        this.board10 = board10;
        this.board12 = board12;
        this.pass10 = pass10;
        this.percent10 = percent10;
        this.pass12 = pass12;
        this.percent12 = percent12;
        this.ptitle = ptitle;
        this.pdesc = pdesc;
        this.ititle = ititle;
        this.idesc = idesc;
        this.resurl = resurl;
        Url = url;
        this.password = password;
        Branch = branch;
        this.cname = cname;
        this.resName = resName;
        this.verified = verified;
        Btech = as;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhyschall() {
        return physchall;
    }

    public void setPhyschall(String physchall) {
        this.physchall = physchall;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddCurr() {
        return AddCurr;
    }

    public void setAddCurr(String addCurr) {
        AddCurr = addCurr;
    }

    public String getAddPerm() {
        return AddPerm;
    }

    public void setAddPerm(String addPerm) {
        AddPerm = addPerm;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getSchool10() {
        return school10;
    }

    public void setSchool10(String school10) {
        this.school10 = school10;
    }

    public String getSchool12() {
        return school12;
    }

    public void setSchool12(String school12) {
        this.school12 = school12;
    }

    public String getBoard10() {
        return board10;
    }

    public void setBoard10(String board10) {
        this.board10 = board10;
    }

    public String getBoard12() {
        return board12;
    }

    public void setBoard12(String board12) {
        this.board12 = board12;
    }

    public String getPass10() {
        return pass10;
    }

    public void setPass10(String pass10) {
        this.pass10 = pass10;
    }

    public String getPercent10() {
        return percent10;
    }

    public void setPercent10(String percent10) {
        this.percent10 = percent10;
    }

    public String getPass12() {
        return pass12;
    }

    public void setPass12(String pass12) {
        this.pass12 = pass12;
    }

    public String getPercent12() {
        return percent12;
    }

    public void setPercent12(String percent12) {
        this.percent12 = percent12;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public String getItitle() {
        return ititle;
    }

    public void setItitle(String ititle) {
        this.ititle = ititle;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public String getResurl() {
        return resurl;
    }

    public void setResurl(String resurl) {
        this.resurl = resurl;
    }
    public User()
    {
    }


    public ArrayList<Registration> getRegistered() {
        return registered;
    }

    public void setRegistered(ArrayList<Registration> registered) {
        this.registered = registered;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }



    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }




    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getCPI() {
        return CPI;
    }

    public void setCPI(String CPI) {
        this.CPI = CPI;
    }

    public String getCredits() {
        return Credits;
    }

    public void setCredits(String credits) {
        Credits = credits;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public void setName(String name) {
        this.name = name;
    }
}
