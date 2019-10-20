package com.android.luggshare.business.models.traveleroffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TravelerOfferRequest {

    @SerializedName("from_uid")
    @Expose
    private String fromUid;
    @SerializedName("to_uid")
    @Expose
    private String toUid;
    @SerializedName("from_reqid")
    @Expose
    private String fromReqid;
    @SerializedName("to_reqid")
    @Expose
    private String toReqid;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("to_reqType")
    @Expose
    private String toReqType;
    @SerializedName("is_dsb")
    @Expose
    private Integer isDsb;
    @SerializedName("req_typ")
    @Expose
    private String reqTyp;
    @SerializedName("dep_from_country")
    @Expose
    private String depFromCountry;
    @SerializedName("dep_from_city")
    @Expose
    private String depFromCity;
    @SerializedName("arrv_to_country")
    @Expose
    private String arrvToCountry;
    @SerializedName("arrv_to_city")
    @Expose
    private String arrvToCity;
    @SerializedName("bag_capacity")
    @Expose
    private Double bagCapacity;
    @SerializedName("pref_item1")
    @Expose
    private String prefItem1;
    @SerializedName("pref_item2")
    @Expose
    private String prefItem2;
    @SerializedName("pref_item3")
    @Expose
    private String prefItem3;
    @SerializedName("ask_price")
    @Expose
    private Integer askPrice;
    @SerializedName("dep_time")
    @Expose
    private String depTime;
    @SerializedName("exp_arrivaltime")
    @Expose
    private String expArrivaltime;

    public String getFromUid() {
        return fromUid;
    }

    public void setFromUid(String fromUid) {
        this.fromUid = fromUid;
    }

    public String getToUid() {
        return toUid;
    }

    public void setToUid(String toUid) {
        this.toUid = toUid;
    }

    public String getFromReqid() {
        return fromReqid;
    }

    public void setFromReqid(String fromReqid) {
        this.fromReqid = fromReqid;
    }

    public String getToReqid() {
        return toReqid;
    }

    public void setToReqid(String toReqid) {
        this.toReqid = toReqid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getToReqType() {
        return toReqType;
    }

    public void setToReqType(String toReqType) {
        this.toReqType = toReqType;
    }

    public Integer getIsDsb() {
        return isDsb;
    }

    public void setIsDsb(Integer isDsb) {
        this.isDsb = isDsb;
    }

    public String getReqTyp() {
        return reqTyp;
    }

    public void setReqTyp(String reqTyp) {
        this.reqTyp = reqTyp;
    }

    public String getDepFromCountry() {
        return depFromCountry;
    }

    public void setDepFromCountry(String depFromCountry) {
        this.depFromCountry = depFromCountry;
    }

    public String getDepFromCity() {
        return depFromCity;
    }

    public void setDepFromCity(String depFromCity) {
        this.depFromCity = depFromCity;
    }

    public String getArrvToCountry() {
        return arrvToCountry;
    }

    public void setArrvToCountry(String arrvToCountry) {
        this.arrvToCountry = arrvToCountry;
    }

    public String getArrvToCity() {
        return arrvToCity;
    }

    public void setArrvToCity(String arrvToCity) {
        this.arrvToCity = arrvToCity;
    }

    public Double getBagCapacity() {
        return bagCapacity;
    }

    public void setBagCapacity(Double bagCapacity) {
        this.bagCapacity = bagCapacity;
    }

    public String getPrefItem1() {
        return prefItem1;
    }

    public void setPrefItem1(String prefItem1) {
        this.prefItem1 = prefItem1;
    }

    public String getPrefItem2() {
        return prefItem2;
    }

    public void setPrefItem2(String prefItem2) {
        this.prefItem2 = prefItem2;
    }

    public String getPrefItem3() {
        return prefItem3;
    }

    public void setPrefItem3(String prefItem3) {
        this.prefItem3 = prefItem3;
    }

    public Integer getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(Integer askPrice) {
        this.askPrice = askPrice;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getExpArrivaltime() {
        return expArrivaltime;
    }

    public void setExpArrivaltime(String expArrivaltime) {
        this.expArrivaltime = expArrivaltime;
    }

}
