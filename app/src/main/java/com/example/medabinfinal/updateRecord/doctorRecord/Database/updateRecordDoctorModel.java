package com.example.medabinfinal.updateRecord.doctorRecord.Database;

import android.graphics.Bitmap;

public class updateRecordDoctorModel {

    private long id;
    private String doctorName,speciality,chamberLocation,symtomps;
    private Integer consultantFee;
    private String comments,visitDate;
    private Bitmap image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public updateRecordDoctorModel() {
    }

    public updateRecordDoctorModel(String doctorName, String speciality, String chamberLocation, String symtomps, Integer consultantFee, String comments, String visitDate, Bitmap image) {
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.chamberLocation = chamberLocation;
        this.symtomps = symtomps;
        this.consultantFee = consultantFee;
        this.comments = comments;
        this.visitDate = visitDate;
        this.image = image;
    }

    public updateRecordDoctorModel(long id, String doctorName, String speciality, String chamberLocation, String symtomps, Integer consultantFee, String comments, String visitDate, Bitmap image) {
        this.id = id;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.chamberLocation = chamberLocation;
        this.symtomps = symtomps;
        this.consultantFee = consultantFee;
        this.comments = comments;
        this.visitDate = visitDate;
        this.image = image;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getChamberLocation() {
        return chamberLocation;
    }

    public void setChamberLocation(String chamberLocation) {
        this.chamberLocation = chamberLocation;
    }

    public String getSymtomps() {
        return symtomps;
    }

    public void setSymtomps(String symtomps) {
        this.symtomps = symtomps;
    }

    public Integer getConsultantFee() {
        return consultantFee;
    }

    public void setConsultantFee(Integer consultantFee) {
        this.consultantFee = consultantFee;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
