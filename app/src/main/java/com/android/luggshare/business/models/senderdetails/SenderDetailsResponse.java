package com.android.luggshare.business.models.senderdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SenderDetailsResponse implements Serializable {

    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("from_country")
    @Expose
    private String fromCountry;
    @SerializedName("from_city")
    @Expose
    private String fromCity;
    @SerializedName("to_country")
    @Expose
    private String toCountry;
    @SerializedName("to_city")
    @Expose
    private String toCity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("reward")
    @Expose
    private String reward;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("del_date")
    @Expose
    private String delDate;
    @SerializedName("total_offer_count")
    @Expose
    private Integer totalOfferCount;
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("image_loc")
    @Expose
    private String imageLoc;
    @SerializedName("image_name")
    @Expose
    private String imageName;

    @SerializedName("image_loc2")
    @Expose
    private String imageLoc2;
    @SerializedName("image_name2")
    @Expose
    private String imageName2;

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromCountry() {
        return fromCountry;
    }

    public void setFromCountry(String fromCountry) {
        this.fromCountry = fromCountry;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCountry() {
        return toCountry;
    }

    public void setToCountry(String toCountry) {
        this.toCountry = toCountry;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public Integer getTotalOfferCount() {
        return totalOfferCount;
    }

    public void setTotalOfferCount(Integer totalOfferCount) {
        this.totalOfferCount = totalOfferCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageLoc() {
        return imageLoc;
    }

    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }

    public String getImageLoc2() {
        return imageLoc2;
    }

    public void setImageLoc2(String imageLoc2) {
        this.imageLoc2 = imageLoc2;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


}