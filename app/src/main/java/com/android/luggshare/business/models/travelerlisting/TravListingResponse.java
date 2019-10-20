package com.android.luggshare.business.models.travelerlisting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TravListingResponse implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("userimage")
    @Expose
    private Object userimage;
    @SerializedName("userreq")
    @Expose
    private Integer userreq;
    @SerializedName("prodname")
    @Expose
    private String prodname;
    @SerializedName("frmlocation")
    @Expose
    private String frmlocation;
    @SerializedName("tolocation")
    @Expose
    private String tolocation;
    @SerializedName("bringers_reward")
    @Expose
    private Double bringersReward;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("reqtype")
    @Expose
    private String reqtype;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("imagename")
    @Expose
    private String imagename;
    @SerializedName("prodimage")
    @Expose
    private String prodimage;
    @SerializedName("delv_date")
    @Expose
    private String delvDate;

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

    public Object getUserimage() {
        return userimage;
    }

    public void setUserimage(Object userimage) {
        this.userimage = userimage;
    }

    public Integer getUserreq() {
        return userreq;
    }

    public void setUserreq(Integer userreq) {
        this.userreq = userreq;
    }

    public String getProdname() {
        return prodname;
    }

    public void setProdname(String prodname) {
        this.prodname = prodname;
    }

    public String getFrmlocation() {
        return frmlocation;
    }

    public void setFrmlocation(String frmlocation) {
        this.frmlocation = frmlocation;
    }

    public String getTolocation() {
        return tolocation;
    }

    public void setTolocation(String tolocation) {
        this.tolocation = tolocation;
    }

    public Double getBringersReward() {
        return bringersReward;
    }

    public void setBringersReward(Double bringersReward) {
        this.bringersReward = bringersReward;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getReqtype() {
        return reqtype;
    }

    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
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

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public String getProdimage() {
        return prodimage;
    }

    public void setProdimage(String prodimage) {
        this.prodimage = prodimage;
    }

    public String getDelvDate() {
        return delvDate;
    }

    public void setDelvDate(String delvDate) {
        this.delvDate = delvDate;
    }

}