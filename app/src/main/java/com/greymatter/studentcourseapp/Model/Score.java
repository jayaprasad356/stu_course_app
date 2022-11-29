package com.greymatter.studentcourseapp.Model;
public class Score {
    private String email;
    private int scores;

    public Score(){}

    public Score(String email, int scores) {
        this.email = email;
        this.scores = scores;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }
}
