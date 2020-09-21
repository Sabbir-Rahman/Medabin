package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class GlucoseModel {
    private long id;
    private String date;
    private float beforeFast;
    private float afterFast;

    public GlucoseModel(long id, String date, float beforeFast, float afterFast) {
        this.id = id;
        this.date = date;
        this.beforeFast = beforeFast;
        this.afterFast = afterFast;
    }

    public GlucoseModel(String date, float beforeFast, float afterFast) {
        this.date = date;
        this.beforeFast = beforeFast;
        this.afterFast = afterFast;
    }

    public GlucoseModel() {
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

    public float getBeforeFast() {
        return beforeFast;
    }

    public void setBeforeFast(float beforeFast) {
        this.beforeFast = beforeFast;
    }

    public float getAfterFast() {
        return afterFast;
    }

    public void setAfterFast(float afterFast) {
        this.afterFast = afterFast;
    }
}
