package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class DailyTimeModel {
    private long id;
    private String date;
    private int sleepTime;
    private int readTime;
    private int workingTime;
    private int exerciseTime;
    private int others;
    private int unknown;

    public DailyTimeModel(long id, String date, int sleepTime, int readTime, int workingTime, int exerciseTime, int others,int unknown) {
        this.id = id;
        this.date = date;
        this.sleepTime = sleepTime;
        this.readTime = readTime;
        this.workingTime = workingTime;
        this.exerciseTime = exerciseTime;
        this.others = others;
        this.unknown = unknown;
    }

    public DailyTimeModel(String date, int sleepTime, int readTime, int workingTime, int exerciseTime, int others,int unknown) {
        this.date = date;
        this.sleepTime = sleepTime;
        this.readTime = readTime;
        this.workingTime = workingTime;
        this.exerciseTime = exerciseTime;
        this.others = others;
        this.unknown = unknown;
    }

    public DailyTimeModel() {
    }

    public int getUnknown() {
        return unknown;
    }

    public void setUnknown(int unknown) {
        this.unknown = unknown;
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

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getReadTime() {
        return readTime;
    }

    public void setReadTime(int readTime) {
        this.readTime = readTime;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public int getExerciseTime() {
        return exerciseTime;
    }

    public void setExerciseTime(int exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }
}
