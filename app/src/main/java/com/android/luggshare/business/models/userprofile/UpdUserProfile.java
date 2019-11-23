package com.android.luggshare.business.models.userprofile;

import com.google.gson.annotations.Expose;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpdUserProfile {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("fname")
    @Expose
    private Object fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pass")
    @Expose
    private Object pass;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdUserProfile() {
    }

    /**
     *
     * @param uid
     * @param fname
     * @param country
     * @param lname
     * @param pass
     * @param city
     * @param email
     */
    public UpdUserProfile(Integer uid, Object fname, String lname, String email, Object pass, String country, String city) {
        super();
        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.country = country;
        this.city = city;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Object getFname() {
        return fname;
    }

    public void setFname(Object fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPass() {
        return pass;
    }

    public void setPass(Object pass) {
        this.pass = pass;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



}
