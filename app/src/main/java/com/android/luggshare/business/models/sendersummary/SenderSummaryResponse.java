package com.android.luggshare.business.models.sendersummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderSummaryResponse {
/*
    @SerializedName("tot_kg")
    @Expose
    private Double totKg;
    @SerializedName("brk_up")
    @Expose
    private Double brkUp;
    @SerializedName("frst_thousnd")
    @Expose
    private Integer frstThousnd;
    @SerializedName("tot_cost")
    @Expose
    private Integer totCost;

    public Double getTotKg() {
        return totKg;
    }

    public void setTotKg(Double totKg) {
        this.totKg = totKg;
    }

    public Double getBrkUp() {
        return brkUp;
    }

    public void setBrkUp(Double brkUp) {
        this.brkUp = brkUp;
    }

    public Integer getFrstThousnd() {
        return frstThousnd;
    }

    public void setFrstThousnd(Integer frstThousnd) {
        this.frstThousnd = frstThousnd;
    }

    public Integer getTotCost() {
        return totCost;
    }

    public void setTotCost(Integer totCost) {
        this.totCost = totCost;
    }
*/

    @SerializedName("tot_kg")
    @Expose
    private Double totKg;
    @SerializedName("brk_up")
    @Expose
    private Double brkUp;
    @SerializedName("frst_thousnd")
    @Expose
    private Double frstThousnd;
    @SerializedName("tot_cost")
    @Expose
    private Double totCost;
    @SerializedName("delivery_reward")
    @Expose
    private Double deliveryReward;
    @SerializedName("service_fee")
    @Expose
    private Double serviceFee;

    /**
     * No args constructor for use in serialization
     *
     */
    public SenderSummaryResponse() {
    }

    /**
     *
     * @param serviceFee
     * @param totCost
     * @param brkUp
     * @param deliveryReward
     * @param totKg
     * @param frstThousnd
     */
    public SenderSummaryResponse(Double totKg, Double brkUp, Double frstThousnd, Double totCost, Double deliveryReward, Double serviceFee) {
        super();
        this.totKg = totKg;
        this.brkUp = brkUp;
        this.frstThousnd = frstThousnd;
        this.totCost = totCost;
        this.deliveryReward = deliveryReward;
        this.serviceFee = serviceFee;
    }

    public Double getTotKg() {
        return totKg;
    }

    public void setTotKg(Double totKg) {
        this.totKg = totKg;
    }

    public Double getBrkUp() {
        return brkUp;
    }

    public void setBrkUp(Double brkUp) {
        this.brkUp = brkUp;
    }

    public Double getFrstThousnd() {
        return frstThousnd;
    }

    public void setFrstThousnd(Double frstThousnd) {
        this.frstThousnd = frstThousnd;
    }

    public Double getTotCost() {
        return totCost;
    }

    public void setTotCost(Double totCost) {
        this.totCost = totCost;
    }

    public Double getDeliveryReward() {
        return deliveryReward;
    }

    public void setDeliveryReward(Double deliveryReward) {
        this.deliveryReward = deliveryReward;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }
}