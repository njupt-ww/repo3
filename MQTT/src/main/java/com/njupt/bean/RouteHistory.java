package com.njupt.bean;

import java.util.Date;

public class RouteHistory {
    private Date time;
    private String devid;
    private int devType;
    private String  agentid;
    private String controllerid;

    public RouteHistory() {
    }

    public RouteHistory(Date time, String devid, int devType, String agentid, String controllerid) {
        this.time = time;
        this.devid = devid;
        this.devType = devType;
        this.agentid = agentid;
        this.controllerid = controllerid;
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

    public int getDevType() {
        return devType;
    }

    public void setDevType(int devType) {
        this.devType = devType;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getControllerid() {
        return controllerid;
    }

    public void setControllerid(String controllerid) {
        this.controllerid = controllerid;
    }

    @Override
    public String toString() {
        return "RouteHistory{" +
                "time=" + time +
                ", devid='" + devid + '\'' +
                ", devType=" + devType +
                ", agentid='" + agentid + '\'' +
                ", controllerid='" + controllerid + '\'' +
                '}';
    }
}
