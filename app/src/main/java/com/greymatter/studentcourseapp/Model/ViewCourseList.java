package com.greymatter.studentcourseapp.Model;

public class ViewCourseList {
    private String course, description;

    public ViewCourseList(){

    }

    public ViewCourseList(String course, String description) {
        this.course = course;
        this.description = description;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
