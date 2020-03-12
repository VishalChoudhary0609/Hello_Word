package com.example.helloword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.helloword.contestActivity.questionlist;

public class ReviewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    List<myquestions> myquestionlist = new ArrayList<>();
    DatabaseReference questionsref = db.getReference().child("questions");
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        recyclerView = findViewById(R.id.questionrecyclerview);
        progressBar = findViewById(R.id.reviewprogressbar);
        if(contestActivity.questionlist.size()>0){

            myquestionlist = questionlist;
            progressBar.setVisibility(View.INVISIBLE);
            questionadapter adapter = new questionadapter(ReviewActivity.this,myquestionlist);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));



        }else{
            questionsref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {




                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        myquestionlist.add(snapshot.getValue(myquestions.class));


                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    questionadapter adapter = new questionadapter(ReviewActivity.this,myquestionlist);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ReviewActivity.this));


                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        }






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,Homeactivity.class);
        startActivity(intent);
        finish();
    }
}
