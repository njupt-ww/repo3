package com.njupt.bean;

import java.util.Date;

public class ChaZuoData {
    private String devid;
    private Date time;
    private String ipAddr;
    private float temperature;
    private float humidity;
    private float current;
    private float voltage;
    private int onOff;

    public ChaZuoData() {
    }

    public ChaZuoData(String devid, Date time, String ipAddr, float temperature, float humidity, float current, float voltage, int onOff) {
        this.devid = devid;
        this.time = time;
        this.ipAddr = ipAddr;
        this.temperature = temperature;
        this.humidity = humidity;
        this.current = current;
        this.voltage = voltage;
        this.onOff = onOff;
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

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
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

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public int getOnOff() {
        return onOff;
    }

    public void setOnOff(int onOff) {
        this.onOff = onOff;
    }

    @Override
    public String toString() {
        return "ChaZuoData{" +
                "devId='" + devid + '\'' +
                ", time=" + time +
                ", ipAddr='" + ipAddr + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", current=" + current +
                ", voltage=" + voltage +
                ", onOff='" + onOff + '\'' +
                '}';
    }
}
