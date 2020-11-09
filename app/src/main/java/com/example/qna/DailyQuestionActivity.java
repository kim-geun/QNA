package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DailyQuestionActivity extends AppCompatActivity {

    Button toIndex, submit;
    TextView date,category,answer;
    DatabaseReference dataRef;
    DatabaseReference userRef;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_question);
        date = findViewById(R.id.daily_question_date);
        category = findViewById(R.id.daily_question_question);
        answer = findViewById(R.id.daily_question_answer);
        toIndex = findViewById(R.id.daily_question_toindex);
        submit  = findViewById(R.id.daily_question_submit);
        dataRef = FirebaseDatabase.getInstance().getReference("Questions");
        userRef = FirebaseDatabase.getInstance().getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        userRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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