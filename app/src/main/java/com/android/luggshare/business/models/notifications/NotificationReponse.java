package com.android.luggshare.business.models.notifications;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationReponse {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Recv_uid")
    @Expose
    private Integer recvUid;
    @SerializedName("Topic")
    @Expose
    private String topic;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("Date")
    @Expose
    private String date;

    /**
     * No args constructor for use in serialization
     *
     */
    public NotificationReponse() {
    }

    /**
     *
     * @param date
     * @param recvUid
     * @param topic
     * @param iD
     * @param desc
     */
    public NotificationReponse(Integer iD, Integer recvUid, String topic, String desc, String date) {
        super();
        this.iD = iD;
        this.recvUid = recvUid;
        this.topic = topic;
        this.desc = desc;
        this.date = date;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public Integer getRecvUid() {
        return recvUid;
    }

    public void setRecvUid(Integer recvUid) {
        this.recvUid = recvUid;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}