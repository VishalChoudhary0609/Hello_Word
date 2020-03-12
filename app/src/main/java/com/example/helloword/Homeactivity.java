package com.example.helloword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.Locale;

public class Homeactivity extends AppCompatActivity {

    static TextToSpeech textToSpeech;
    static  String fragdecide;
    BottomAppBar bottomAppBar;
    static  int anycount = 0;

    home_fragment homeFragment = new home_fragment();
    profile_fragment profileFragment = new profile_fragment();
    contest_fragment contestFragment = new contest_fragment();
    contestcheckerfrag leaderboardfreg = new contestcheckerfrag();
    ImageButton profilebtn, contestbtn;
    FloatingActionButton home_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);

         profilebtn = findViewById(R.id.profile_btn_id);
         bottomAppBar = findViewById(R.id.bottom_app_bar);
         home_btn = findViewById(R.id.home_floating);
         contestbtn = findViewById(R.id.contest_btn_id);
         home_btn = findViewById(R.id.home_floating);
         home_btn.setSelected(true);
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });
         getSupportFragmentManager().beginTransaction().add(R.id.framelayout,homeFragment).commit();

    }

    public void home_clicked(View view) {



        home_btn.setSelected(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,homeFragment).commit();
        profilebtn.setSelected(false);
        contestbtn.setSelected(false);



    }

    public void contest_clicked(View view) {

        SharedPreferences sharedPreferencesForContest = getSharedPreferences("contestTakenOrNor",MODE_PRIVATE);
        fragdecide = sharedPreferencesForContest.getString("contestTaken","b");
        contestbtn.setSelected(true);
        if(fragdecide.equals("true")){getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,leaderboardfreg).commit();}
        else if(fragdecide.equals("false")) getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,contestFragment).commit();
        profilebtn.setSelected(false);
        home_btn.setSelected(false);

    }

    public void profile_clicked(View view) {
        profilebtn.setSelected(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,profileFragment).commit();
        contestbtn.setSelected(false);
        home_btn.setSelected(false);
    }

    public void emptybtn_clicked(View view) {
        home_clicked(home_btn);

    }

    @Override
    protected void onPause() {
        super.onPause();
        textToSpeech.shutdown();
    }

}
