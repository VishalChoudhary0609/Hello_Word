package com.example.helloword;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class home_fragment extends Fragment {

    TextView wordfotheday,description,examples,textView;
    ImageButton speak;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference wordOfTheDayRef = myRef.child("word of the day");
    String word,meaning,example;
    ProgressBar loading;
    BottomAppBar bottomAppBar;
    FloatingActionButton home_btn;


    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        wordfotheday = view.findViewById(R.id.WordoftheDay);
        description = view.findViewById(R.id.description);
        loading = view.findViewById(R.id.wordloading);
        loading.setVisibility(View.VISIBLE);
        bottomAppBar = getActivity().findViewById(R.id.bottom_app_bar);
        home_btn = getActivity().findViewById(R.id.home_floating);
        textView = view.findViewById(R.id.exampletextview);
        speak = view.findViewById(R.id.imageButton);
        examples= view.findViewById(R.id.showexampletextview);

        wordOfTheDayRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               storeword storeword =  dataSnapshot.getValue(storeword.class);
                loading.setVisibility(View.INVISIBLE);
                word = storeword.getWord();
                wordfotheday.setText(word);
                wordfotheday.setVisibility(View.VISIBLE);
                speak.setVisibility(View.VISIBLE);
                meaning = storeword.getMeaning();
                description.setText(meaning);
                description.setVisibility(View.VISIBLE);
                example = storeword.getExamples();
                examples.setText(example);
                textView.setVisibility(View.VISIBLE);
                int x=Homeactivity.anycount;
                Handler handler = new Handler();
                if(x==0){
                    Animation animation4 = AnimationUtils.loadAnimation(getActivity(),R.anim.homebottomanim);

                    bottomAppBar.startAnimation(animation4);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            bottomAppBar.setVisibility(View.VISIBLE);
                            home_btn.setVisibility(View.VISIBLE);
                        }
                    });
                    Homeactivity.anycount= 1;

                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        bottomAppBar.setVisibility(View.VISIBLE);
                        home_btn.setVisibility(View.VISIBLE);
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
     });

        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Homeactivity.textToSpeech.speak(word, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        return view;

    }



}
