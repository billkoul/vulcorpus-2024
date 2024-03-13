package com.example.unprotectedapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView secretTextView = findViewById(R.id.secret_text_view);
        String secretApiKey = "1234567890abcdef";        
        secretTextView.setText("Secret API Key: " + secretApiKey);
        
    }
}