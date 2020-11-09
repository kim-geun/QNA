package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionIndexActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    QuestionIdxRVAdapter adapter;
    DatabaseReference dataRef = FirebaseDatabase.getInstance().getReference("User");
    String uid = new String();
    ArrayList<UserData.Question_list_data> question_list_data = new ArrayList<>();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_index);
        uid = user.getUid();
        recyclerView = findViewById(R.id.question_index_recyclerview);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        layoutManager.setStackFromEnd(true);
        adapter = new QuestionIdxRVAdapter(question_list_data);
        adapter.setOnItemClickListener(new QuestionIdxRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//              Intent itemIntent = new Intent(this,DailyQuestionActivity.class);
                //itemIntent.putExtra("QuestionData", userData.dailyAnswer.get(position).questionData);
             // startActivity(itemIntent);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        dataRef.child(uid).child("dailyAnswer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                question_list_data.clear();
                for(DataSnapshot snap : snapshot.getChildren()){
                    question_list_data.add(snap.getValue(UserData.Question_list_data.class));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}