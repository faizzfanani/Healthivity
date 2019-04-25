package com.kontrakanelite.healthivity;

public class User {
    private String name;
    private String email;
    private int age;
    private String password;
    private String idUser;

    public User(){

    }

    public User(String idUser, String name, String email, int age, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
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

    public String getPassword() {
        return password;
    }
}
