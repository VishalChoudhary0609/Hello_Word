package com.example.helloword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

import static com.example.helloword.MainActivity.UserType;
import static com.example.helloword.MainActivity.mAuth;

public class LoginActivity extends AppCompatActivity {

    EditText entername;
    Spinner selectyear;
    Button getstarted;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference Users = myRef.child("Users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        entername = findViewById(R.id.entername_edittextid);
        selectyear = findViewById(R.id.selectyear_spinnerid);
        getstarted = findViewById(R.id.getstarted_btnid);
        String[] selectyeararray = {"","1st year","2nd year","3rd year","4th year"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinneritemlayout,selectyeararray);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectyear.setAdapter(arrayAdapter);



    }

    public void register(View view) {
        final String name = entername.getText().toString().trim();
        final String year = selectyear.getSelectedItem().toString();
     if(name.equals("") || year.equals("")){
         Toast.makeText(this,"enter complete info",Toast.LENGTH_SHORT).show();
     }else {
         mAuth.signInAnonymously()
                 .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {

                             HashMap<String,String> userinfo  = new HashMap<>();
                             userinfo.put("Name",name);
                             userinfo.put("year",year);
                             userinfo.put("contestTaken","false");

                             Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(userinfo);
                             Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Score").setValue(0);
                             UserType = "LoggedIn";
                             FirebaseMessaging.getInstance().subscribeToTopic(UserType)
                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             if (!task.isSuccessful()) {

                                             }
                                         }
                                     });



                             // Sign in success, update UI with the signed-in user's information
                             Intent intent = new Intent(LoginActivity.this, Homeactivity.class);
                             Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();

                             SharedPreferences sharedPreferences = getSharedPreferences("Name_year", Context.MODE_PRIVATE);
                             SharedPreferences.Editor editor = sharedPreferences.edit();
                             SharedPreferences sharedPreferencesForContest = getSharedPreferences("contestTakenOrNor",MODE_PRIVATE);
                             SharedPreferences.Editor editor1 = sharedPreferencesForContest.edit();
                             editor.putString("Name", entername.getText().toString());
                             editor.putString("Year", selectyear.getSelectedItem().toString());
                             editor1.putString("contestTaken","false");
                             editor1.commit();
                             editor.commit();
                             startActivity(intent);
                             finish();
                         } else {
                             Toast.makeText(LoginActivity.this, "Something went wrong....", Toast.LENGTH_SHORT).show();
                         }

                         // ...
                     }
                 });
     }
    }
}
