package com.android.luggshare.common.bundle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClaimBundle implements Serializable {

    @SerializedName("offerid")
    @Expose
    private Integer offerid;
    @SerializedName("item")
    @Expose
    private String item;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("userid")
    @Expose
    private Integer userid;

    /**
     * No args constructor for use in serialization
     *
     */
    public ClaimBundle() {
    }

    /**
     *
     * @param date
     * @param item
     * @param amount
     * @param detail
     */
    public ClaimBundle(Integer offerid, String item, String date, Double amount, String detail, String username,Integer userid) {
        super();
        this.offerid = offerid;
        this.item = item;
        this.date = date;
        this.amount = amount;
        this.detail = detail;
        this.username = username;
        this.userid = userid;
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid){
        this.offerid = offerid;
    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid){
        this.userid = userid;
    }

}
