package com.greymatter.studentcourseapp.Model;

public class ViewCourseList {
    private String firstName, lastName;

    public ViewCourseList(){

    }

    public ViewCourseList(String course, String description) {
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
