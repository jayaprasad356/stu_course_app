package com.greymatter.studentcourseapp.Model;


public class AddQuestion {

    String Question, FirstQuestion, SecondQuestion, ThirdQuestion,FourthQuestion;

    public AddQuestion() {
    }

    public AddQuestion(String question, String firstquestion, String secoundquestion, String thirdquestion, String fourthquestion) {
        this.Question = question;
        this.FirstQuestion = firstquestion;
        this.SecondQuestion = secoundquestion;
        this.ThirdQuestion = thirdquestion;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        this.Question = question;
    }

    public String getFirstQuestion() {
        return FirstQuestion;
    }

    public void setFirstQuestion(String firstQuestion) {
        this.FirstQuestion = firstQuestion;
    }

    public String getSecondQuestion() {
        return SecondQuestion;
    }

    public void setSecondQuestion(String secondQuestion) {
        this.SecondQuestion = secondQuestion;
    }

    public String getThirdQuestion() {
        return ThirdQuestion;
    }

    public void setThirdQuestion(String thirdQuestion) {
        this.ThirdQuestion = thirdQuestion;
    }
    public String getFourthQuestion() {
        return FourthQuestion;
    }

    public void setFourthQuestion(String fourthQuestion) {
        this.FourthQuestion = fourthQuestion;
    }
}