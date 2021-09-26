package com.direx.direxcamerarent;

public class Drone {

    private String droneid;
    private String title;
    private String price;
    private String description;
    public Drone(){}
    public Drone(String droneid, String title, String price, String description) {
        this.droneid = droneid;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getDroneid() {
        return droneid;
    }

    public void setDroneid(String droneid) {
        this.droneid = droneid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {

        this.price = price;
    }

     public String getDescription() { return description; }

     public void setDescription(String description) {
        this.description = description ; }



}