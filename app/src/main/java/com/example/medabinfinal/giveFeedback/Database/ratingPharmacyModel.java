package com.example.medabinfinal.giveFeedback.Database;

public class ratingPharmacyModel {

    private int id;
    private String name;
    private float service,pricing,wellOrganised,medicineAvailability,total;

    public ratingPharmacyModel() {
    }

    public ratingPharmacyModel(String name, float service, float pricing, float wellOrganised, float medicineAvailability, float total) {
        this.name = name;
        this.service = service;
        this.pricing = pricing;
        this.wellOrganised = wellOrganised;
        this.medicineAvailability = medicineAvailability;
        this.total = total;
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

    public float getPricing() {
        return pricing;
    }

    public void setPricing(float pricing) {
        this.pricing = pricing;
    }

    public float getWellOrganised() {
        return wellOrganised;
    }

    public void setWellOrganised(float wellOrganised) {
        this.wellOrganised = wellOrganised;
    }

    public float getMedicineAvailability() {
        return medicineAvailability;
    }

    public void setMedicineAvailability(float medicineAvailability) {
        this.medicineAvailability = medicineAvailability;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
