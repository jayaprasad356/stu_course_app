package com.greymatter.studentcourseapp.Model;
public class Course {
    private String course_id,name, description;

    public Course(){}

    public Course(String course_id, String name, String description) {
        this.course_id = course_id;
        this.name = name;
        this.description = description;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
