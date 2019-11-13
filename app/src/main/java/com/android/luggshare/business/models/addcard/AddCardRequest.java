package com.android.luggshare.business.models.addcard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddCardRequest implements Serializable {

    @SerializedName("uid")
    @Expose
    private String uid;
    @SerializedName("credit_Card")
    @Expose
    private String creditCard;
    @SerializedName("card_name")
    @Expose
    private String cardName;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("bill_address")
    @Expose
    private String billAddress;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("cvv")
    @Expose
    private String cvv;
    @SerializedName("isvalid")
    @Expose
    private Integer isvalid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBillAddress() {
        return billAddress;
    }

    public void setBillAddress(String billAddress) {
        this.billAddress = billAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }
}
