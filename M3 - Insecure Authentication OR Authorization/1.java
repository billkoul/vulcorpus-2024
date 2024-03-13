package com.example.insecureapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnAccess = findViewById(R.id.btnAccess);

        btnAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("backdoor".equals(v.getTag())) {
                    accessSensitiveFeatures();
                } else {
                    Toast.makeText(MainActivity.this, "Access Denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void accessSensitiveFeatures() {
        Toast.makeText(this, "Sensitive feature accessed!", Toast.LENGTH_SHORT).show();
    }
}