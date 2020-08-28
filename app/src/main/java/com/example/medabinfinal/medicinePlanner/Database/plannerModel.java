package com.example.medabinfinal.medicinePlanner.Database;

public class plannerModel {

    private String name;
    private double quantity;
    private float ratePc;
    private double rateTtl;

    //constructor
    public plannerModel(String name, double quantity, float ratePc, double rateTtl) {
        this.name = name;
        this.quantity = quantity;
        this.ratePc = ratePc;
        this.rateTtl = rateTtl;
    }

    //general constructor\


    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public double getRateTtl() {
        return rateTtl;
    }

    public void setRateTtl(float rateTtl) {
        this.rateTtl = rateTtl;
    }

    public plannerModel() {
    }

    @Override
    public String toString() {
        return
                "Medicine name: " + name +"\n"+
                "Medicine quantity needed: " + String.format("%.0f",quantity)+"\n"+
                "Rate per pc=" + String.format("%.3f",ratePc) +" tk"+"\n\n"+
                "Total tk needed for this medicine: " + String.format("%.3f",rateTtl)+" tk"+"\n"
                ;
    }
}
