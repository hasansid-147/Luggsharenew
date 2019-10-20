package com.android.luggshare.business.models.traveler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelerResponse {

    @SerializedName("Message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}