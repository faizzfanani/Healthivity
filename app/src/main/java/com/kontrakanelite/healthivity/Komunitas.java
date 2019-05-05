package com.kontrakanelite.healthivity;

import java.util.ArrayList;

public class Komunitas {

    private String idKomunitas;
    private String namaKomunitas;
    //private String deskripsiKomunitas;
    private String jadwalKomunitas;
    private float longitude;
    private float latitude;
    private String kategori;

    public Komunitas() {
    }

    public Komunitas(String idKomunitas, String namaKomunitas, String jadwalKomunitas, float longitude, float latitude, String kategori) {
        this.idKomunitas = idKomunitas;
        this.namaKomunitas = namaKomunitas;
        this.jadwalKomunitas = jadwalKomunitas;
        this.longitude = longitude;
        this.latitude = latitude;
        this.kategori = kategori;
    }

    public String getIdKomunitas() {
        return idKomunitas;
    }

    public void setIdKomunitas(String idKomunitas) {
        this.idKomunitas = idKomunitas;
    }

    public String getNamaKomunitas() {
        return namaKomunitas;
    }

    public void setNamaKomunitas(String namaKomunitas) {
        this.namaKomunitas = namaKomunitas;
    }

    public String getJadwalKomunitas() {
        return jadwalKomunitas;
    }

    public void setJadwalKomunitas(String jadwalKomunitas) {
        this.jadwalKomunitas = jadwalKomunitas;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
