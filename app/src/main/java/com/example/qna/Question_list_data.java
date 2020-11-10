package com.example.qna;

import java.io.Serializable;

public class Question_list_data implements Serializable { // list wrapper 용 데이터
    QuestionData questionData;
    String answer;
    String date;
    public Question_list_data(){
        questionData = new QuestionData();
        answer = new String();
        date = new String();
    }
    public Question_list_data(QuestionData questionData, String answer, String date) {
        this.questionData = questionData;
        this.answer = answer;
        this.date = date;
    }
    public QuestionData getQuestionData() {
        return questionData;
    }

    public void setQuestionData(QuestionData questionData) {
        this.questionData = questionData;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}