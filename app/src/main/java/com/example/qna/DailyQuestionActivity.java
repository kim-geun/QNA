package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;


public class DailyQuestionActivity extends AppCompatActivity {

    Button toIndex, submit;
    TextView date,category_question,category;
    EditText answer;
    DatabaseReference dataRef;
    DatabaseReference userRef;
    FirebaseUser user;
    UserData userData;
    String uid;
    QuestionData questionData;
    int q_num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_question);
        date = findViewById(R.id.daily_question_date);
        category_question = findViewById(R.id.daily_question_question);
        category = findViewById(R.id.daily_question_category);
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
                userData = snapshot.getValue(UserData.class);
                if(userData.dailyAnswer !=null&&userData.dailyAnswer.size()>0){
                    q_num = userData.dailyAnswer.size() + 1;
                    q_num %= 10; // 질문 수가 10개라서 일단 이렇게 함
                }
                else{
                    q_num = 1; // 아무것도 없으면 1번 질문;
                }
                int category_num = userData.category.size() - 1;
                Random random = new Random();
                random.setSeed(System.currentTimeMillis()); // 랜덤 시드
                int category_selected = category_num==1? 0:random.nextInt(category_num);
                dataRef.child(QuestionData.eToK(userData.category.get(category_selected))).child(Integer.toString(q_num)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        questionData = snapshot.getValue(QuestionData.class);
                        category.setText(questionData.category);
                        category_question.setText(questionData.context);
                        Date now = new Date();
                        SimpleDateFormat dateformat = new SimpleDateFormat("MM월dd일");
                        date.setText(dateformat.format(now));
                        submit.setOnClickListener(new View.OnClickListener() {// 제출 버튼 클릭
                            @Override
                            public void onClick(View view) {
                                String ansText = answer.getText().toString();
                                if(!ansText.isEmpty()) {
                                    userData.addNewAnswer(questionData, ansText, date.getText().toString());
                                    userRef.child(uid).updateChildren(userData.toMap());
                                    Intent intent = new Intent(DailyQuestionActivity.this, QuestionResultActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(DailyQuestionActivity.this, "답변을 작성해 주세요", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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


    }
}