package com.android.luggshare.common.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchaserRequestBundle implements Serializable {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("req_typ")
    @Expose
    private String reqTyp;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("prod_detail")
    @Expose
    private String prod_detail;
    @SerializedName("prod_name")
    @Expose
    private String prod_name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("from_country")
    @Expose
    private String from_country;
    @SerializedName("from_city")
    @Expose
    private String from_city;
    @SerializedName("to_country")
    @Expose
    private String to_country;
    @SerializedName("to_city")
    @Expose
    private String to_city;
    @SerializedName("del_date")
    @Expose
    private String del_date;
    @SerializedName("bringer_rewrd")
    @Expose
    private String bringer_rewrd;
    @SerializedName("img1")
    @Expose
    private String img1;
    @SerializedName("extension1")
    @Expose
    private String extension1;
    @SerializedName("img2")
    @Expose
    private String img2;
    @SerializedName("extension2")
    @Expose
    private String extension2;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReqTyp() {
        return reqTyp;
    }

    public void setReqTyp(String reqTyp) {
        this.reqTyp = reqTyp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProd_detail() {
        return prod_detail;
    }

    public void setProd_detail(String prod_detail) {
        this.prod_detail = prod_detail;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFrom_country() {
        return from_country;
    }

    public void setFrom_country(String from_country) {
        this.from_country = from_country;
    }

    public String getFrom_city() {
        return from_city;
    }

    public void setFrom_city(String from_city) {
        this.from_city = from_city;
    }

    public String getTo_country() {
        return to_country;
    }

    public void setTo_country(String to_country) {
        this.to_country = to_country;
    }

    public String getTo_city() {
        return to_city;
    }

    public void setTo_city(String to_city) {
        this.to_city = to_city;
    }

    public String getDel_date() {
        return del_date;
    }

    public void setDel_date(String del_date) {
        this.del_date = del_date;
    }

    public String getBringer_rewrd() {
        return bringer_rewrd;
    }

    public void setBringer_rewrd(String bringer_rewrd) {
        this.bringer_rewrd = bringer_rewrd;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getExtension1() {
        return extension1;
    }

    public void setExtension1(String extension1) {
        this.extension1 = extension1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getExtension2() {
        return extension2;
    }

    public void setExtension2(String extension2) {
        this.extension2 = extension2;
    }
}