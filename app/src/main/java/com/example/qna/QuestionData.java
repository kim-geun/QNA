package com.example.qna;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionData implements Serializable {
    String id;
    String category;
    String context;
    double starRate;
    int rateNum;
    public QuestionData() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public double getStarRate() {
        return starRate;
    }

    public void setStarRate(double starRate) {
        this.starRate = starRate;
    }

    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }

    public QuestionData(String id, String category, String context, double starRate, int rateNum) {
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
    public static String eToK(String e){
        switch (e){
            case "etc": return "기타";
            case "love": return "연애결혼";
            case "sports": return "운동건강";
            case "relationship": return "인간관계";
            case "music": return "음악";
            case "movie": return "영화";
            default: return "null";
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public double getStarRate() {
        return starRate;
    }

    public void setStarRate(double starRate) {
        this.starRate = starRate;
    }

    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }
}
