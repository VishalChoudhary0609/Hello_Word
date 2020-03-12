package com.example.helloword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class contestActivity extends AppCompatActivity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference questionsref = db.getReference().child("questions");
    static List<myquestions> questionlist = Collections.EMPTY_LIST;
    int totalques=0 ,position, correctanswers=0;
    ProgressBar progressBar;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference Users = myRef.child("Users");
    Button optionA,optionB,optionC,optionD,reviewbtn;
    TextView question,score,contestwritten,totalquestionstextview,correctanswerstextview;
    CardView quescard;
    LinearLayout scorelayout,finallinearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        totalquestionstextview = findViewById(R.id.totalquestionstextview);
        correctanswerstextview = findViewById(R.id.correctquestions);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        reviewbtn = findViewById(R.id.reviewbtn);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        finallinearlayout = findViewById(R.id.finalscorelayout);
        contestwritten = findViewById(R.id.contestwriiten);
        question = findViewById(R.id.questextview);
        scorelayout = findViewById(R.id.scorelayout);
        quescard = findViewById(R.id.questioncard);
        quescard.setVisibility(View.INVISIBLE);
        scorelayout.setVisibility(View.INVISIBLE);
        score = findViewById(R.id.urscoretextview);
        progressBar = findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);



        questionlist  = new ArrayList<>();
         questionsref.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                 for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                  questionlist.add(snapshot.getValue(myquestions.class));
                 }
                 totalques = questionlist.size();
                 position =0;

                 if(position<totalques){
                     quescard.setVisibility(View.VISIBLE);
                     scorelayout.setVisibility(View.VISIBLE);
                     progressBar.setVisibility(View.INVISIBLE);
                     loadques(position);
                 }
             }
             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
             }
         });

    }

    private void loadques(final int position) {
          enablebtns();
          clickabletbn();

          if(position<totalques){
              optionA.setBackgroundColor(Color.WHITE);
              optionB.setBackgroundColor(Color.WHITE);
              optionC.setBackgroundColor(Color.WHITE);
              optionD.setBackgroundColor(Color.WHITE);

              question.setText(questionlist.get(position).getQuestion());
              optionA.setText(questionlist.get(position).getOptionA());
              optionB.setText(questionlist.get(position).getOptionB());
              optionC.setText(questionlist.get(position).getOptionC());
              optionD.setText(questionlist.get(position).getOptionD());
              final String answer = questionlist.get(position).getAnswer();
              final int[] position1 = {position};


              optionA.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      disablebtns();

                      if(optionA.getText().toString().equals(answer)){

                          ++correctanswers;
                          enablenonclickable(optionA);
                          optionA.setBackgroundColor(Color.GREEN);
                      }else{
                          optionA.setBackgroundColor(Color.RED);
                          if(optionB.getText().toString().equals(answer)){
                              enablenonclickable(optionB);
                              optionB.setBackgroundColor(Color.GREEN);

                          }else if(optionC.getText().toString().equals(answer)){
                              optionC.setBackgroundColor(Color.GREEN);
                              enablenonclickable(optionC);

                          }else {
                              optionD.setBackgroundColor(Color.GREEN);
                              enablenonclickable(optionD);

                          }
                      }

                      loadqueswithAnim(position1);
                  }
              });
              optionB.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      disablebtns();

                      if(optionB.getText().toString().equals(answer)){
                          ++correctanswers;
                          enablenonclickable(optionB);
                          optionB.setBackgroundColor(Color.GREEN);
                      }else{
                          optionB.setBackgroundColor(Color.RED);
                          if(optionA.getText().toString().equals(answer)){
                              optionA.setBackgroundColor(Color.GREEN);
                              enablenonclickable(optionA);


                          }else if(optionC.getText().toString().equals(answer)){
                              enablenonclickable(optionC);

                              optionC.setBackgroundColor(Color.GREEN);
                          }else optionD.setBackgroundColor(Color.GREEN);
                          enablenonclickable(optionD);

                      }
                      loadqueswithAnim(position1);


                  }
              });
              optionC.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      disablebtns();

                      if(optionC.getText().toString().equals(answer)){
                          ++correctanswers;
                          enablenonclickable(optionC);

                          optionC.setBackgroundColor(Color.GREEN);

                      }else{
                          optionC.setBackgroundColor(Color.RED);
                          if(optionB.getText().toString().equals(answer)){
                              enablenonclickable(optionB);

                              optionB.setBackgroundColor(Color.GREEN);

                          }else if(optionA.getText().toString().equals(answer)){
                              enablenonclickable(optionA);

                              optionA.setBackgroundColor(Color.GREEN);
                          }else optionD.setBackgroundColor(Color.GREEN);
                          enablenonclickable(optionD);

                      }
                      loadqueswithAnim(position1);



                  }
              });
              optionD.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      disablebtns();
                      if(optionD.getText().toString().equals(answer)){
                          ++correctanswers;
                          enablenonclickable(optionD);

                          optionD.setBackgroundColor(Color.GREEN);
                      }else{
                          optionD.setBackgroundColor(Color.RED);
                          if(optionB.getText().toString().equals(answer)){
                              enablenonclickable(optionB);

                              optionB.setBackgroundColor(Color.GREEN);

                          }else if(optionC.getText().toString().equals(answer)){
                              enablenonclickable(optionC);

                              optionC.setBackgroundColor(Color.GREEN);
                          }else optionA.setBackgroundColor(Color.GREEN);
                          enablenonclickable(optionA);

                      }
                      loadqueswithAnim(position1);
                    }
              });


          }else {
              Intent intent = new Intent(contestActivity.this,Homeactivity.class);
              startActivity(intent);
              finish();
          }

    }
    public void loadanim(){

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.cardanim);
        quescard.startAnimation(animation);


    }
    public void disablebtns(){
        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);
    }
    public void clickabletbn(){
        optionA.setClickable(true);
        optionB.setClickable(true);
        optionC.setClickable(true);
        optionD.setClickable(true);
    }
    public void enablebtns(){
        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);
    }
    public void loadqueswithAnim(final int[]position1){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                position1[0]++;
                if(position1[0]!=totalques){ loadanim();      //dont call loadques() again if its already last ques
                    Animation animation = AnimationUtils.loadAnimation(contestActivity.this,R.anim.cardanim);
                    scorelayout.startAnimation(animation);
                    handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        score.setText(String.valueOf(correctanswers*10));
                        loadques(position1[0]);
                    }
                }, 100);
                }else {
                    Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Score").setValue((correctanswers*10)).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            SharedPreferences sharedPreferencesForContest = getSharedPreferences("contestTakenOrNor",MODE_PRIVATE);
                            SharedPreferences.Editor editor1 = sharedPreferencesForContest.edit();
                            String fragdecide = sharedPreferencesForContest.getString("contestTaken","b");
                            editor1.putString("contestTaken","true");
                            editor1.commit();
                            Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("contestTaken").setValue("true");

                        }
                    });

                    score.setText(String.valueOf(correctanswers*10));

                    Animation animation1 = AnimationUtils.loadAnimation(contestActivity.this,R.anim.lastquesanim);
                    Animation animation2 = AnimationUtils.loadAnimation(contestActivity.this,R.anim.movendzoom);
                    Animation animation3 = AnimationUtils.loadAnimation(contestActivity.this,R.anim.reviewbtnanim);
                    contestwritten.startAnimation(animation1);
                    quescard.startAnimation(animation1);
                    scorelayout.startAnimation(animation2);
                    reviewbtn.startAnimation(animation3);
                    reviewbtn.setVisibility(View.VISIBLE);
                    totalquestionstextview.setText(String.valueOf(totalques));
                    correctanswerstextview.setText(String.valueOf(correctanswers));
                    finallinearlayout.setVisibility(View.VISIBLE);
                    finallinearlayout.startAnimation(animation3);

                }
            }
        }, 800);
    }

    public void openreviewpage(View view) {

        Intent intent = new Intent(this,ReviewActivity.class);
        startActivity(intent);
        finish();

    }
    public void enablenonclickable(View v){
        v.setEnabled(true);
        v.setClickable(false);
    }
}
