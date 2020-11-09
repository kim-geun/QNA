package com.example.qna;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable {
    String id;
    ArrayList<String> category;
    String context;
    double starRate;
    int rateNum;

    public QuestionData(String id, ArrayList<String> category, String context, double starRate, int rateNum) {
        this.id = id;
        this.category = category;
        this.context = context;
        this.starRate = starRate;
        this.rateNum = rateNum;
    }

    public void addAndRecalculateStarRate(double addStarRate){ //투표수 증가, 별점 평균 반영
        double tmpStarRateAll = starRate*rateNum + addStarRate;
        rateNum++;
        starRate = tmpStarRateAll/rateNum;
    }
    public void updateCategory(ArrayList<String> newCategory){
        category.clear();
        category.addAll(newCategory);
    }
}
