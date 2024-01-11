package com.example.meterstoinches;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText enterMeters;
    private Button convertButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterMeters = findViewById(R.id.metersEditText);
        convertButton = findViewById(R.id.convertButtonID);
        resultTextView = findViewById(R.id.resultID);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic
                double multiplier = 39.37;
                double result = 0.0;

                if(enterMeters.getText().toString().equals("") ||
                        enterMeters.getText().toString().equals("."))
                {
                    resultTextView.setTextColor(Color.RED);
                    resultTextView.setVisibility(View.VISIBLE);
                    resultTextView.setText(R.string.error_message);
                }
                else
                {
                    double meterValue = Double.parseDouble(enterMeters.getText().toString());
                    result = meterValue * multiplier;
                    resultTextView.setVisibility(View.VISIBLE);
                    resultTextView.setTextColor(Color.GREEN);
                    resultTextView.setText(String.format("%.2f", result) + " inches");
                }


            }
        });
    }
}