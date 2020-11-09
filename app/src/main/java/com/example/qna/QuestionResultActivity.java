package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class QuestionResultActivity extends AppCompatActivity {

    Spinner spinner;
    LinearLayout recommendLayout;
    TextView add_question_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_result);

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

        recommendLayout = findViewById(R.id.question_result_recommend);
        add_question_textview = findViewById(R.id.question_result_add_question);

        add_question_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recommendLayout.getVisibility()==View.VISIBLE)
                    recommendLayout.setVisibility(View.GONE);
                else
                    recommendLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}