package com.android.luggshare.business.models.getmyoffersreceived;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOffersReceivedListRequestModel {

    @SerializedName("uid")
    @Expose
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }


}
