package com.greymatter.studentcourseapp.Model;


public class AddCourse {

    String Title, Discription;

    public AddCourse() {
    }

    public AddCourse(String title, String discription) {
        this.Title = title;
        this.Discription = discription;

    }

    public String getFirstName() {
        return Title;
    }

    public void setFirstName(String title) {
        this.Title = title;
    }

    public String getLastName() {
        return Discription;
    }

    public void setLastName(String discription) {
        this.Discription = discription;
    }


}