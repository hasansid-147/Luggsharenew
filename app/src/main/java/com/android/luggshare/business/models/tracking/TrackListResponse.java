package com.android.luggshare.business.models.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackListResponse {

    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @SerializedName("req_typ")
    @Expose
    private String reqTyp;
    @SerializedName("deliveryname")
    @Expose
    private String deliveryname;
    @SerializedName("traveleruid")
    @Expose
    private Integer traveleruid;

    /**
     * No args constructor for use in serialization
     *
     */
    public TrackListResponse() {
    }

    /**
     *
     * @param reqTyp
     * @param offerId
     * @param traveleruid
     * @param deliveryname
     */
    public TrackListResponse(Integer offerId, String reqTyp, String deliveryname, Integer traveleruid) {
        super();
        this.offerId = offerId;
        this.reqTyp = reqTyp;
        this.deliveryname = deliveryname;
        this.traveleruid = traveleruid;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getReqTyp() {
        return reqTyp;
    }

    public void setReqTyp(String reqTyp) {
        this.reqTyp = reqTyp;
    }

    public String getDeliveryname() {
        return deliveryname;
    }

    public void setDeliveryname(String deliveryname) {
        this.deliveryname = deliveryname;
    }

    public Integer getTraveleruid() {
        return traveleruid;
    }

    public void setTraveleruid(Integer traveleruid) {
        this.traveleruid = traveleruid;
    }

}
