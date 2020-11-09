package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DailyQuestionActivity extends AppCompatActivity {

    Button toIndex, submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_question);

        toIndex = findViewById(R.id.daily_question_toindex);
        submit  = findViewById(R.id.daily_question_submit);

        toIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DailyQuestionActivity.this, QuestionIndexActivity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DailyQuestionActivity.this, QuestionResultActivity.class);
                startActivity(intent);
            }
        });
    }
}