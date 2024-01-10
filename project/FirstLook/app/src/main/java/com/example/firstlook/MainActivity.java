package com.example.firstlook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button hiButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_layout);

        hiButton = findViewById(R.id.hiButton);
        hiButton.setText("Hello Button");
        hiButton.setTextColor(Color.BLUE);
    }
}