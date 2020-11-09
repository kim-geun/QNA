package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    // widgets
    private EditText editId,editPw;
    private TextView textMessage;
    private Button btnSignup;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initFirebase();
        initView();
    }

    public void initFirebase(){
        mAuth=FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference();
    }

    public void initView(){
        // Initialize widgets
        editId=(EditText)findViewById(R.id.editId);
        editPw=(EditText)findViewById(R.id.editPw);
        btnSignup=(Button)findViewById(R.id.btnSignup);
        textMessage=(TextView)findViewById(R.id.textMessage);

        // Signup
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editId.getText().toString().trim();
                String pw=editPw.getText().toString().trim();
                if(email.equals("")){
                    textMessage.setText("이메일을 입력하세요");
                }
                else if(pw.equals("")){
                    textMessage.setText("비밀번호를 입력하세요");
                }
                else{
                    textMessage.setText("");
                    createUser(email,pw);
                }
            }
        });
    }

    public void createUser(String email,String pw){
        mAuth.createUserWithEmailAndPassword(email,pw)
                .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user=mAuth.getCurrentUser();
                            String uid=user.getUid();
                            mDatabase.getReference().child("Users").child(uid).child("uid").setValue(uid);
                            sendEmail(user);
                            updateUI(user);
                        }
                        else{
                            showToast("이메일 계정을 한 번 확인해주세요");
                        }
                    }
                });
    }

    public void sendEmail(FirebaseUser user){
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    // showToast("인증 이메일을 전송했습니다");
                }
                else{
                    showToast("인증 이메일 전송에 실패하였습니다");
                }
            }
        });
    }

    public void updateUI(FirebaseUser user){
        mAuth.signOut();
        if(user!=null) {
            boolean emailVerified = user.isEmailVerified();
            if (!emailVerified) {
                showToast("이메일 인증 후 사용할 수 있습니다");
            }
            finish();
        }
    }

    public void showToast(String message){
        Toast.makeText(SignupActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}