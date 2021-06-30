package com.njupt.bean;

public class DeviceParam {
    private int id;
    private String devid;
    private String devName;
    private String ipAddr;
    private int devType;
    private String comType;
    private int duration;
    private int devState;
    private int dataFiledCount;
    private String dataFiledDesc;

    public DeviceParam() {
    }

    public DeviceParam(String devid, String devName, String ipAddr, int devType, String comType, int duration, int devState, int dataFiledCount, String dataFiledDesc) {
        this.devid = devid;
        this.devName = devName;
        this.ipAddr = ipAddr;
        this.devType = devType;
        this.comType = comType;
        this.duration = duration;
        this.devState = devState;
        this.dataFiledCount = dataFiledCount;
        this.dataFiledDesc = dataFiledDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDevid() {
        return devid;
    }

    public void setDevid(String devid) {
        this.devid = devid;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public int getDevType() {
        return devType;
    }

    public void setDevType(int devType) {
        this.devType = devType;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDevState() {
        return devState;
    }

    public void setDevState(int devState) {
        this.devState = devState;
    }

    public int getDataFiledCount() {
        return dataFiledCount;
    }

    public void setDataFiledCount(int dataFiledCount) {
        this.dataFiledCount = dataFiledCount;
    }

    public String getDataFiledDesc() {
        return dataFiledDesc;
    }

    public void setDataFiledDesc(String dataFiledDesc) {
        this.dataFiledDesc = dataFiledDesc;
    }

    @Override
    public String toString() {
        return "DeviceParam{" +
                "id=" + id +
                ", devid='" + devid + '\'' +
                ", devName='" + devName + '\'' +
                ", ipAddr='" + ipAddr + '\'' +
                ", devType=" + devType +
                ", comType='" + comType + '\'' +
                ", duration=" + duration +
                ", devState=" + devState +
                ", dataFiledCount=" + dataFiledCount +
                ", dataFiledDesc='" + dataFiledDesc + '\'' +
                '}';
    }
}
