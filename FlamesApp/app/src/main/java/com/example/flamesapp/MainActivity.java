package com.example.flamesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button proceed_btn = findViewById(R.id.proceed);
        Button exit_btn = findViewById(R.id.exit_main);
        EditText yourName = findViewById(R.id.name_input);
        EditText partnerName = findViewById(R.id.partner_input);

        proceed_btn.setOnClickListener(view -> {
            if (yourName.getText().toString().equals("") || partnerName.getText().toString().equals("")) {
                Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(this, ResultScreen.class);
                intent.putExtra("your_name", yourName.getText().toString().toLowerCase());
                intent.putExtra("partner_name", partnerName.getText().toString().toLowerCase());
                startActivity(intent);
            }
        });

        exit_btn.setOnClickListener(view -> {
            finishAffinity();
            System.exit(0);
        });

    }
}