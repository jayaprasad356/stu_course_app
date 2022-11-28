package com.greymatter.studentcourseapp.Model;

public class Users {

    String Name, PhoneNumber, Email, Password;

    public Users() {
    }

    public Users(String firstName, String lastName, String age, String userName) {
        this.Name = firstName;
        this.PhoneNumber = lastName;
        this.Email = age;
        this.Password = userName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}