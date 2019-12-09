package com.android.luggshare.business.models.getmyoffersreceived;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOffersReceivedListResponseModel {

    @SerializedName("trv_name")
    @Expose
    private String trvName;
    @SerializedName("offer_id")
    @Expose
    private Integer offerId;
    @SerializedName("trv_id")
    @Expose
    private Integer trvId;
    @SerializedName("SenderPurchaserid")
    @Expose
    private Integer senderPurchaserid;
    @SerializedName("travler_rating")
    @Expose
    private Integer travlerRating;
    @SerializedName("trv_req_id")
    @Expose
    private Integer trvReqId;
    @SerializedName("Departing_from")
    @Expose
    private String departingFrom;
    @SerializedName("Arrival_to")
    @Expose
    private String arrivalTo;
    @SerializedName("Arriv_date")
    @Expose
    private String arrivDate;
    @SerializedName("delv_Date")
    @Expose
    private String delvDate;
    @SerializedName("send_req_id")
    @Expose
    private Integer sendReqId;
    @SerializedName("item_name")
    @Expose
    private String itemName;
    @SerializedName("request_type")
    @Expose
    private String request_type;
    @SerializedName("offer_price")
    @Expose
    private Double offerPrice;
    @SerializedName("offer_Status")
    @Expose
    private String offerStatus;
    @SerializedName("is_rated")
    @Expose
    private Integer isRated;

    public String getTrvName() {
        return trvName;
    }

    public void setTrvName(String trvName) {
        this.trvName = trvName;
    }

    public Integer getOfferId() {
        return offerId;
    }

    public void setOfferId(Integer offerId) {
        this.offerId = offerId;
    }

    public Integer getTrvId() {
        return trvId;
    }

    public void setTrvId(Integer trvId) {
        this.trvId = trvId;
    }

    public Integer getSenderPurchaserid() {
        return senderPurchaserid;
    }

    public void setSenderPurchaserid(Integer senderPurchaserid) {
        this.senderPurchaserid = senderPurchaserid;
    }

    public Integer getTravlerRating() {
        return travlerRating;
    }

    public void setTravlerRating(Integer travlerRating) {
        this.travlerRating = travlerRating;
    }

    public Integer getTrvReqId() {
        return trvReqId;
    }

    public void setTrvReqId(Integer trvReqId) {
        this.trvReqId = trvReqId;
    }

    public String getDepartingFrom() {
        return departingFrom;
    }

    public void setDepartingFrom(String departingFrom) {
        this.departingFrom = departingFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public String getArrivDate() {
        return arrivDate;
    }

    public void setArrivDate(String arrivDate) {
        this.arrivDate = arrivDate;
    }

    public String getDelvDate() {
        return delvDate;
    }

    public void setDelvDate(String delvDate) {
        this.delvDate = delvDate;
    }

    public Integer getSendReqId() {
        return sendReqId;
    }

    public void setSendReqId(Integer sendReqId) {
        this.sendReqId = sendReqId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getRequest_type() {
        return request_type;
    }

    public void setRequest_type(String request_type) {
        this.request_type = request_type;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Integer getIsRated() {
        return isRated;
    }

    public void setIsRated(Integer isRated) {
        this.isRated = isRated;
    }
}
