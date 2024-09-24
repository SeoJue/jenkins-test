package com.example.jenkins_test;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String email;

    private String role;
    private String name;
    private String gender;
    private String phNum;

    private String description;
    private String image;

    public User() {
    }

    public User(KakaoUserInfo userInfo) {
        this.username = userInfo.getProvider() + "_" + userInfo.getId();
        this.email = userInfo.getEmail();
        this.role = "NORMAL";
        this.name = userInfo.getName();
        this.gender = userInfo.getGender();
        this.phNum = userInfo.getPhone_number();
        this.description = null;
        this.image = null;
    }

    public void changeRole(String role){
        this.role = role;
    }

    public void changeDescription(String description){
        this.description = description;
    }

    public void changeImage(String image){
        this.image = image;
    }

    public void changeEmail(String email){
        this.email = email;
    }

    public void changePhNum(String phNum){
        this.phNum = phNum;
    }
}
