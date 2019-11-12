package com.android.luggshare.business.models.acceptoffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OfferAcceptRequest implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("TravelerUid")
    @Expose
    private Integer travelerUid;
    @SerializedName("SendPurchaserreqid")
    @Expose
    private Integer sendPurchaserreqid;
    @SerializedName("Travelerreqid")
    @Expose
    private Integer travelerreqid;
    @SerializedName("reqtype")
    @Expose
    private String reqtype;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTravelerUid() {
        return travelerUid;
    }

    public void setTravelerUid(Integer travelerUid) {
        this.travelerUid = travelerUid;
    }

    public Integer getSendPurchaserreqid() {
        return sendPurchaserreqid;
    }

    public void setSendPurchaserreqid(Integer sendPurchaserreqid) {
        this.sendPurchaserreqid = sendPurchaserreqid;
    }

    public Integer getTravelerreqid() {
        return travelerreqid;
    }

    public void setTravelerreqid(Integer travelerreqid) {
        this.travelerreqid = travelerreqid;
    }

    public String getReqtype() {
        return reqtype;
    }

    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
