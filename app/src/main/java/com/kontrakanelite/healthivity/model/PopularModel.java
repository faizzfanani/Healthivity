package com.kontrakanelite.healthivity.model;

public class PopularModel {
    String namaPopular;
    String jumlahMember;

    public PopularModel(String namaPopular, String jumlahMember) {
        this.namaPopular = namaPopular;
        this.jumlahMember = jumlahMember;
    }

    public String getNamaPopular() {
        return namaPopular;
    }

    public void setNamaPopular(String namaPopular) {
        this.namaPopular = namaPopular;
    }

    public String getJumlahMember() {
        return jumlahMember;
    }

    public void setJumlahMember(String jumlahMember) {
        this.jumlahMember = jumlahMember;
    }
}
