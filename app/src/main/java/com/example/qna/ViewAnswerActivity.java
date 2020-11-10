package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAnswerActivity extends AppCompatActivity {

    // widgets
    private TextView textDate,textQuestion,textAnswer;
    private Button btnList;

    // Firebase
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answer);

        initFirebase();
        loadAnswer();

        initView();
    }

    public void initView(){
        // Initialize widgets
        textDate=(TextView)findViewById(R.id.view_answer_date);
        textQuestion=(TextView)findViewById(R.id.view_answer_question);
        textAnswer=(TextView)findViewById(R.id.view_answer_answer);
        btnList=(Button)findViewById(R.id.view_answer_toindex);

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

    }

    // Load answer which was just clicked
    public void loadAnswer(){

    }

    // If needed
    public void initFirebase(){
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference();
    }
}