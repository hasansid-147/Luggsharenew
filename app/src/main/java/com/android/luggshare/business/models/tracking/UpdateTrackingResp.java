package com.android.luggshare.business.models.tracking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTrackingResp {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("exMessage")
    @Expose
    private String exMessage;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateTrackingResp() {
    }

    /**
     *
     * @param exMessage
     * @param message
     * @param status
     */
    public UpdateTrackingResp(String status, String message, String exMessage) {
        super();
        this.status = status;
        this.message = message;
        this.exMessage = exMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExMessage() {
        return exMessage;
    }

    public void setExMessage(String exMessage) {
        this.exMessage = exMessage;
    }

}
