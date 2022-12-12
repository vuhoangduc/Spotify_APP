package com.example.duan1_spotify_clone.DTO;

public class User {
    private String name_user;
    private String gender_user;
    private int age_user;
    private String email_user;
    private String pass_user;

    public User() {
    }
    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getGender_user() {
        return gender_user;
    }

    public void setGender_user(String gender_user) {
        this.gender_user = gender_user;
    }

    public int getAge_user() {
        return age_user;
    }

    public void setAge_user(int age_user) {
        this.age_user = age_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPass_user() {
        return pass_user;
    }

    public void setPass_user(String pass_user) {
        this.pass_user = pass_user;
    }

    public User( String name_user, String gender_user, int age_user, String email_user, String pass_user) {
        this.name_user = name_user;
        this.gender_user = gender_user;
        this.age_user = age_user;
        this.email_user = email_user;
        this.pass_user = pass_user;
    }

}
