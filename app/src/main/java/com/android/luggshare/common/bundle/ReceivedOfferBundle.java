package com.android.luggshare.common.bundle;

import com.android.luggshare.business.models.getmyoffersreceived.MyOffersReceivedListResponseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceivedOfferBundle implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String requestType;
    @SerializedName("requestObj")
    @Expose
    private MyOffersReceivedListResponseModel requestObj;

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

    public MyOffersReceivedListResponseModel getRequestObj() {
        return requestObj;
    }

    public void setRequestObj(MyOffersReceivedListResponseModel requestObj) {
        this.requestObj = requestObj;
    }
}