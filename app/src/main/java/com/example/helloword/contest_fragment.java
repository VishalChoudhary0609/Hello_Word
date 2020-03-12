package com.example.helloword;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class contest_fragment extends Fragment {
    Button entercontest;


    public contest_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contest_fragment, container, false);
        entercontest = view.findViewById(R.id.entercontestbtn);

        entercontest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                entercontest.setClickable(false);
                Intent intent = new Intent(getActivity(),contestActivity.class);
                entercontest.setClickable(true);
                startActivity(intent);

            }
        });
        return view;
    }

}
