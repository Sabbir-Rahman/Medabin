package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class HeightModel {
    private long id;
    private String date;
    private int feet,inch;
    private float feetInch;

    public HeightModel(long id, String date, int feet, int inch) {
        this.id = id;
        this.date = date;
        this.feet = feet;
        this.inch = inch;
    }

    public HeightModel(String date, int feet, int inch) {
        this.date = date;
        this.feet = feet;
        this.inch = inch;
    }

    public HeightModel() {
    }

    public HeightModel(String date, int feet, int inch, float feetInch) {
        this.date = date;
        this.feet = feet;
        this.inch = inch;
        this.feetInch = feetInch;
    }

    public float getFeetInch() {
        return feetInch;
    }

    public void setFeetInch(float feetInch) {
        this.feetInch = feetInch;
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

    public int getFeet() {
        return feet;
    }

    public void setFeet(int feet) {
        this.feet = feet;
    }

    public int getInch() {
        return inch;
    }

    public void setInch(int inch) {
        this.inch = inch;
    }
}
