package com.example.helloword;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile_fragment extends Fragment {

    Button makechnages;
    TextView yourname,youryear;


    public profile_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_fragment, container, false);
        makechnages = view.findViewById(R.id.makechangesbutton);
        yourname = view.findViewById(R.id.entername_edittextid);
        youryear = view.findViewById(R.id.selectyear_spinnerid);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Name_year", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name","");
        String year = sharedPreferences.getString("Year","");
        yourname.setText(name);
        youryear.setText(year);

        makechnages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),profileupdate.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
