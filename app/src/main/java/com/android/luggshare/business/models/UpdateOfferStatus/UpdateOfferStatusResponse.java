package com.android.luggshare.business.models.UpdateOfferStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateOfferStatusResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("from_uid")
    @Expose
    private Integer fromUid;
    @SerializedName("to_uid")
    @Expose
    private Integer toUid;
    @SerializedName("from_email")
    @Expose
    private String fromEmail;
    @SerializedName("to_email")
    @Expose
    private String toEmail;

    /**
     * No args constructor for use in serialization
     *
     */
    public UpdateOfferStatusResponse() {
    }

    /**
     *
     * @param toUid
     * @param fromUid
     * @param message
     * @param toEmail
     * @param status
     * @param fromEmail
     */
    public UpdateOfferStatusResponse(String status, String message, Integer fromUid, Integer toUid, String fromEmail, String toEmail) {
        super();
        this.status = status;
        this.message = message;
        this.fromUid = fromUid;
        this.toUid = toUid;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
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

    public Integer getFromUid() {
        return fromUid;
    }

    public void setFromUid(Integer fromUid) {
        this.fromUid = fromUid;
    }

    public Integer getToUid() {
        return toUid;
    }

    public void setToUid(Integer toUid) {
        this.toUid = toUid;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

}
