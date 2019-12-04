package com.android.luggshare.business.models.editoffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditOfferRequest {

    @SerializedName("offerid")
    @Expose
    private Integer offerid;
    @SerializedName("from_reqid")
    @Expose
    private Integer fromReqid;
    @SerializedName("to_reqid")
    @Expose
    private Integer toReqid;
    @SerializedName("price")
    @Expose
    private Integer price;

    /**
     * No args constructor for use in serialization
     *
     */
    public EditOfferRequest() {
    }

    /**
     *
     * @param toReqid
     * @param price
     * @param offerid
     * @param fromReqid
     */
    public EditOfferRequest(Integer offerid, Integer fromReqid, Integer toReqid, Integer price) {
        super();
        this.offerid = offerid;
        this.fromReqid = fromReqid;
        this.toReqid = toReqid;
        this.price = price;
    }

    public Integer getOfferid() {
        return offerid;
    }

    public void setOfferid(Integer offerid) {
        this.offerid = offerid;
    }

    public Integer getFromReqid() {
        return fromReqid;
    }

    public void setFromReqid(Integer fromReqid) {
        this.fromReqid = fromReqid;
    }

    public Integer getToReqid() {
        return toReqid;
    }

    public void setToReqid(Integer toReqid) {
        this.toReqid = toReqid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
