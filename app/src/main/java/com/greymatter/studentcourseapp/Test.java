package com.greymatter.studentcourseapp;

public class Test {
    public Test(String title, String description, String noOfQuestion) {
        this.title=title;
        this.description=description;
        this.noOfQuestion=noOfQuestion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(String noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    String title,description,noOfQuestion;
}
