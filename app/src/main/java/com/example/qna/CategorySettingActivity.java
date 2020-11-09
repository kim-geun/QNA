package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

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
                ArrayList<String> newCategory = new ArrayList<String>();
                if(chk_love.isChecked() == true) newCategory.add("love");
                if(chk_hobby.isChecked() == true) newCategory.add("hobby");
                if(chk_relationship.isChecked() == true) newCategory.add("relationship");
                if(chk_sports.isChecked() == true) newCategory.add("sports");
                if(chk_movie.isChecked() == true) newCategory.add("movie");
                if(chk_music.isChecked() == true) newCategory.add("music");
                if(newCategory.size() == 0){
                    Toast.makeText(getApplicationContext(),"카테고리를 1개 이상 선택하세요",Toast.LENGTH_LONG).show();
                }
                else{

                }
            }
        });

    }
}