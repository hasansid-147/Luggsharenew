package com.android.luggshare.business.models.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTrackList {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("Trackingas")
    @Expose
    private Integer trackingas;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetTrackList() {
    }

    /**
     *
     * @param uid
     * @param trackingas
     */
    public GetTrackList(Integer uid, Integer trackingas) {
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