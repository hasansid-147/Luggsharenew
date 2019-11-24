package com.android.luggshare.business.models.userprofile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailVerificationResponse {

    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Msg")
    @Expose
    private String msg;

    /**
     * No args constructor for use in serialization
     *
     */
    public EmailVerificationResponse() {
    }

    /**
     *
     * @param msg
     * @param success
     * @param title
     */
    public EmailVerificationResponse(Boolean success, String title, String msg) {
        super();
        this.success = success;
        this.title = title;
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
