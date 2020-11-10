package com.example.qna;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionIdxRVAdapter extends RecyclerView.Adapter<QuestionIdxRVAdapter.ViewHolder> {
    ArrayList<Question_list_data> questionDataList;
    OnItemClickListener mlistener;
    public QuestionIdxRVAdapter(ArrayList<Question_list_data> questionDataList) {
        this.questionDataList = questionDataList;
    }
    interface OnItemClickListener{
        public void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mlistener = onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.question_list_wrapper,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.category.setText(String.join("/",questionDataList.get(position).questionData.category));
        holder.date.setText(questionDataList.get(position).date);
        holder.context.setText("Q. "+questionDataList.get(position).questionData.context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.onItemClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionDataList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView date = itemView.findViewById(R.id.question_wrapper_date);
        TextView category = itemView.findViewById(R.id.question_wrapper_category);
        TextView context = itemView.findViewById(R.id.question_wrapper_context);
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
