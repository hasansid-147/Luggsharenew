package com.android.luggshare.common.bundle;

import com.android.luggshare.business.models.travelerlisting.TravListingResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TravelerRequestBundle implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("req_typ")
    @Expose
    private String reqTyp;
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
    @SerializedName("bag_capacity")
    @Expose
    private Double bagCapacity;
    @SerializedName("pref_item1")
    @Expose
    private String prefItem1;
    @SerializedName("pref_item2")
    @Expose
    private String prefItem2;
    @SerializedName("pref_item3")
    @Expose
    private String prefItem3;

    @SerializedName("dep_time")
    @Expose
    private String depTime;

    @SerializedName("exp_arrivaltime")
    @Expose
    private String expArrivaltime;

    @SerializedName("trav_response_obj")
    @Expose
    private TravListingResponse travListRespObj;

    @SerializedName("from_details_screen")
    @Expose
    private Boolean fromDetailsScreen = false;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReqTyp() {
        return reqTyp;
    }

    public void setReqTyp(String reqTyp) {
        this.reqTyp = reqTyp;
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

    public Double getBagCapacity() {
        return bagCapacity;
    }

    public void setBagCapacity(Double bagCapacity) {
        this.bagCapacity = bagCapacity;
    }

    public String getPrefItem1() {
        return prefItem1;
    }

    public void setPrefItem1(String prefItem1) {
        this.prefItem1 = prefItem1;
    }

    public String getPrefItem2() {
        return prefItem2;
    }

    public void setPrefItem2(String prefItem2) {
        this.prefItem2 = prefItem2;
    }

    public String getPrefItem3() {
        return prefItem3;
    }

    public void setPrefItem3(String prefItem3) {
        this.prefItem3 = prefItem3;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getExpArrivaltime() {
        return expArrivaltime;
    }

    public void setExpArrivaltime(String expArrivaltime) {
        this.expArrivaltime = expArrivaltime;
    }

    public TravListingResponse getTravListRespObj() {
        return travListRespObj;
    }

    public void setTravListRespObj(TravListingResponse travListRespObj) {
        this.travListRespObj = travListRespObj;
    }

    public Boolean getFromDetailsScreen() {
        return fromDetailsScreen;
    }

    public void setFromDetailsScreen(Boolean fromDetailsScreen) {
        this.fromDetailsScreen = fromDetailsScreen;
    }
}