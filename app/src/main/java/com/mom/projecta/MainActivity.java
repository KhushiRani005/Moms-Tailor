package com.mom.projecta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference();

        DatabaseReference usersRef = ref.child("users");

        Map<String, String> users = new HashMap<>();
        users.put("alanisawesome", "hey");
        users.put("gracehop", "hello");

        usersRef.setValue(users);

    }
}