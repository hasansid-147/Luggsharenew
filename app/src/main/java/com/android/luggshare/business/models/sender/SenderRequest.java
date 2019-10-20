package com.android.luggshare.business.models.sender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderRequest {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("req_typ")
    @Expose
    private String reqTyp;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("size")
    @Expose
    private String size;
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
    @SerializedName("del_date")
    @Expose
    private String delDate;
    @SerializedName("Item_cat1")
    @Expose
    private String itemCat1;
    @SerializedName("Item_cat2")
    @Expose
    private Object itemCat2;
    @SerializedName("Item_cat3")
    @Expose
    private Object itemCat3;
    @SerializedName("Item_cat4")
    @Expose
    private Object itemCat4;
    @SerializedName("Item_cat5")
    @Expose
    private Object itemCat5;
    @SerializedName("Item_cat6")
    @Expose
    private Object itemCat6;

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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public String getItemCat1() {
        return itemCat1;
    }

    public void setItemCat1(String itemCat1) {
        this.itemCat1 = itemCat1;
    }

    public Object getItemCat2() {
        return itemCat2;
    }

    public void setItemCat2(Object itemCat2) {
        this.itemCat2 = itemCat2;
    }

    public Object getItemCat3() {
        return itemCat3;
    }

    public void setItemCat3(Object itemCat3) {
        this.itemCat3 = itemCat3;
    }

    public Object getItemCat4() {
        return itemCat4;
    }

    public void setItemCat4(Object itemCat4) {
        this.itemCat4 = itemCat4;
    }

    public Object getItemCat5() {
        return itemCat5;
    }

    public void setItemCat5(Object itemCat5) {
        this.itemCat5 = itemCat5;
    }

    public Object getItemCat6() {
        return itemCat6;
    }

    public void setItemCat6(Object itemCat6) {
        this.itemCat6 = itemCat6;
    }

}