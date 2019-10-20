package com.android.luggshare.business.models.sendersummary;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenderSummaryRequest {

    @SerializedName("prod_weight")
    @Expose
    private Double prodWeight;
    @SerializedName("size")
    @Expose
    private Integer size;

    public Double getProdWeight() {
        return prodWeight;
    }

    public void setProdWeight(Double prodWeight) {
        this.prodWeight = prodWeight;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}