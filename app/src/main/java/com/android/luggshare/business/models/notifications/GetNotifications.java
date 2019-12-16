package com.android.luggshare.business.models.notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetNotifications {

    @SerializedName("recv_uid")
    @Expose
    private Integer recvUid;

    /**
     * No args constructor for use in serialization
     *
     */
    public GetNotifications() {
    }

    /**
     *
     * @param recvUid
     */
    public GetNotifications(Integer recvUid) {
        super();
        this.recvUid = recvUid;
    }

    public Integer getRecvUid() {
        return recvUid;
    }

    public void setRecvUid(Integer recvUid) {
        this.recvUid = recvUid;
    }

}
