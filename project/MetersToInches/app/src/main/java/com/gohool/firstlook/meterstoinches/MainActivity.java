package com.gohool.firstlook.meterstoinches;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 1m = 39,3701 inch
    private EditText enterMeters;
    private Button convertButton;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        enterMeters = (EditText) findViewById(R.id.metersEditText);
        resultTextView = (TextView) findViewById(R.id.resultID);
        convertButton = (Button) findViewById(R.id.convertID);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double multipler = 39.3701;
                double result = 0.0;

                if (enterMeters.getText().toString().equals("")) {
                    resultTextView.setText("Please enter a valid value!");
                    resultTextView.setTextColor(Color.RED);
                } else {
                    double meterValue = Double.parseDouble(enterMeters.getText().toString());
                    result = meterValue * multipler;

                    resultTextView.setTextColor(Color.GREEN);
//                resultTextView.setText(Double.toString(result) + " inches");
                    resultTextView.setText(String.format("%.2f", result) + " inches");
                }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}