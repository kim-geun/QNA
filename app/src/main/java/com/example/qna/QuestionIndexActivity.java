package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;

import java.io.Serializable;

public class QuestionIndexActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    QuestionIdxRVAdapter adapter;
    DatabaseReference dataRef;
    String uid = new String();
    UserData userData = new UserData(uid);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_index);
        recyclerView = findViewById(R.id.question_index_recyclerview);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        adapter = new QuestionIdxRVAdapter(userData.dailyAnswer);
        adapter.setOnItemClickListener(new QuestionIdxRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//              Intent itemIntent = new Intent(this,DailyQuestionActivity.class);
                //itemIntent.putExtra("QuestionData", userData.dailyAnswer.get(position).questionData);
             // startActivity(itemIntent);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}