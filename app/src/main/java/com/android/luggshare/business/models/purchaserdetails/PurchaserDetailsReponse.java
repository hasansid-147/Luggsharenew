package com.android.luggshare.business.models.purchaserdetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchaserDetailsReponse implements Serializable {
    @SerializedName("req_id")
    @Expose
    private Integer reqId;
    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("prod_name")
    @Expose
    private String prodName;
    @SerializedName("prod_detail")
    @Expose
    private String prodDetail;
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
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("quant")
    @Expose
    private Integer quant;
    @SerializedName("del_date")
    @Expose
    private String delDate;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("reward")
    @Expose
    private Double reward;
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

    /**
     * No args constructor for use in serialization
     *
     */
    public PurchaserDetailsReponse() {
    }

    /**
     *
     * @param reward
     * @param fromCountry
     * @param delDate
     * @param imageName
     * @param imageName2
     * @param imageLoc
     * @param toCity
     * @param imageLoc2
     * @param quant
     * @param url
     * @param reqId
     * @param toCountry
     * @param uid
     * @param prodDetail
     * @param price
     * @param prodName
     * @param fromCity
     * @param username
     * @param totalOfferCount
     * @param status
     */
    public PurchaserDetailsReponse(Integer reqId, Integer uid, String username, String prodName, String prodDetail, String fromCountry, String fromCity, String toCountry, String toCity, String url, Integer quant, String delDate, Double price, Double reward, Integer totalOfferCount, String status, String imageLoc, String imageName, String imageLoc2, String imageName2) {
        super();
        this.reqId = reqId;
        this.uid = uid;
        this.username = username;
        this.prodName = prodName;
        this.prodDetail = prodDetail;
        this.fromCountry = fromCountry;
        this.fromCity = fromCity;
        this.toCountry = toCountry;
        this.toCity = toCity;
        this.url = url;
        this.quant = quant;
        this.delDate = delDate;
        this.price = price;
        this.reward = reward;
        this.totalOfferCount = totalOfferCount;
        this.status = status;
        this.imageLoc = imageLoc;
        this.imageName = imageName;
        this.imageLoc2 = imageLoc2;
        this.imageName2 = imageName2;
    }

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

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDetail() {
        return prodDetail;
    }

    public void setProdDetail(String prodDetail) {
        this.prodDetail = prodDetail;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageLoc2() {
        return imageLoc2;
    }

    public void setImageLoc2(String imageLoc2) {
        this.imageLoc2 = imageLoc2;
    }

    public String getImageName2() {
        return imageName2;
    }

    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }


}
