package com.example.medabinfinal.teleDoctor;

public class Contacts
{
    public String name, status , image;

    public Contacts()
    {

    }

    public Contacts(String name) {
        this.name = name;
        this.status=status;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
