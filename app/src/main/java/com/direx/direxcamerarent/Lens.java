package com.direx.direxcamerarent;

public class Lens {

    private String lensid;
    private String lenstitle;
    private String lensprice;
    private String lensdis;

    public Lens(){}

    public Lens(String lensid, String lenstitle, String lensprice, String lensdis) {
        this.lensid = lensid;
        this.lenstitle = lenstitle;
        this.lensprice = lensprice;
        this.lensdis = lensdis;
    }

    public String getLensid() {
        return lensid;
    }

    public void setLensid(String lensid) {
        this.lensid = lensid;
    }

    public String getLenstitle() {
        return lenstitle;
    }

    public void setLenstitle(String lenstitle) {
        this.lenstitle = lenstitle;
    }

    public String getLensprice() {
        return lensprice;
    }

    public void setLensprice(String lensprice) {
        this.lensprice = lensprice;
    }

    public String getLensdis() {
        return lensdis;
    }

    public void setLensdis(String lensdis) {
        this.lensdis = lensdis;
    }
}
