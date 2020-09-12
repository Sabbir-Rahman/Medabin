package com.example.medabinfinal.hospitalinfo.Model;

public class hospitalModel {
    private int Id;
    private String Name,Location,Phone,EmergencyUnit,HotlineAmbulance,Speciality;
    private int CebinNo,WardNo;
    private String others;

    public hospitalModel(int id, String name, String location, String phone, String emergencyUnit, String hotlineAmbulance, String speciality, int cebinNo, int wardNo, String others) {
        Id = id;
        Name = name;
        Location = location;
        Phone = phone;
        EmergencyUnit = emergencyUnit;
        HotlineAmbulance = hotlineAmbulance;
        Speciality = speciality;
        CebinNo = cebinNo;
        WardNo = wardNo;
        this.others = others;
    }

    public hospitalModel() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmergencyUnit() {
        return EmergencyUnit;
    }

    public void setEmergencyUnit(String emergencyUnit) {
        EmergencyUnit = emergencyUnit;
    }

    public String getHotlineAmbulance() {
        return HotlineAmbulance;
    }

    public void setHotlineAmbulance(String hotlineAmbulance) {
        HotlineAmbulance = hotlineAmbulance;
    }

    public String getSpeciality() {
        return Speciality;
    }

    public void setSpeciality(String speciality) {
        Speciality = speciality;
    }

    public int getCebinNo() {
        return CebinNo;
    }

    public void setCebinNo(int cebinNo) {
        CebinNo = cebinNo;
    }

    public int getWardNo() {
        return WardNo;
    }

    public void setWardNo(int wardNo) {
        WardNo = wardNo;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
