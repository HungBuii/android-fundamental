package com.gohool.firstlook.metertoinches;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText inputMeters;
    private Button calculate;
    private TextView resultInches;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        inputMeters = (EditText) findViewById(R.id.inputMeters);
        calculate = (Button) findViewById(R.id.calculate);
        resultInches = (TextView) findViewById(R.id.resultInches);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float inches = 0.0f;

                if (inputMeters.getText().toString().equals(""))
                {
                    resultInches.setText("Please enter valid number!");
                    resultInches.setTextColor(Color.RED);
                }
                else
                {
                    float meters = Float.parseFloat(inputMeters.getText().toString());
                    inches = meters * 39.37f;

                    resultInches.setText(String.format("%.2f", inches) + " inches");
                    resultInches.setTextColor(Color.WHITE);
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