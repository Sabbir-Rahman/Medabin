package com.example.medabinfinal.updateRecord.hospitalRecord.Database;

public class HospitalRecordModel {

    private long id;
    private String hospitalName,admitReason,admitUnder,aboutCabinWard;
    private int totalCost;
    private String admitDate,releaseDate,comments;

    public HospitalRecordModel(long id, String hospitalName, String admitReason, String admitUnder, String aboutCabinWard, int totalCost, String admitDate, String releaseDate, String comments) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.admitReason = admitReason;
        this.admitUnder = admitUnder;
        this.aboutCabinWard = aboutCabinWard;
        this.totalCost = totalCost;
        this.admitDate = admitDate;
        this.releaseDate = releaseDate;
        this.comments = comments;
    }

    public HospitalRecordModel(String hospitalName, String admitReason, String admitUnder, String aboutCabinWard, int totalCost, String admitDate, String releaseDate, String comments) {
        this.hospitalName = hospitalName;
        this.admitReason = admitReason;
        this.admitUnder = admitUnder;
        this.aboutCabinWard = aboutCabinWard;
        this.totalCost = totalCost;
        this.admitDate = admitDate;
        this.releaseDate = releaseDate;
        this.comments = comments;
    }

    public HospitalRecordModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAdmitReason() {
        return admitReason;
    }

    public void setAdmitReason(String admitReason) {
        this.admitReason = admitReason;
    }

    public String getAdmitUnder() {
        return admitUnder;
    }

    public void setAdmitUnder(String admitUnder) {
        this.admitUnder = admitUnder;
    }

    public String getAboutCabinWard() {
        return aboutCabinWard;
    }

    public void setAboutCabinWard(String aboutCabinWard) {
        this.aboutCabinWard = aboutCabinWard;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(String admitDate) {
        this.admitDate = admitDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
