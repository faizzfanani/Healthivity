package com.kontrakanelite.healthivity.model;

public class Member {
    int idKomunitas;
    String idUser;

    public Member() {
    }

    public Member(int idKomunitas, String idUser) {
        this.idKomunitas = idKomunitas;
        this.idUser = idUser;
    }

    public int getIdKomunitas() {
        return idKomunitas;
    }

    public void setIdKomunitas(int idKomunitas) {
        this.idKomunitas = idKomunitas;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
