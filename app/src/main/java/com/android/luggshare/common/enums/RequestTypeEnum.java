package com.android.luggshare.common.enums;

import com.google.gson.annotations.SerializedName;

public enum RequestTypeEnum {

    @SerializedName("Sender")
    SENDER("Sender"),

    @SerializedName("TRAVELER")
    TRAVELER("TRAVELER"),


    @SerializedName("PURCHASER")
    PURCHASER("PURCHASER");



    private String string;

    RequestTypeEnum(String string) {
        this.string = string;
    }

    public String getReqType() {
        return string;
    }

    public static RequestTypeEnum fromString(String string) {
        if (string != null) {
            for (RequestTypeEnum buildType : RequestTypeEnum.values()) {
                if (string.equalsIgnoreCase(buildType.string)) return buildType;
            }
        }
        return null;
    }
}