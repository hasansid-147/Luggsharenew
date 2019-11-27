package com.android.luggshare.common.bundle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrackingBundle implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("Trackingas")
    @Expose
    private Integer trackingas;

    /**
     * No args constructor for use in serialization
     */
    public TrackingBundle() {
    }

    /**
     * @param uid
     * @param trackingas
     */
    public TrackingBundle(Integer uid, Integer trackingas) {
        super();
        this.uid = uid;
        this.trackingas = trackingas;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getTrackingas() {
        return trackingas;
    }

    public void setTrackingas(Integer trackingas) {
        this.trackingas = trackingas;
    }
}