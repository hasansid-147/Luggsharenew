package com.android.luggshare.business.models.traveleroffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelerOfferResponse {

    @SerializedName("offerid")
    @Expose
    private Integer offerid;
    @SerializedName("fromuid")
    @Expose
    private Integer fromuid;
    @SerializedName("from_user")
    @Expose
    private String fromUser;
    @SerializedName("from_email")
    @Expose
    private String fromEmail;
    @SerializedName("fromreq")
    @Expose
    private Integer fromreq;
    @SerializedName("touid")
    @Expose
    private Integer touid;
    @SerializedName("to_user")
    @Expose
    private String toUser;
    @SerializedName("to_email")
    @Expose
    private String toEmail;
    @SerializedName("toreq")
    @Expose
    private Integer toreq;
    @SerializedName("item_name")
    @Expose
    private Object itemName;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

    public Integer getFromuid() {
        return fromuid;
    }

    public void setFromuid(Integer fromuid) {
        this.fromuid = fromuid;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public Integer getFromreq() {
        return fromreq;
    }

    public void setFromreq(Integer fromreq) {
        this.fromreq = fromreq;
    }

    public Integer getTouid() {
        return touid;
    }

    public void setTouid(Integer touid) {
        this.touid = touid;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public Integer getToreq() {
        return toreq;
    }

    public void setToreq(Integer toreq) {
        this.toreq = toreq;
    }

    public Object getItemName() {
        return itemName;
    }

    public void setItemName(Object itemName) {
        this.itemName = itemName;
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

}