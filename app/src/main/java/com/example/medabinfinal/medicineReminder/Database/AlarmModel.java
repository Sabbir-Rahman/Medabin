package com.example.medabinfinal.medicineReminder.Database;

public class AlarmModel {
    private long id;
    private String medicineName;
    private int count,remain;
    private String time1,time2,time3,isAlarm;
    private int time1_hour,time1_minute,time2_hour,time2_minute,time3_hour,time3_minute;

    public AlarmModel(long id, String medicineName, int count, int remain, String time1, String time2, String time3, String isAlarm, int time1_hour, int time1_minute, int time2_hour, int time2_minute, int time3_hour, int time3_minute) {
        this.id = id;
        this.medicineName = medicineName;
        this.count = count;
        this.remain = remain;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.isAlarm = isAlarm;
        this.time1_hour = time1_hour;
        this.time1_minute = time1_minute;
        this.time2_hour = time2_hour;
        this.time2_minute = time2_minute;
        this.time3_hour = time3_hour;
        this.time3_minute = time3_minute;
    }

    public AlarmModel(String medicineName, int count, int remain, String time1, String time2, String time3, String isAlarm, int time1_hour, int time1_minute, int time2_hour, int time2_minute, int time3_hour, int time3_minute) {
        this.medicineName = medicineName;
        this.count = count;
        this.remain = remain;
        this.time1 = time1;
        this.time2 = time2;
        this.time3 = time3;
        this.isAlarm = isAlarm;
        this.time1_hour = time1_hour;
        this.time1_minute = time1_minute;
        this.time2_hour = time2_hour;
        this.time2_minute = time2_minute;
        this.time3_hour = time3_hour;
        this.time3_minute = time3_minute;
    }

    public AlarmModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(String isAlarm) {
        this.isAlarm = isAlarm;
    }

    public int getTime1_hour() {
        return time1_hour;
    }

    public void setTime1_hour(int time1_hour) {
        this.time1_hour = time1_hour;
    }

    public int getTime1_minute() {
        return time1_minute;
    }

    public void setTime1_minute(int time1_minute) {
        this.time1_minute = time1_minute;
    }

    public int getTime2_hour() {
        return time2_hour;
    }

    public void setTime2_hour(int time2_hour) {
        this.time2_hour = time2_hour;
    }

    public int getTime2_minute() {
        return time2_minute;
    }

    public void setTime2_minute(int time2_minute) {
        this.time2_minute = time2_minute;
    }

    public int getTime3_hour() {
        return time3_hour;
    }

    public void setTime3_hour(int time3_hour) {
        this.time3_hour = time3_hour;
    }

    public int getTime3_minute() {
        return time3_minute;
    }

    public void setTime3_minute(int time3_minute) {
        this.time3_minute = time3_minute;
    }
}
