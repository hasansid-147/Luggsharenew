package com.android.luggshare.business.models.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLatestTracking {

    @SerializedName("offerid")
    @Expose
    private Integer offerid;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLatestTracking() {
    }

    /**
     *
     * @param offerid
     */
    public GetLatestTracking(Integer offerid) {
        super();
        this.offerid = offerid;
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

}
