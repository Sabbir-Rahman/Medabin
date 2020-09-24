package com.example.medabinfinal.giveFeedback.Database;

public class ratingDoctorModel {
    private int id;
    private String name;
    private float behaviour,fee,prescription,diagnosis,total;

    public ratingDoctorModel(String name, float behaviour, float fee, float prescription, float diagnosis, float total) {
        this.name = name;
        this.behaviour = behaviour;
        this.fee = fee;
        this.prescription = prescription;
        this.diagnosis = diagnosis;
        this.total = total;
    }

    public ratingDoctorModel() {
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

    public float getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(float behaviour) {
        this.behaviour = behaviour;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public float getPrescription() {
        return prescription;
    }

    public void setPrescription(float prescription) {
        this.prescription = prescription;
    }

    public float getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(float diagnosis) {
        this.diagnosis = diagnosis;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
