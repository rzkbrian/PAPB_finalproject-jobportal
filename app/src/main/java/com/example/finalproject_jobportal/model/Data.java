package com.example.finalproject_jobportal.model;

public class Data {
    String judul;
    String deskripsi;
    String gaji;
    String skill;

    String id;
    String date;

    public Data(){

    }

    public Data(String judul, String deskripsi, String gaji, String skill, String id, String date) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.gaji = gaji;
        this.skill = skill;
        this.id = id;
        this.date = date;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGaji() {
        return gaji;
    }

    public void setGaji(String gaji) {
        this.gaji = gaji;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
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
