package com.example.qna;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    // widgets
    private Button btnLogout;
    private Button btn_reset;

    // Firebase
    private FirebaseAuth mAuth;

    // SharedPreference
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_reset = findViewById(R.id.btn_reset);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, CategorySettingActivity.class);
                startActivity(intent);
            }
        });

        initFirebase();
        initView();
    }

    public void initFirebase(){
        mAuth=FirebaseAuth.getInstance();

        preferences=getSharedPreferences("autologin", Activity.MODE_PRIVATE);
    }

    public void initView(){
        btnLogout=(Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    public void signOut(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        mAuth.signOut();
    }
}