package com.android.luggshare.business.models.acceptoffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OfferAcceptResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("offerid")
    @Expose
    private Integer offerid;
    @SerializedName("Traveleremail")
    @Expose
    private String traveleremail;
    @SerializedName("Senderemail")
    @Expose
    private String senderemail;
    @SerializedName("Travelername")
    @Expose
    private String travelername;
    @SerializedName("Sendername")
    @Expose
    private String sendername;
    @SerializedName("DevCode")
    @Expose
    private Integer devCode;
    @SerializedName("Dev_name")
    @Expose
    private String devName;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("card")
    @Expose
    private String card;

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

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

    public String getTraveleremail() {
        return traveleremail;
    }

    public void setTraveleremail(String traveleremail) {
        this.traveleremail = traveleremail;
    }

    public String getSenderemail() {
        return senderemail;
    }

    public void setSenderemail(String senderemail) {
        this.senderemail = senderemail;
    }

    public String getTravelername() {
        return travelername;
    }

    public void setTravelername(String travelername) {
        this.travelername = travelername;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public Integer getDevCode() {
        return devCode;
    }

    public void setDevCode(Integer devCode) {
        this.devCode = devCode;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}