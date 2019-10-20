package com.android.luggshare.common.interfaces;

public interface onPickDateTimeListener {
    void onDateSelect(String date, String formatedDate, String delegate);
    void onTimeSelect(String time, String formatedTime, String delegate);
}
