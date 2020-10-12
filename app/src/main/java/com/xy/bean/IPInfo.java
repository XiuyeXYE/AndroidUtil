package com.xy.bean;

public class IPInfo {

    private int rs;
    private int code;
    private String address;
    private String ip;
    private int isDomain;

    public int getRs() {
        return rs;
    }

    public void setRs(int rs) {
        this.rs = rs;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getIsDomain() {
        return isDomain;
    }

    public void setIsDomain(int isDomain) {
        this.isDomain = isDomain;
    }

    @Override
    public String toString() {
        return "IPInfo{" +
                "rs=" + rs +
                ", code=" + code +
                ", address='" + address + '\'' +
                ", ip='" + ip + '\'' +
                ", isDomain=" + isDomain +
                '}';
    }
}
