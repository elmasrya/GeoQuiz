package com.andrewelmasry.geoquiz;

/**
 * Created by Andrew on 1/20/16.
 */
public class TrueFalse {

    private int question;

    private boolean trueQuestion;

    public TrueFalse(int question, boolean trueQuestion) {

        this.question = question;
        this.trueQuestion = trueQuestion;
    }

    public int getQuestion () {
        return question;
    }

    public void setQuestion(int question){
        this.question = question;
    }

    public boolean getTrueQuestion() {
        return trueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;

    }

}
