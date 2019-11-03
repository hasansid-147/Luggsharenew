package com.android.luggshare.common.enums;

import com.google.gson.annotations.SerializedName;

public enum RequestStatusEnum {

    @SerializedName("ACCEPTED")
    ACCEPTED("ACCEPTED"),

    @SerializedName("CREATED")
    CREATED("CREATED");

    private String string;

    RequestStatusEnum(String string) {
        this.string = string;
    }

    public String getReqType() {
        return string;
    }

    public static RequestStatusEnum fromString(String string) {
        if (string != null) {
            for (RequestStatusEnum buildType : RequestStatusEnum.values()) {
                if (string.equalsIgnoreCase(buildType.string)) return buildType;
            }
        }
        return null;
    }
}