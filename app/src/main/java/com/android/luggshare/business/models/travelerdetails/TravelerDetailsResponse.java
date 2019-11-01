package com.android.luggshare.business.models.travelerdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TravelerDetailsResponse implements Serializable {

    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("dep_from_country")
    @Expose
    private String depFromCountry;
    @SerializedName("dep_from_city")
    @Expose
    private String depFromCity;
    @SerializedName("arrv_to_country")
    @Expose
    private String arrvToCountry;
    @SerializedName("arrv_to_city")
    @Expose
    private String arrvToCity;
    @SerializedName("dep_time")
    @Expose
    private String depTime;
    @SerializedName("exp_arrv_time")
    @Expose
    private String expArrvTime;
    @SerializedName("bag_cap")
    @Expose
    private Double bagCap;
    @SerializedName("Categories")
    @Expose
    private String categories;
    @SerializedName("reqstatus")
    @Expose
    private String reqstatus;
    @SerializedName("offerstatus")
    @Expose
    private String offerstatus;

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepFromCountry() {
        return depFromCountry;
    }

    public void setDepFromCountry(String depFromCountry) {
        this.depFromCountry = depFromCountry;
    }

    public String getDepFromCity() {
        return depFromCity;
    }

    public void setDepFromCity(String depFromCity) {
        this.depFromCity = depFromCity;
    }

    public String getArrvToCountry() {
        return arrvToCountry;
    }

    public void setArrvToCountry(String arrvToCountry) {
        this.arrvToCountry = arrvToCountry;
    }

    public String getArrvToCity() {
        return arrvToCity;
    }

    public void setArrvToCity(String arrvToCity) {
        this.arrvToCity = arrvToCity;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getExpArrvTime() {
        return expArrvTime;
    }

    public void setExpArrvTime(String expArrvTime) {
        this.expArrvTime = expArrvTime;
    }

    public Double getBagCap() {
        return bagCap;
    }

    public void setBagCap(Double bagCap) {
        this.bagCap = bagCap;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getReqstatus() {
        return reqstatus;
    }

    public void setReqstatus(String reqstatus) {
        this.reqstatus = reqstatus;
    }

    public String getOfferstatus() {
        return offerstatus;
    }

    public void setOfferstatus(String offerstatus) {
        this.offerstatus = offerstatus;
    }
}