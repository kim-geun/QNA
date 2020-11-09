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

        final CheckBox cb1 = (CheckBox)findViewById(R.id.chk_love);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.chk_hobby);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.chk_relationship);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.chk_sports);
        final CheckBox cb5 = (CheckBox)findViewById(R.id.chk_movie);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.chk_music);


        Button b = (Button)findViewById(R.id.btn_submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}