package com.android.luggshare.business.models.UpdateOfferStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOfferStatus {


    @SerializedName("offerid")
    @Expose
    private Integer offerid;
    @SerializedName("delcode")
    @Expose
    private Integer delcode;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateOfferStatus() {
    }

    /**
     *
     * @param delcode
     * @param offerid
     * @param status
     */
    public UpdateOfferStatus(Integer offerid, Integer delcode, String status) {
        super();
        this.offerid = offerid;
        this.delcode = delcode;
        this.status = status;
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

    public Integer getDelcode() {
        return delcode;
    }

    public void setDelcode(Integer delcode) {
        this.delcode = delcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
