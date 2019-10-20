package com.android.luggshare.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.android.luggshare.R;
import com.android.luggshare.common.interfaces.onPickDateTimeListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.android.luggshare.utils.DateFormatter.TRANSACTION_DATE_FORMAT;
import static com.android.luggshare.utils.DateFormatter.UI_DATE_FORMAT;


public class DateTimePicker {

    private Date DateSelected;
    private Date TimeSelected;
    String dateStr = "";
    String timeStr = "";
    String dateTimeStr = "";
    private onPickDateTimeListener pickDateTimeListener;
    private String delegate;
    Context ctx;

    public static DateTimePicker newInstance() {
        return new DateTimePicker();
    }

    public void initDatePicker(final Context _ctx, final String _delegate) {

        this.ctx = _ctx;
        dateStr = "";
        timeStr = "";
        dateTimeStr = "";

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(ctx, R.style.datePickerStyle, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                Date date = new Date();
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // and get that as a Date
                Date dateSpecified = c.getTime();
                if (dateSpecified.before(date)) {
                    //UIHelper.showShortToastInCenter(_ctx, "Selected Date is in Past");
                    Toast.makeText(_ctx, "Selected Date is in Past", Toast.LENGTH_SHORT).show();
                } else {
                    DateSelected = dateSpecified;
                    //String predate = new SimpleDateFormat("EEE,MMM d").format(c.getTime());
                    String predate = new SimpleDateFormat(UI_DATE_FORMAT).format(c.getTime());
                    //String predateNew = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                    String predateNew = new SimpleDateFormat(TRANSACTION_DATE_FORMAT).format(c.getTime());

                    pickDateTimeListener.onDateSelect(predate, predateNew, _delegate);
                }

            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        Calendar min = Calendar.getInstance();
        min.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(min.getTimeInMillis());
        Calendar max = Calendar.getInstance();
        max.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH) + 30);
        //dialog.getDatePicker().setMaxDate(max.getTimeInMillis());
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(_ctx, R.color.colorWhite));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(ContextCompat.getColor(_ctx, R.color.colorPrimary));
    }

    public void initTimePicker(final Context _ctx, final String _delegate) {
        this.ctx = _ctx;
        if (DateSelected != null) {
            final Calendar calendar = Calendar.getInstance();
            final Calendar cal = Calendar.getInstance();
            cal.setTime(DateSelected);
            TimePickerDialog dialog = new TimePickerDialog(ctx, R.style.datePickerStyle, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Date date = new Date();
                    if (DateHelper.isSameDay(DateSelected, date) && !DateHelper.isTimeAfter(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE) + 30, hourOfDay, minute)) {
                        //UIHelper.showShortToastInCenter(getContext(), "Selected Time is Less then Current Time ");
                        //UIHelper.showShortToastInCenter(ctx, "Selected Time should be atleast 30 minutes greater than the current time.");
                        Toast.makeText(_ctx, "Selected Time is Less then Current Time ", Toast.LENGTH_SHORT).show();
                    } else {
                        Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        c.set(year, month, day, hourOfDay, minute);
                        TimeSelected = c.getTime();
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, month, day, hourOfDay, minute + 15);
                        /*String preTime = new SimpleDateFormat("HH:mm a").format(c.getTime()) + " - " +
                                new SimpleDateFormat("HH:mm a").format(cal.getTime());*/
                        String preTime = new SimpleDateFormat("HH:mm a").format(c.getTime());
                        String preTimeNew = new SimpleDateFormat("HH:mm:ss").format(c.getTime());

                        pickDateTimeListener.onTimeSelect(preTime, preTimeNew, _delegate);
                    }
                }
            }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);

            dialog.show();
            dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(_ctx, R.color.colorWhite));
            dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(ContextCompat.getColor(_ctx, R.color.colorPrimary));
        } else {
            //UIHelper.showShortToastInCenter(_ctx, "Select Date First");
            Toast.makeText(_ctx, "Select Date First", Toast.LENGTH_SHORT).show();
        }
    }

    public void initInDependentTimePicker(Context _ctx, final String _delegate) {
        this.ctx = _ctx;
        //if (DateSelected != null) {
        final Calendar calendar = Calendar.getInstance();
        final Calendar cal = Calendar.getInstance();
        //cal.setTime(DateSelected);
        TimePickerDialog dialog = new TimePickerDialog(ctx, R.style.datePickerStyle, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Date date = new Date();
                    /*if (DateHelper.isSameDay(DateSelected, date) && !DateHelper.isTimeAfter(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE) + 30, hourOfDay, minute)) {
                        //UIHelper.showShortToastInCenter(getContext(), "Selected Time is Less then Current Time ");
                        UIHelper.showShortToastInCenter(ctx, "Selected Time should be atleast 30 minutes greater than the current time.");
                    } else {*/
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                c.set(year, month, day, hourOfDay, minute);
                TimeSelected = c.getTime();
                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day, hourOfDay, minute + 15);
                        /*String preTime = new SimpleDateFormat("HH:mm a").format(c.getTime()) + " - " +
                                new SimpleDateFormat("HH:mm a").format(cal.getTime());*/
                String preTime = new SimpleDateFormat("HH:mm a").format(c.getTime());
                String preTimeNew = new SimpleDateFormat("HH:mm:ss").format(c.getTime());

                pickDateTimeListener.onTimeSelect(preTime, preTimeNew, _delegate);
                //}
            }
        }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), false);

        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(_ctx, R.color.colorWhite));
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(ContextCompat.getColor(_ctx, R.color.colorPrimary));
        /*} else {
            UIHelper.showShortToastInCenter(CustomApplication.getContext(), "Select Date First");
        }*/
    }

    public void setListener(onPickDateTimeListener listener, String delegate) {
        this.pickDateTimeListener = listener;
        this.delegate = delegate;
    }

}
