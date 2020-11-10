package com.example.qna;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserData{ //UID로 구분 in firebase
    public String uid;
    public ArrayList<String> category;
    public ArrayList<Question_list_data> dailyAnswer; // << Question Category, Question answer, Question date > , Answer>
    public  UserData(){
        this.uid = new String();
        category = new ArrayList();
        dailyAnswer = new ArrayList<>();

    }
    public UserData(String UID) {
        this.uid = UID;
        category = new ArrayList();
        dailyAnswer = new ArrayList();
    }
    public UserData(String UID,ArrayList<String> category) {
        this.uid = UID;
        this.category = category;
        this.dailyAnswer = new ArrayList();
    }
    public void updateCategory(ArrayList<String> newCategory){ // 유저 카테고리 업데이트
        category.clear();
        category.addAll(newCategory);
    }
    public QuestionData getQuestionId(int order){ // order번째 QuestionData return
        return dailyAnswer.get(order).questionData;
    }
    public String getQuestionAnswer(int order){ // order번째 질문의 Answer return
        return dailyAnswer.get(order).answer;
    }
    public void addNewAnswer(QuestionData questionData, String answer, String date){ // 새로운 답변을 했을 때 데이터 추가 (질문,답변,날짜)
        dailyAnswer.add(new Question_list_data(questionData,answer,date));
    }
    public Map<String,Object> toMap(){
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("category",category);
        hashMap.put("dailyAnswer",dailyAnswer);
        return hashMap;
    }
}

