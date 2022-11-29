package com.greymatter.studentcourseapp.Model;

public class Test {
    String course_id,name, description, noOfQuestion , startdate, starttime, enddate, endtime;


    public Test(){

    }

    public Test(String course_id, String name, String description, String noOfQuestion, String startdate, String starttime, String enddate, String endtime) {
        this.course_id = course_id;
        this.name = name;
        this.description = description;
        this.noOfQuestion = noOfQuestion;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
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

    public String getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(String noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }
}
