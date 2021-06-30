package com.njupt.bean;

import java.util.Date;

public class AlarmHistory {
    private Date time;
    private String devid;
    private String devname;
    private String fieldname;
    private String fieldvalue;
    private String message;

    public AlarmHistory() {
    }

    public AlarmHistory(Date time, String devid, String devname, String fieldname, String fieldvalue, String message) {
        this.time = time;
        this.devid = devid;
        this.devname = devname;
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldvalue() {
        return fieldvalue;
    }

    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AlarmHistory{" +
                "time=" + time +
                ", devid='" + devid + '\'' +
                ", devname='" + devname + '\'' +
                ", fieldname='" + fieldname + '\'' +
                ", fieldvalue='" + fieldvalue + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
