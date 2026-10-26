package com.example.quiz;

public class TrueFalse {

    private int mQuestionId;// rappresenta l'id della doamnda;

    private boolean mAnswer;// rappresenta la risposta

    public TrueFalse(int mQuestionID, boolean mAnswer){

        this.mQuestionId=mQuestionID;
        this.mAnswer=mAnswer;

    }

    public int getmQuestionId() {
        return mQuestionId;
    }

    public void setmQuestionId(int mQuestionId) {
        this.mQuestionId = mQuestionId;
    }

    public boolean ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
