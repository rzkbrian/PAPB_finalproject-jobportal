package com.example.finalproject_jobportal.model;

public class Data {
    String title;
    String description;
    String salary;
    String skills;

    String id;
    String date;

    public Data(){

    }

    public Data(String judul, String deskripsi, String gaji, String skill, String id, String date) {
        this.title = judul;
        this.description = deskripsi;
        this.salary = gaji;
        this.skills = skill;
        this.id = id;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String judul) {
        this.title = judul;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String deskripsi) {
        this.description = deskripsi;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String gaji) {
        this.salary = gaji;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkilsl(String skill) {
        this.skills = skill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
