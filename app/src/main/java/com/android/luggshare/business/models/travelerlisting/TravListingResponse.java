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
    private String userimage;
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
    private Object size;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("imagename")
    @Expose
    private String imagename;
    @SerializedName("prodimage")
    @Expose
    private String prodimage;
    @SerializedName("imagename2")
    @Expose
    private String imagename2;
    @SerializedName("prodimage2")
    @Expose
    private String prodimage2;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("delv_date")
    @Expose
    private String delvDate;

    /**
     * No args constructor for use in serialization
     *
     */
    public TravListingResponse() {
    }

    /**
     *
     * @param imagename2
     * @param userreq
     * @param weight
     * @param prodimage2
     * @param prodname
     * @param delvDate
     * @param prodimage
     * @param url
     * @param frmlocation
     * @param uid
     * @param tolocation
     * @param size
     * @param imagename
     * @param userimage
     * @param price
     * @param reqtype
     * @param bringersReward
     * @param username
     */
    public TravListingResponse(Integer uid, String username, String userimage, Integer userreq, String prodname, String frmlocation, String tolocation, Double bringersReward, Double price, String reqtype, Object size, Double weight, String imagename, String prodimage, String imagename2, String prodimage2, String url, String delvDate) {
        super();
        this.uid = uid;
        this.username = username;
        this.userimage = userimage;
        this.userreq = userreq;
        this.prodname = prodname;
        this.frmlocation = frmlocation;
        this.tolocation = tolocation;
        this.bringersReward = bringersReward;
        this.price = price;
        this.reqtype = reqtype;
        this.size = size;
        this.weight = weight;
        this.imagename = imagename;
        this.prodimage = prodimage;
        this.imagename2 = imagename2;
        this.prodimage2 = prodimage2;
        this.url = url;
        this.delvDate = delvDate;
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

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
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

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
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

    public String getImagename2() {
        return imagename2;
    }

    public void setImagename2(String imagename2) {
        this.imagename2 = imagename2;
    }

    public String getProdimage2() {
        return prodimage2;
    }

    public void setProdimage2(String prodimage2) {
        this.prodimage2 = prodimage2;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDelvDate() {
        return delvDate;
    }

    public void setDelvDate(String delvDate) {
        this.delvDate = delvDate;
    }

}