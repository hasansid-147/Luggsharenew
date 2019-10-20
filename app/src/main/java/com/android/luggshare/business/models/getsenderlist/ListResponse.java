package com.android.luggshare.business.models.getsenderlist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListResponse {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("req_id")
    @Expose
    private Integer reqId;
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
    @SerializedName("capacity")
    @Expose
    private Object capacity;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("dep_time")
    @Expose
    private Object depTime;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("req_type")
    @Expose
    private String reqType;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
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

    public Object getCapacity() {
        return capacity;
    }

    public void setCapacity(Object capacity) {
        this.capacity = capacity;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getDepTime() {
        return depTime;
    }

    public void setDepTime(Object depTime) {
        this.depTime = depTime;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getReqType() {
        return reqType;
    }

    public void setReqType(String reqType) {
        this.reqType = reqType;
    }

}
