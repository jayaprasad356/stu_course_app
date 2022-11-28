package com.greymatter.studentcourseapp.Model;

public class DashBoardList {
    private String firstName, lastName;

    public DashBoardList(){

    }

    public DashBoardList(String course, String description) {
        this.firstName = course;
        this.lastName = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
