package com.kontrakanelite.healthivity.model;

public class KategoriModel {
    String NamaKategori;
    int GambarKategori;

    public KategoriModel(String namaKategori, int gambarKategori) {
        NamaKategori = namaKategori;
        GambarKategori = gambarKategori;
    }

    public String getNamaKategori() {
        return NamaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        NamaKategori = namaKategori;
    }

    public int getGambarKategori() {
        return GambarKategori;
    }

    public void setGambarKategori(int gambarKategori) {
        GambarKategori = gambarKategori;
    }
}
