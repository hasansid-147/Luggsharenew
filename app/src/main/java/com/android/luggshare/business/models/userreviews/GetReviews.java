package com.android.luggshare.business.models.userreviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetReviews {

    @SerializedName("uid")
    @Expose
    private Integer uid;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetReviews() {
    }

    /**
     *
     * @param uid
     */
    public GetReviews(Integer uid) {
        super();
        this.uid = uid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

}
