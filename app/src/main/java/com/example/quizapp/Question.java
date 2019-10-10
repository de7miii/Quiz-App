package com.example.quizapp;

public class Question {
    private int mId;
    private String mQuestion;
    private String mAnswer;

    Question(int id, String question, String answer) {
        mId = id;
        mQuestion = question;
        mAnswer = answer;
    }

    public int getId() {
        return mId;
    }

    public String getQuestion() {
        return mQuestion;
    }

    String getAnswer() {
        return mAnswer;
    }
}
