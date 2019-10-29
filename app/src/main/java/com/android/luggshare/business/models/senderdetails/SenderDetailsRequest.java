package com.android.luggshare.business.models.senderdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SenderDetailsRequest implements Serializable {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("req_id")
    @Expose
    private String reqId;
    @SerializedName("req_type")
    @Expose
    private String reqType;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

}
