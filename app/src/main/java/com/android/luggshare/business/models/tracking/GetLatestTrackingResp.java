package com.android.luggshare.business.models.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetLatestTrackingResp {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("longitutde")
    @Expose
    private String longitutde;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetLatestTrackingResp() {
    }

    /**
     *
     * @param longitutde
     * @param latitude
     * @param comment
     * @param status
     * @param updatedAt
     */
    public GetLatestTrackingResp(String status, String comment, String longitutde, String latitude, String updatedAt) {
        super();
        this.status = status;
        this.comment = comment;
        this.longitutde = longitutde;
        this.latitude = latitude;
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLongitutde() {
        return longitutde;
    }

    public void setLongitutde(String longitutde) {
        this.longitutde = longitutde;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
