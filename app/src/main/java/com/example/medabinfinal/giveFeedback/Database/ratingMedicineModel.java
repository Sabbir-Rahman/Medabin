package com.example.medabinfinal.giveFeedback.Database;

public class ratingMedicineModel {

    private int id;
    private String name;
    private float price,packaging,effectiveness,side,total;

    public ratingMedicineModel(String name, float price, float packaging, float effectiveness, float side, float total) {
        this.name = name;
        this.price = price;
        this.packaging = packaging;
        this.effectiveness = effectiveness;
        this.side = side;
        this.total = total;
    }

    public ratingMedicineModel() {
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPackaging() {
        return packaging;
    }

    public void setPackaging(float packaging) {
        this.packaging = packaging;
    }

    public float getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(float effectiveness) {
        this.effectiveness = effectiveness;
    }

    public float getSide() {
        return side;
    }

    public void setSide(float side) {
        this.side = side;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
