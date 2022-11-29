package com.greymatter.studentcourseapp.Model;

public class Test {
    String test_id,course_id,name, description, noOfQuestion , startdate, starttime, enddate, endtime,start_timestamp,end_timestamp;


    public Test(){

    }

    public Test(String test_id, String course_id, String name, String description, String noOfQuestion, String startdate, String starttime, String enddate, String endtime, String start_timestamp, String end_timestamp) {
        this.test_id = test_id;
        this.course_id = course_id;
        this.name = name;
        this.description = description;
        this.noOfQuestion = noOfQuestion;
        this.startdate = startdate;
        this.starttime = starttime;
        this.enddate = enddate;
        this.endtime = endtime;
        this.start_timestamp = start_timestamp;
        this.end_timestamp = end_timestamp;
    }

    public String getTest_id() {
        return test_id;
    }

    public void setTest_id(String test_id) {
        this.test_id = test_id;
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

    public String getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(String start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public String getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(String end_timestamp) {
        this.end_timestamp = end_timestamp;
    }
}
