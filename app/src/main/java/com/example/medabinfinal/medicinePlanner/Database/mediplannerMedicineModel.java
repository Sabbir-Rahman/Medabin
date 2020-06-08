package com.example.medabinfinal.medicinePlanner.Database;

public class mediplannerMedicineModel {
    private int id;
    private String name,company;
    private int schedule;
    private float consume,rate;
    private boolean isActive;


    public mediplannerMedicineModel(int id, String name, String company, int schedule, float consume, float rate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.schedule = schedule;
        this.consume = consume;
        this.rate = rate;
        this.isActive = isActive;
    }

    public mediplannerMedicineModel() {

    }

    @Override
    public String toString() {
        return
                " Medicine id=" + id +
                "\n Medicine Name: " + name +
                "\n Medicine Company: " + company +
                "\n Medicine Schedule: " +"Repeat in "+ schedule +" day"+
                "\n Medicine consume: " + consume +" times in " +schedule+ " day" +
                "\n Medicine Rate: " + rate +" tk" + " per 1 pc"+
                "\n Currently Active Medicine? " + isActive +
                '\n'+'\n';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    public float getConsume() {
        return consume;
    }

    public void setConsume(float consume) {
        this.consume = consume;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
