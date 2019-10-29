package com.android.luggshare.common.bundle;

import com.android.luggshare.business.models.getsenderlist.ListResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RequestTypeBundle implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String requestType;
    @SerializedName("requestObj")
    @Expose
    private ListResponse requestObj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public ListResponse getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(ListResponse requestObj) {
        this.requestObj = requestObj;
    }
}