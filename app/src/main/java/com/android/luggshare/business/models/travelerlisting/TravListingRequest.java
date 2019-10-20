package com.android.luggshare.business.models.travelerlisting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravListingRequest {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("reqtype")
    @Expose
    private String reqtype;
    @SerializedName("fromcountry")
    @Expose
    private String fromcountry;
    @SerializedName("frmcity")
    @Expose
    private String frmcity;
    @SerializedName("tocountry")
    @Expose
    private String tocountry;
    @SerializedName("tocity")
    @Expose
    private String tocity;
    @SerializedName("deptime")
    @Expose
    private String deptime;
    @SerializedName("arrvtime")
    @Expose
    private String arrvtime;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReqtype() {
        return reqtype;
    }

    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }

    public String getFromcountry() {
        return fromcountry;
    }

    public void setFromcountry(String fromcountry) {
        this.fromcountry = fromcountry;
    }

    public String getFrmcity() {
        return frmcity;
    }

    public void setFrmcity(String frmcity) {
        this.frmcity = frmcity;
    }

    public String getTocountry() {
        return tocountry;
    }

    public void setTocountry(String tocountry) {
        this.tocountry = tocountry;
    }

    public String getTocity() {
        return tocity;
    }

    public void setTocity(String tocity) {
        this.tocity = tocity;
    }

    public String getDeptime() {
        return deptime;
    }

    public void setDeptime(String deptime) {
        this.deptime = deptime;
    }

    public String getArrvtime() {
        return arrvtime;
    }

    public void setArrvtime(String arrvtime) {
        this.arrvtime = arrvtime;
    }

}