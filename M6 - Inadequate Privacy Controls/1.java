package com.example.inadequateprivacycontrols;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "SensitiveDataPrefs";
    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_WORLD_READABLE);
        
        SharedPreferences.Editor editor = sharedPreferences.edit();
        
        editor.putString(USERNAME_KEY, "user@example.com");
        editor.putString(PASSWORD_KEY, "a6nscaoin1c!V");

        editor.apply();
    }
}