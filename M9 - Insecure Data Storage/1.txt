package com.example.insecuredatastorage;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILENAME = "user_credentials.txt";
    private static final String TAG = "InsecureDataStorage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = "user@example.com";
        String password = "password123";

        String userCredentials = "Username: " + username + "\nPassword: " + password;

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            outputStream.write(userCredentials.getBytes());
            outputStream.close();
        } catch (IOException e) {
            Log.e(TAG, "File write failed", e);
        }
    }
}