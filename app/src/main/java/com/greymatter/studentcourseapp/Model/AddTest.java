package com.greymatter.studentcourseapp.Model;

import android.icu.text.CaseMap;

public class AddTest {

    String title, description, starttime, endtime, startdate, enddate;

    public AddTest() {
    }

    public AddTest(String Title, String Description, String StartTime, String EndTime, String StartDate, String EndDate) {
        this.title = Title;
        this.description = Description;
        this.starttime = StartTime;
        this.endtime = EndTime;
        this.startdate = StartDate;
        this.enddate = EndDate;

    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.endtime = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.endtime = enddate;
    }
}