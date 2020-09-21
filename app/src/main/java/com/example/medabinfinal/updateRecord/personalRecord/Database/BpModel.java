package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class BpModel {
    private long id;
    private String date,time;
    private int high,low;

    public BpModel(long id, String date, String time, int high, int low) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.high = high;
        this.low = low;
    }

    public BpModel(String date, String time, int high, int low) {
        this.date = date;
        this.time = time;
        this.high = high;
        this.low = low;
    }

    public BpModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }
}
