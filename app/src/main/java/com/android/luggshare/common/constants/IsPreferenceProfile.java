package com.android.luggshare.common.constants;

public class IsPreferenceProfile {

    private static IsPreferenceProfile instance;

    // Global variable
    private boolean data = false;

    // Restrict the constructor from being instantiated
    private IsPreferenceProfile(){}

    public void setData(boolean d){
        this.data=d;
    }
    public boolean getData(){
        return this.data;
    }

    public static synchronized IsPreferenceProfile getInstance(){
        if(instance==null){
            instance=new IsPreferenceProfile();
        }
        return instance;
    }

}
