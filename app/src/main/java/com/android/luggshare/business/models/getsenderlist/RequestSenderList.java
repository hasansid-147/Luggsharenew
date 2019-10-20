package com.android.luggshare.business.models.getsenderlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSenderList {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("req_type")
    @Expose
    private String reqType;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

}
