package com.example.helloword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class profileupdate extends AppCompatActivity {
    EditText entername;
    Spinner selectyear;
    Button savechanges;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference user = database.getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileupdate);

        entername = findViewById(R.id.entername_edittextid);
        selectyear = findViewById(R.id.selectyear_spinnerid);
        savechanges = findViewById(R.id.getstarted_btnid);
        String[] selectyeararray = {"","1st year","2nd year","3rd year","4th year"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinneritemlayout,selectyeararray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectyear.setAdapter(arrayAdapter);
    }

    public void savechanges(View view) {

        String name = entername.getText().toString().trim();
        String year = selectyear.getSelectedItem().toString();

        if(!name.equals("")&& !year.equals("")){

            user.child("Name").setValue(name);
            user.child("year").setValue(year).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    SharedPreferences sharedPreferences = getSharedPreferences("Name_year",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Name",entername.getText().toString().trim());
                    editor.putString("Year",selectyear.getSelectedItem().toString());
                    editor.commit();
                    Intent intent = new Intent(profileupdate.this,Homeactivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }
}
