package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class QuestionResultActivity extends AppCompatActivity {

    Spinner spinner;
    LinearLayout recommendLayout;
    TextView add_question_textview;
    EditText recommend;
    ImageButton toindex_button;
    Button submit;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_result);

        // Initialize Firebase variables
        initFirebase();

        //spinner
        spinner = findViewById(R.id.question_result_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.category)
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //스피너 객체에다가 어댑터 넣어주기
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //선택되면
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            //아무것도 선택되지 않은 상태일 때
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //질문 추가
        recommendLayout = findViewById(R.id.question_result_recommend);
        add_question_textview = findViewById(R.id.question_result_add_question);

        add_question_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recommendLayout.getVisibility()==View.VISIBLE){
                    Animation animation = new AlphaAnimation(1, 0);
                    animation.setDuration(500);
                    recommendLayout.setVisibility(View.GONE);
                    recommendLayout.setAnimation(animation);
                }
                else{
                    Animation animation = new AlphaAnimation(0, 1);
                    animation.setDuration(500);
                    recommendLayout.setVisibility(View.VISIBLE);
                    recommendLayout.setAnimation(animation);
                }
            }
        });

        toindex_button = findViewById(R.id.question_result_toindex);
        toindex_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionResultActivity.this, QuestionIndexActivity.class);
                startActivity(intent);
            }
        });

        submit = findViewById(R.id.question_result_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category = spinner.getSelectedItem().toString();         //spinner category 가져오기
                recommend = findViewById(R.id.question_result_recomment_text);  //recommend 버튼 연결
                String recommend_string = recommend.getText().toString();       //recommend text 가져오기
                if (recommend_string.length() == 0) {
                    //공백일 때 처리할 내용
                    Toast.makeText(getApplicationContext(), "추가할 질문을 입력하세요.", Toast.LENGTH_LONG).show();
                } else {
                    //공백이 아닐 때 처리할 내용
                    mRef.child("Question").child(category).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            long size = snapshot.getChildrenCount();
                            addQuestion(category, recommend_string,size);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }

    public void addQuestion(String category, String context, long size){
        String id=String.valueOf(size + 1);
        QuestionData qdata=new QuestionData(id,category,context,0.0,0);
        mRef.child("Question").child(category).child(id).setValue(qdata).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(QuestionResultActivity.this,"추가 완료",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initFirebase(){
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference();
    }
}