package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class WeightModel {
    private long id;
    private String date;
    private float weightKg;

    public WeightModel(long id, String date, float weightKg) {
        this.id = id;
        this.date = date;
        this.weightKg = weightKg;
    }

    public WeightModel(String date, float weightKg) {
        this.date = date;
        this.weightKg = weightKg;
    }

    public WeightModel() {
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

    public float getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(float weightKg) {
        this.weightKg = weightKg;
    }
}
