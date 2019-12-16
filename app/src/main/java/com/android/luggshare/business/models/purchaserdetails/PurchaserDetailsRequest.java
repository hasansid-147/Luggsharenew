package com.android.luggshare.business.models.purchaserdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchaserDetailsRequest implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    @SerializedName("req_type")
    @Expose
    private String reqType;

    /**
     * No args constructor for use in serialization
     *
     */
    public PurchaserDetailsRequest() {
    }

    /**
     *
     * @param uid
     * @param reqType
     * @param reqId
     */
    public PurchaserDetailsRequest(Integer uid, Integer reqId, String reqType) {
        super();
        this.uid = uid;
        this.reqId = reqId;
        this.reqType = reqType;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

}