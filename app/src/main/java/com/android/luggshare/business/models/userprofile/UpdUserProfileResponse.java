package com.android.luggshare.business.models.userprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;




public class UpdUserProfileResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdUserProfileResponse() {
    }

    /**
     *
     * @param message
     * @param status
     */
    public UpdUserProfileResponse(String status, String message) {
        super();
        this.status = status;
        this.message = message;
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

}
