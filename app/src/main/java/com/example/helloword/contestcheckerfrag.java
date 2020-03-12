package com.example.helloword;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class contestcheckerfrag extends Fragment {
    Button gotoreview;
    static List<winnerclass> winnerlist = Collections.EMPTY_LIST;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    RecyclerView recyclerView;
    Button reviewbtn;
    ProgressBar progressBar;
    DatabaseReference Usersref = db.getReference().child("Users");


    public contestcheckerfrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_contestcheckerfrag, container, false);
        gotoreview = view.findViewById(R.id.reviewbtn);
        progressBar = view.findViewById(R.id.winnerloadprogress);
        progressBar.setVisibility(View.VISIBLE);
        reviewbtn = view.findViewById(R.id.reviewbtn);
        recyclerView = view.findViewById(R.id.leaderboardrecycler_id);
        winnerlist  = new ArrayList<>();
        Usersref.orderByChild("Score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(winnerlist.size()==20){
                        break;
                    }
                    winnerlist.add(snapshot.getValue(winnerclass.class));

                }
                Collections.reverse(winnerlist);
                progressBar.setVisibility(View.INVISIBLE);
                reviewbtn.setVisibility(View.VISIBLE);
                winneradapter adapter = new winneradapter(getActivity(),winnerlist);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
                reviewbtn.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "something went wrong..... \n check internet connection", Toast.LENGTH_SHORT).show();

            }
        });
        gotoreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ReviewActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }


}
