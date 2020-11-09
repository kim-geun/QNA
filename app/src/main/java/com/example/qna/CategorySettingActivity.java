package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;

public class CategorySettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_setting);

        final CheckBox cb1 = (CheckBox)findViewById(R.id.chk_hobby);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.chk_);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.checkBox4);


        Button b = (Button)findViewById(R.id.button1);
        final TextView tv = (TextView)findViewById(R.id.textView2);


        출처: https://bitsoul.tistory.com/45 [Happy Programmer~]
    }
}