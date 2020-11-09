package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    // widgets
    private Button btnLogout;
    private Button btn_reset;
    private Button btnSignout;

    // Firebase
    private FirebaseAuth mAuth;

    // SharedPreference
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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

        btn_reset = findViewById(R.id.btn_reset);
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, CategorySettingActivity.class);
                startActivity(intent);
            }
        });

        btnSignout=findViewById(R.id.btnSignout);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteUser();
            }
        });
    }

    public void deleteUser(){
        mAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    showToast("회원 탈퇴 완료");
                    signOut();
                    updateUI();
                }
            }
        });
    }

    public void signOut(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        mAuth.signOut();
    }

    public void showToast(String message){
        Toast.makeText(SettingActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    public void updateUI(){
        finish();
    }
}