package com.kontrakanelite.healthivity;

import java.util.ArrayList;

public class Komunitas {

    private String idKomunitas;
    private String namaKomunitas;
    private String deskripsiKomunitas;
    private String [] jadwalKumpul;
    private String longitude;
    private String latitude;

    public Komunitas() {
    }

    public Komunitas(String idKomunitas, String namaKomunitas, String deskripsiKomunitas, String [] jadwalKumpul, String longitude, String latitude) {
        this.idKomunitas = idKomunitas;
        this.namaKomunitas = namaKomunitas;
        this.deskripsiKomunitas = deskripsiKomunitas;
        this.jadwalKumpul = jadwalKumpul;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getDeskripsiKomunitas() {
        return deskripsiKomunitas;
    }

    public void setDeskripsiKomunitas(String deskripsiKomunitas) {
        this.deskripsiKomunitas = deskripsiKomunitas;
    }

    public String [] getJadwalKumpul() {
        return jadwalKumpul;
    }

    public void setJadwalKumpul(String [] jadwalKumpul) {
        this.jadwalKumpul = jadwalKumpul;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
