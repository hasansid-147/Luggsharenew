package com.android.luggshare.business.models.purchasersummary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaserSummaryRequest {

    @SerializedName("prod_price")
    @Expose
    private Double prodPrice;
    @SerializedName("qunatity")
    @Expose
    private Integer qunatity;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     * No args constructor for use in serialization
     *
     */
    public PurchaserSummaryRequest() {
    }

    /**
     *
     * @param qunatity
     * @param prodPrice
     * @param currency
     */
    public PurchaserSummaryRequest(Double prodPrice, Integer qunatity, String currency) {
        super();
        this.prodPrice = prodPrice;
        this.qunatity = qunatity;
        this.currency = currency;
    }

    public Double getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Double prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getQunatity() {
        return qunatity;
    }

    public void setQunatity(Integer qunatity) {
        this.qunatity = qunatity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
