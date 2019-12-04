package com.android.luggshare.business.models.getmyofferspending;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOffersPendingListResponseModel {


    @SerializedName("offerid")
    @Expose
    private Integer offerid;
    @SerializedName("rcv_name")
    @Expose
    private String rcvName;
    @SerializedName("rcvId")
    @Expose
    private Integer rcvId;
    @SerializedName("rcv_reqid")
    @Expose
    private Integer rcvReqid;
    @SerializedName("from_reqid")
    @Expose
    private Integer fromReqid;
    @SerializedName("rcv_packag_name")
    @Expose
    private String rcvPackagName;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("rcv_fromloc")
    @Expose
    private String rcvFromloc;
    @SerializedName("rcv_toloc")
    @Expose
    private String rcvToloc;
    @SerializedName("offerdate")
    @Expose
    private String offerdate;
    @SerializedName("rcv_delvdate")
    @Expose
    private String rcvDelvdate;
    @SerializedName("Item_price")
    @Expose
    private Double itemPrice;
    @SerializedName("offer_price")
    @Expose
    private Double offerPrice;
    @SerializedName("delv_type")
    @Expose
    private String delvType;
    @SerializedName("offer_Status")
    @Expose
    private String offerStatus;

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }


    public String getRcvName() {
        return rcvName;
    }

    public void setRcvName(String rcvName) {
        this.rcvName = rcvName;
    }

    public Integer getRcvId() {
        return rcvId;
    }

    public void setRcvId(Integer rcvId) {
        this.rcvId = rcvId;
    }

    public Integer getRcvReqid() {
        return rcvReqid;
    }

    public void setRcvReqid(Integer rcvReqid) {
        this.rcvReqid = rcvReqid;
    }

    public Integer getFromReqid() {
        return fromReqid;
    }

    public void setFromReqid(Integer fromReqid) {
        this.fromReqid = fromReqid;
    }

    public String getRcvPackagName() {
        return rcvPackagName;
    }

    public void setRcvPackagName(String rcvPackagName) {
        this.rcvPackagName = rcvPackagName;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    public String getRcvFromloc() {
        return rcvFromloc;
    }

    public void setRcvFromloc(String rcvFromloc) {
        this.rcvFromloc = rcvFromloc;
    }

    public String getRcvToloc() {
        return rcvToloc;
    }

    public void setRcvToloc(String rcvToloc) {
        this.rcvToloc = rcvToloc;
    }

    public String getOfferdate() {
        return offerdate;
    }

    public void setOfferdate(String offerdate) {
        this.offerdate = offerdate;
    }

    public String getRcvDelvdate() {
        return rcvDelvdate;
    }

    public void setRcvDelvdate(String rcvDelvdate) {
        this.rcvDelvdate = rcvDelvdate;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Double getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Double offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getDelvType() {
        return delvType;
    }

    public void setDelvType(String delvType) {
        this.delvType = delvType;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }
}
