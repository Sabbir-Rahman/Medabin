package com.example.medabinfinal.updateRecord.personalRecord.Database;

public class FoodModel {
    private long id;
    private String date;
    private int carbohydratesPercentage;
    private int proteinPercentage;
    private int fatPercetage;
    private int vitaminPercentage;
    private int waterPercentage;
    private int mineralPercentage;
    private int unknownPercentage;

    public FoodModel(long id, String date, int carbohydratesPercentage, int proteinPercentage, int fatPercetage, int vitaminPercentage, int mineralPercentage, int waterPercentage, int unknownPercentage) {
        this.id = id;
        this.date = date;
        this.carbohydratesPercentage = carbohydratesPercentage;
        this.proteinPercentage = proteinPercentage;
        this.fatPercetage = fatPercetage;
        this.vitaminPercentage = vitaminPercentage;
        this.mineralPercentage = mineralPercentage;
        this.waterPercentage = waterPercentage;
        this.unknownPercentage = unknownPercentage;
    }

    public FoodModel(String date, int carbohydratesPercentage, int proteinPercentage, int fatPercetage, int vitaminPercentage, int mineralPercentage,int waterPercentage, int unknownPercentage) {
        this.date = date;
        this.carbohydratesPercentage = carbohydratesPercentage;
        this.proteinPercentage = proteinPercentage;
        this.fatPercetage = fatPercetage;
        this.vitaminPercentage = vitaminPercentage;
        this.mineralPercentage = mineralPercentage;
        this.waterPercentage = waterPercentage;
        this.unknownPercentage = unknownPercentage;
    }

    public FoodModel() {
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



    public int getCarbohydratesPercentage() {
        return carbohydratesPercentage;
    }

    public void setCarbohydratesPercentage(int carbohydratesPercentage) {
        this.carbohydratesPercentage = carbohydratesPercentage;
    }

    public int getProteinPercentage() {
        return proteinPercentage;
    }

    public void setProteinPercentage(int proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public int getFatPercetage() {
        return fatPercetage;
    }

    public void setFatPercetage(int fatPercetage) {
        this.fatPercetage = fatPercetage;
    }

    public int getVitaminPercentage() {
        return vitaminPercentage;
    }

    public void setVitaminPercentage(int vitaminPercentage) {
        this.vitaminPercentage = vitaminPercentage;
    }

    public int getWaterPercentage() {
        return waterPercentage;
    }

    public void setWaterPercentage(int waterPercentage) {
        this.waterPercentage = waterPercentage;
    }

    public int getMineralPercentage() {
        return mineralPercentage;
    }

    public void setMineralPercentage(int mineralPercentage) {
        this.mineralPercentage = mineralPercentage;
    }

    public int getUnknownPercentage() {
        return unknownPercentage;
    }

    public void setUnknownPercentage(int unknownPercentage) {
        this.unknownPercentage = unknownPercentage;
    }
}
