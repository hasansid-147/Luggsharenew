package com.android.luggshare.business.models.purchasersummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaserSummaryResponse {

    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("total_price")
    @Expose
    private Double totalPrice;
    @SerializedName("reward")
    @Expose
    private Double reward;
    @SerializedName("estimated_total")
    @Expose
    private Double estimatedTotal;

    /**
     * No args constructor for use in serialization
     *
     */
    public PurchaserSummaryResponse() {
    }

    /**
     *
     * @param reward
     * @param quantity
     * @param estimatedTotal
     * @param totalPrice
     */
    public PurchaserSummaryResponse(Integer quantity, Double totalPrice, Double reward, Double estimatedTotal) {
        super();
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.reward = reward;
        this.estimatedTotal = estimatedTotal;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getReward() {
        return reward;
    }

    public void setReward(Double reward) {
        this.reward = reward;
    }

    public Double getEstimatedTotal() {
        return estimatedTotal;
    }

    public void setEstimatedTotal(Double estimatedTotal) {
        this.estimatedTotal = estimatedTotal;
    }

}
