package com.android.luggshare.business.models.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTracking {

    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("latitude")
    @Expose
    private String latitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateTracking() {
    }

    /**
     *
     * @param latitude
     * @param offerId
     * @param comment
     * @param status
     * @param longitude
     */
    public UpdateTracking(Integer offerId, String comment, String status, String longitude, String latitude) {
        super();
        this.offerId = offerId;
        this.comment = comment;
        this.status = status;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

}
