package com.example.helloword;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class questionadapter extends RecyclerView.Adapter<questionadapter.MyviewHolder> {

     LayoutInflater inflater;
     List<myquestions> myquestionsList;
     public  questionadapter(Context context,List<myquestions> myquestionsList){
         inflater = LayoutInflater.from(context);
         this.myquestionsList = myquestionsList;
     }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = inflater.inflate(R.layout.customreviewquestioncard,parent,false);
         MyviewHolder myviewHolder = new MyviewHolder(view);
         return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
         myquestions currentquestion = myquestionsList.get(position);

         holder.question.setText(currentquestion.getQuestion());
         holder.answer.setText(currentquestion.getAnswer());


    }

    @Override
    public int getItemCount() {
        return myquestionsList.size();
    }

    class  MyviewHolder extends  RecyclerView.ViewHolder{

         TextView question,answer;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.questiontextviewid);
            answer = itemView.findViewById(R.id.answertextviewid);

        }
    }
}
