package com.njupt.bean;

import java.util.Date;

public class TemHumData {
    private String devid;
    private Date time;
    private String ipAddr;
    private float temperature;
    private float humidity;

    public TemHumData() {
    }

    public TemHumData(String devid, Date time, String ipAddr, float temperature, float humidity) {
        this.devid = devid;
        this.time = time;
        this.ipAddr = ipAddr;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @Override
    public String toString() {
        return "TemHumData{" +
                "devId='" + devid + '\'' +
                ", time=" + time +
                ", ipAddr='" + ipAddr + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}
