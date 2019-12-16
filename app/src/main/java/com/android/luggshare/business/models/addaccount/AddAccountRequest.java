package com.android.luggshare.business.models.addaccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAccountRequest {

    @SerializedName("uid")
    @Expose
    private Integer uid;
    @SerializedName("AccNumber")
    @Expose
    private String accNumber;
    @SerializedName("Acct_holder_name")
    @Expose
    private String acctHolderName;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("swift_code")
    @Expose
    private String swiftCode;
    @SerializedName("isvalid")
    @Expose
    private Integer isvalid;

    /**
     * No args constructor for use in serialization
     *
     */
    public AddAccountRequest() {
    }

    /**
     *
     * @param uid
     * @param accNumber
     * @param acctHolderName
     * @param swiftCode
     * @param currency
     * @param isvalid
     */
    public AddAccountRequest(Integer uid, String accNumber, String acctHolderName, String currency, String swiftCode, Integer isvalid) {
        super();
        this.uid = uid;
        this.accNumber = accNumber;
        this.acctHolderName = acctHolderName;
        this.currency = currency;
        this.swiftCode = swiftCode;
        this.isvalid = isvalid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getAcctHolderName() {
        return acctHolderName;
    }

    public void setAcctHolderName(String acctHolderName) {
        this.acctHolderName = acctHolderName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

}