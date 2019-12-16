package com.android.luggshare.common.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ViewReviewsBundle implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;

    /**
     * No args constructor for use in serialization
     *
     */
    public ViewReviewsBundle() {
    }

    /**
     *
     * @param uid
     */
    public ViewReviewsBundle(Integer uid) {
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
