package com.android.luggshare.business.models.editoffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditOfferReponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("recv_uid")
    @Expose
    private Integer recvUid;
    @SerializedName("recv_email")
    @Expose
    private String recvEmail;

    /**
     * No args constructor for use in serialization
     *
     */
    public EditOfferReponse() {
    }

    /**
     *
     * @param recvEmail
     * @param recvUid
     * @param message
     * @param status
     */
    public EditOfferReponse(Integer status, String message, Integer recvUid, String recvEmail) {
        super();
        this.status = status;
        this.message = message;
        this.recvUid = recvUid;
        this.recvEmail = recvEmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRecvUid() {
        return recvUid;
    }

    public void setRecvUid(Integer recvUid) {
        this.recvUid = recvUid;
    }

    public String getRecvEmail() {
        return recvEmail;
    }

    public void setRecvEmail(String recvEmail) {
        this.recvEmail = recvEmail;
    }

}
