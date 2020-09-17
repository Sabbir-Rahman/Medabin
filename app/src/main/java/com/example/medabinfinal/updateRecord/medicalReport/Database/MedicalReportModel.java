package com.example.medabinfinal.updateRecord.medicalReport.Database;

import android.graphics.Bitmap;

public class MedicalReportModel {

    private long id;
    private String reportName,prescribedPeople,diagnosticName,reprotDetails;
    private int testCost;
    private String recordComment,reportDate;
    private Bitmap imageReport;

    public MedicalReportModel(long id, String reportName, String prescribedPeople, String diagnosticName, String reprotDetails, int testCost, String recordComment, String date, Bitmap imageReport) {
        this.id = id;
        this.reportName = reportName;
        this.prescribedPeople = prescribedPeople;
        this.diagnosticName = diagnosticName;
        this.reprotDetails = reprotDetails;
        this.testCost = testCost;
        this.recordComment = recordComment;
        this.reportDate = date;
        this.imageReport = imageReport;
    }

    public MedicalReportModel(String reportName, String prescribedPeople, String diagnosticName, String reprotDetails, int testCost, String recordComment, String date, Bitmap imageReport) {
        this.reportName = reportName;
        this.prescribedPeople = prescribedPeople;
        this.diagnosticName = diagnosticName;
        this.reprotDetails = reprotDetails;
        this.testCost = testCost;
        this.recordComment = recordComment;
        this.reportDate = date;
        this.imageReport = imageReport;
    }

    public MedicalReportModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getPrescribedPeople() {
        return prescribedPeople;
    }

    public void setPrescribedPeople(String prescribedPeople) {
        this.prescribedPeople = prescribedPeople;
    }

    public String getDiagnosticName() {
        return diagnosticName;
    }

    public void setDiagnosticName(String diagnosticName) {
        this.diagnosticName = diagnosticName;
    }

    public String getReprotDetails() {
        return reprotDetails;
    }

    public void setReprotDetails(String reprotDetails) {
        this.reprotDetails = reprotDetails;
    }

    public int getTestCost() {
        return testCost;
    }

    public void setTestCost(int testCost) {
        this.testCost = testCost;
    }

    public String getRecordComment() {
        return recordComment;
    }

    public void setRecordComment(String recordComment) {
        this.recordComment = recordComment;
    }

    public String getDate() {
        return reportDate;
    }

    public void setDate(String date) {
        reportDate = date;
    }

    public Bitmap getImageReport() {
        return imageReport;
    }

    public void setImageReport(Bitmap imageReport) {
        this.imageReport = imageReport;
    }
}
