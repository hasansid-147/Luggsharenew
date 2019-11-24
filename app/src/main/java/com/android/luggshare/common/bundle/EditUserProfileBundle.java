package com.android.luggshare.common.bundle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class EditUserProfileBundle implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("pass")
    @Expose
    private String pass;
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
    public EditUserProfileBundle() {
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
    public EditUserProfileBundle(Integer uid, String fname, String lname, String email, String pass, String country, String city) {
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
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