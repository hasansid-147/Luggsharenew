package com.android.luggshare.common.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SenderRequestBundle implements Serializable {

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
    @SerializedName("delivery_date")
    @Expose
    private String delivery_date;
    @SerializedName("arrival_date")
    @Expose
    private String arrival_date;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("item_1")
    @Expose
    private String item_1;
    @SerializedName("item_2")
    @Expose
    private String item_2;
    @SerializedName("item_3")
    @Expose
    private String item_3;
    @SerializedName("item_4")
    @Expose
    private String item_4;
    @SerializedName("item_5")
    @Expose
    private String item_5;
    @SerializedName("item_6")
    @Expose
    private String item_6;
    @SerializedName("img1")
    @Expose
    private String img1;
    @SerializedName("img_1_extension")
    @Expose
    private String img_1_extension;
    @SerializedName("img2")
    @Expose
    private String img2;
    @SerializedName("img_2_extension")
    @Expose
    private String img_2_extension;

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

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(String arrival_date) {
        this.arrival_date = arrival_date;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getItem_1() {
        return item_1;
    }

    public void setItem_1(String item_1) {
        this.item_1 = item_1;
    }

    public String getItem_2() {
        return item_2;
    }

    public void setItem_2(String item_2) {
        this.item_2 = item_2;
    }

    public String getItem_3() {
        return item_3;
    }

    public void setItem_3(String item_3) {
        this.item_3 = item_3;
    }

    public String getItem_4() {
        return item_4;
    }

    public void setItem_4(String item_4) {
        this.item_4 = item_4;
    }

    public String getItem_5() {
        return item_5;
    }

    public void setItem_5(String item_5) {
        this.item_5 = item_5;
    }

    public String getItem_6() {
        return item_6;
    }

    public void setItem_6(String item_6) {
        this.item_6 = item_6;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg_1_extension() {
        return img_1_extension;
    }

    public void setImg_1_extension(String img_1_extension) {
        this.img_1_extension = img_1_extension;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg_2_extension() {
        return img_2_extension;
    }

    public void setImg_2_extension(String img_2_extension) {
        this.img_2_extension = img_2_extension;
    }
}


