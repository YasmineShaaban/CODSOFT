package com.example.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class favorites extends AppCompatActivity {

    TextView favlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        favlist = findViewById(R.id.favlist);

        Intent i = getIntent();
        favlist.setText(i.getStringExtra("FAVQ"));

    }
}