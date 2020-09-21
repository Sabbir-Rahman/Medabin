package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class DiceaseModel {
    private long id;
    private String date,time;
    private String feelFever,feelStomachPain,feelBodyPain,feelHeadPain;

    public DiceaseModel(long id, String date, String time, String feelFever, String feelStomachPain, String feelBodyPain, String feelHeadPain) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.feelFever = feelFever;
        this.feelStomachPain = feelStomachPain;
        this.feelBodyPain = feelBodyPain;
        this.feelHeadPain = feelHeadPain;
    }

    public DiceaseModel(String date, String time, String feelFever, String feelStomachPain, String feelBodyPain, String feelHeadPain) {
        this.date = date;
        this.time = time;
        this.feelFever = feelFever;
        this.feelStomachPain = feelStomachPain;
        this.feelBodyPain = feelBodyPain;
        this.feelHeadPain = feelHeadPain;
    }

    public DiceaseModel() {
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

    public String getFeelFever() {
        return feelFever;
    }

    public void setFeelFever(String feelFever) {
        this.feelFever = feelFever;
    }

    public String getFeelStomachPain() {
        return feelStomachPain;
    }

    public void setFeelStomachPain(String feelStomachPain) {
        this.feelStomachPain = feelStomachPain;
    }

    public String getFeelBodyPain() {
        return feelBodyPain;
    }

    public void setFeelBodyPain(String feelBodyPain) {
        this.feelBodyPain = feelBodyPain;
    }

    public String getFeelHeadPain() {
        return feelHeadPain;
    }

    public void setFeelHeadPain(String feelHeadPain) {
        this.feelHeadPain = feelHeadPain;
    }
}
