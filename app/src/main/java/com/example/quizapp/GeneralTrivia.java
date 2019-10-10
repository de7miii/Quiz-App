package com.example.quizapp;

import java.util.ArrayList;
import java.util.List;

class GeneralTrivia {

    private List<Question> mQuestions = new ArrayList<>();
    private static GeneralTrivia instance;

    private GeneralTrivia() {
        mQuestions.add(new Question(
                1,
                "Who invented the telephone?",
                "Graham Bell"));
        mQuestions.add(new Question(
                2,
                "Who wrote Julius Caesar, Macbeth and Hamlet?",
                "William Shakespeare"
        ));
        mQuestions.add(new Question(
                3,
                "What year did the Spanish Civil War end?",
                "1939"
        ));
        mQuestions.add(new Question(
                4,
                "Where is the smallest bone in the body?",
                "Ear"
        ));
        mQuestions.add(new Question(
                5,
                "What does the roman numeral C represent?",
                "100"
        ));
        mQuestions.add(new Question(
                6,
                "Who painted The Starry Night?",
                "Vincent van Gogh"
        ));
        mQuestions.add(new Question(
                7,
                "Which of the following countries are part of the Middle East, North Africa(MENA) Region?",
                "United Arab Emirates, Sudan"
        ));
    }

    static GeneralTrivia getInstance() {

        if (instance == null)
            instance = new GeneralTrivia();

        return instance;
    }

    List<Question> getQuestions() {
        return mQuestions;
    }

    String getQuestionAnswer(int questionId) {
        StringBuilder answer = new StringBuilder();
        for (Question q :
                mQuestions) {
            if (q.getId() == questionId) {
                answer.append(q.getAnswer());
            }
        }
        return answer.toString();
    }
}
