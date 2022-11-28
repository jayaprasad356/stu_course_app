package com.greymatter.studentcourseapp.Model;

public class Registerinfo {
    private String name, mobile,email,password,category;

    public Registerinfo(){

    }

    public Registerinfo(String name, String mobile, String email, String password, String category) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
