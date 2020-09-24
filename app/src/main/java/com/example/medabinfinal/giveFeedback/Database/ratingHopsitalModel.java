package com.example.medabinfinal.giveFeedback.Database;

public class ratingHopsitalModel {

    private int id;
    private String name;
    private float service,expense,infrastructure,testingQuality,total;

    public ratingHopsitalModel(String name, float service, float expense, float infrastructure, float testingQuality, float total) {
        this.name = name;
        this.service = service;
        this.expense = expense;
        this.infrastructure = infrastructure;
        this.testingQuality = testingQuality;
        this.total = total;
    }

    public ratingHopsitalModel() {
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

    public float getService() {
        return service;
    }

    public void setService(float service) {
        this.service = service;
    }

    public float getExpense() {
        return expense;
    }

    public void setExpense(float expense) {
        this.expense = expense;
    }

    public float getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(float infrastructure) {
        this.infrastructure = infrastructure;
    }

    public float getTestingQuality() {
        return testingQuality;
    }

    public void setTestingQuality(float testingQuality) {
        this.testingQuality = testingQuality;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
