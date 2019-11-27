package com.android.luggshare.business.models.getmyofferspending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOffersPendingListRequestModel {

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
