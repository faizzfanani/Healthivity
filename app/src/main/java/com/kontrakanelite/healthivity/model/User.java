package com.kontrakanelite.healthivity.model;

public class User {
    private String name;
    private String email;
    private int age;
    private String idNumber;
    private String password;
    private String idUser;

    public User(){

    }

    public User(String idUser, String name, String email, int age, String idNumber, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.idNumber = idNumber;
        this.password = password;
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getPassword() {
        return password;
    }
}
