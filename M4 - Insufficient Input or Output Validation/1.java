package com.example.vulnerableapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private Button submitButton;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.userInput);
        submitButton = findViewById(R.id.submitButton);

        database = openOrCreateDatabase("vulnerableDB", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT);");

        submitButton.setOnClickListener(view -> {
            String input = userInput.getText().toString();
            String query = "INSERT INTO users (name) VALUES ('" + input + "');";
            database.execSQL(query); 
        });
    }

    @Override
    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }
}
