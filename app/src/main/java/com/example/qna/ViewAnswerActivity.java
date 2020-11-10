package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAnswerActivity extends AppCompatActivity {

    // widgets
    private TextView textDate,textQuestion,textAnswer,textCategory;
    private Button btnList;

    // Firebase
    // private FirebaseDatabase mDatabase;
    // private DatabaseReference mRef;

    // Data
    private Question_list_data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answer);

        loadAnswer();
        initView();
    }

    public void initView(){
        // Initialize widgets
        textDate=(TextView)findViewById(R.id.view_answer_date);
        textQuestion=(TextView)findViewById(R.id.view_answer_question);
        textAnswer=(TextView)findViewById(R.id.view_answer_answer);
        btnList=(Button)findViewById(R.id.view_answer_toindex);
        textCategory=(TextView)findViewById(R.id.view_answer_category);

        textDate.setText(data.getDate());
        textQuestion.setText("Q. "+data.getQuestionData().context);
        textAnswer.setText(data.getAnswer());
        textCategory.setText(data.getQuestionData().category);

        // When clicked, Back to List
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToList();
            }
        });
    }

    // Function - Back to List
    public void backToList(){
        finish();
    }

    // Load answer which was just clicked
    public void loadAnswer(){
        Intent intent=getIntent();
        data=(Question_list_data)intent.getSerializableExtra("QuestionData");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}