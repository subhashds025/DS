package com.crm.payLoad;

import java.util.Date;

public class DetailsError {
    private Date date;
    private String mss;
    private String request;

    public DetailsError(String mss,Date date,String request) {
        this.mss = mss;
        this.date=date;
        this.request=request;
    }

   public String getMss() {
       return mss;
    }

    public Date getDate() {
        return date;
    }

    public String getRequest() {
        return request;
    }
}
