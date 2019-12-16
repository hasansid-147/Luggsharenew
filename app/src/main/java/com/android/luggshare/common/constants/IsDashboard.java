package com.android.luggshare.common.constants;

public class IsDashboard {

    private static IsDashboard instance;

    // Global variable
    private int data = 1;

    // Restrict the constructor from being instantiated
    private IsDashboard(){}

    public void setData(int d){
        this.data=d;
    }
    public int getData(){
        return this.data;
    }

    public static synchronized IsDashboard getInstance(){
        if(instance==null){
            instance=new IsDashboard();
        }
        return instance;
    }
}
