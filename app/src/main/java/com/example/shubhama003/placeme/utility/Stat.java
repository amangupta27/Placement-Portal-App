package com.example.shubhama003.placeme.utility;

/**
 * Created by shubhama003 on 12/10/17.
 */

public class Stat {

    String CSE,IT,ECE,EE,ME,CIV,PIE,BT,CHE;

    public Stat(String CSE, String IT, String ECE, String EE, String ME, String CIV, String PIE, String BT, String CHE) {
        this.CSE = CSE;
        this.IT = IT;
        this.ECE = ECE;
        this.EE = EE;
        this.ME = ME;
        this.CIV = CIV;
        this.PIE = PIE;
        this.BT = BT;
        this.CHE = CHE;
    }
    public Stat()
    {

    }

    public String getCSE() {
        return CSE;
    }

    public void setCSE(String CSE) {
        this.CSE = CSE;
    }

    public String getIT() {
        return IT;
    }

    public void setIT(String IT) {
        this.IT = IT;
    }

    public String getECE() {
        return ECE;
    }

    public void setECE(String ECE) {
        this.ECE = ECE;
    }

    public String getEE() {
        return EE;
    }

    public void setEE(String EE) {
        this.EE = EE;
    }

    public String getME() {
        return ME;
    }

    public void setME(String ME) {
        this.ME = ME;
    }

    public String getCIV() {
        return CIV;
    }

    public void setCIV(String CIV) {
        this.CIV = CIV;
    }

    public String getPIE() {
        return PIE;
    }

    public void setPIE(String PIE) {
        this.PIE = PIE;
    }

    public String getBT() {
        return BT;
    }

    public void setBT(String BT) {
        this.BT = BT;
    }

    public String getCHE() {
        return CHE;
    }

    public void setCHE(String CHE) {
        this.CHE = CHE;
    }

    public static Stat initZero(Stat q)
    {
         if(q.getBT()==null)
             q.setBT("0");
        if(q.getCIV()==null)
            q.setCIV("0");
        if(q.getCSE()==null)
            q.setCSE("0");
        if(q.getIT()==null)
            q.setIT("0");
        if(q.getECE()==null)
        q.setECE("0");
        if(q.getEE()==null)
            q.setEE("0");
        if(q.getME()==null)
            q.setME("0");
        if(q.getPIE()==null)
            q.setPIE("0");
        if(q.getCHE()==null)
            q.setCHE("0");

         return q;
    }

    public void  set(String s)
    {
        if(s.equals("BT"))
            BT = String.valueOf(Integer.valueOf(BT)+1);

        if(s.equals("CSE"))
            CSE = String.valueOf(Integer.valueOf(CSE)+1);

        if(s.equals("ECE"))
            ECE = String.valueOf(Integer.valueOf(ECE)+1);

        if(s.equals("IT"))
            IT = String.valueOf(Integer.valueOf(IT)+1);

        if(s.equals("EE"))
            EE = String.valueOf(Integer.valueOf(EE)+1);

        if(s.equals("ME"))
            ME = String.valueOf(Integer.valueOf(ME)+1);

        if(s.equals("CHE"))
            CHE = String.valueOf(Integer.valueOf(CHE)+1);

        if(s.equals("CIV"))
            CIV = String.valueOf(Integer.valueOf(CIV)+1);

        if(s.equals("PIE"))
            PIE = String.valueOf(Integer.valueOf(PIE)+1);
    }
}
