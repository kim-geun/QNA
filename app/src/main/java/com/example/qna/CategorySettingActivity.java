package com.example.qna;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategorySettingActivity extends AppCompatActivity {

    DatabaseReference dataRef;
    FirebaseUser auth;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_setting);
        auth = FirebaseAuth.getInstance().getCurrentUser();
        uid = auth.getUid();
        dataRef = FirebaseDatabase.getInstance().getReference("Users");
        final CheckBox chk_love = (CheckBox)findViewById(R.id.chk_love);
        final CheckBox chk_etc = (CheckBox)findViewById(R.id.chk_etc);
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
                if(chk_etc.isChecked() == true) newCategory.add("etc");
                if(chk_relationship.isChecked() == true) newCategory.add("relationship");
                if(chk_sports.isChecked() == true) newCategory.add("sports");
                if(chk_movie.isChecked() == true) newCategory.add("movie");
                if(chk_music.isChecked() == true) newCategory.add("music");
                if(newCategory.size() == 0){
                    Toast.makeText(getApplicationContext(),"카테고리를 1개 이상 선택하세요",Toast.LENGTH_LONG).show();
                }
                else{
                    dataRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            UserData user = snapshot.getValue(UserData.class);
                            Log.d("로그로그", String.join(",",newCategory));
                            user.updateCategory(newCategory);
                            dataRef.child(uid).removeValue();
                            dataRef.child(uid).setValue(user);
                            finish();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    Intent intent = new Intent(CategorySettingActivity.this, DailyQuestionActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}