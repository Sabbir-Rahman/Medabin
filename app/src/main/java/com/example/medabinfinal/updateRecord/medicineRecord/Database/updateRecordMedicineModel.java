package com.example.medabinfinal.updateRecord.medicineRecord.Database;

public class updateRecordMedicineModel {

    private long id;
    private String medicineName,medicineType;
    private float times;
    private int days;
    private String morning,noon,night,others;
    private String reasonToTakeMedicine,prescribedBy,startDate,endDate;


    public updateRecordMedicineModel() {

    }

    public updateRecordMedicineModel(String medicineName, String medicineType, float times, int days, String morning, String noon, String night, String others, String reasonToTakeMedicine, String prescribedBy, String startDate, String endDate) {
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.times = times;
        this.days = days;
        this.morning = morning;
        this.noon = noon;
        this.night = night;
        this.others = others;
        this.reasonToTakeMedicine = reasonToTakeMedicine;
        this.prescribedBy = prescribedBy;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public updateRecordMedicineModel(long id, String medicineName, String medicineType, float times, int days, String morning, String noon, String night, String others, String reasonToTakeMedicine, String prescribedBy, String endDate) {
        this.id = id;
        this.medicineName = medicineName;
        this.medicineType = medicineType;
        this.times = times;
        this.days = days;
        this.morning = morning;
        this.noon = noon;
        this.night = night;
        this.others = others;
        this.reasonToTakeMedicine = reasonToTakeMedicine;
        this.prescribedBy = prescribedBy;
        this.endDate = endDate;
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

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public float getTimes() {
        return times;
    }

    public void setTimes(float times) {
        this.times = times;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getMorning() {
        return morning;
    }

    public void setMorning(String morning) {
        this.morning = morning;
    }

    public String getNoon() {
        return noon;
    }

    public void setNoon(String noon) {
        this.noon = noon;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getReasonToTakeMedicine() {
        return reasonToTakeMedicine;
    }

    public void setReasonToTakeMedicine(String reasonToTakeMedicine) {
        this.reasonToTakeMedicine = reasonToTakeMedicine;
    }

    public String getPrescribedBy() {
        return prescribedBy;
    }

    public void setPrescribedBy(String prescribedBy) {
        this.prescribedBy = prescribedBy;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }




}
