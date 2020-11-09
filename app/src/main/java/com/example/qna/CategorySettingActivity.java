package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class CategorySettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_setting);

        final CheckBox chk_love = (CheckBox)findViewById(R.id.chk_love);
        final CheckBox chk_hobby = (CheckBox)findViewById(R.id.chk_hobby);
        final CheckBox chk_relationship = (CheckBox)findViewById(R.id.chk_relationship);
        final CheckBox chk_sports = (CheckBox)findViewById(R.id.chk_sports);
        final CheckBox chk_movie = (CheckBox)findViewById(R.id.chk_movie);
        final CheckBox chk_music = (CheckBox)findViewById(R.id.chk_music);


        Button b = (Button)findViewById(R.id.btn_submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}