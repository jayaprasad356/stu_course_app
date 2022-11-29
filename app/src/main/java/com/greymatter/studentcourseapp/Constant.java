package com.greymatter.studentcourseapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Constant {
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TYPE = "type";
    public static final String REGISTRATION = "registration";
    public static final String COURSES = "courses";
    public static final String STUDENT_ENROLL_COURSES = "student_enroll_courses";
    public static final String STUDENT_ENROLL_COURSES_ID = "student_enroll_courses_id";
    public static final String QUESTIONS = "questions";
    public static final String SCORES = "scores";
    public static final String QUESTION = "question";
    public static final String TESTS = "tests";
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_ENROLL_ID = "course_enroll_id";
    public static final String TEST_ID = "test_id";
    public static final String QUESTION_ID = "question_id";
    public static final String OPTION_1 = "option_1";
    public static final String OPTION_2 = "option_2";
    public static final String OPTION_3 = "option_3";
    public static final String OPTION_4 = "option_4";
    public static final String CORRECT_OPTION = "correct_option";
    public static final String QUESTION_COUNT = "question_count";
    public static final String DESCRIPTION = "description";
    public static final String STARTDATE = "startdate";
    public static final String STARTTIME = "starttime";
    public static final String ENDDATE = "enddate";
    public static final String ENDTIME = "endtime";
    public static final String START_TIMESTAMP = "start_timestamp";
    public static final String END_TIMESTAMP = "end_timestamp";

    public static final  String removedot(String email) {
        return email.replace(".",",");
    }
    public static final  String adddot(String email) {
        return email.replace(",",".");
    }
}