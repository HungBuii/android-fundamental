package com.gohool.firstlook.trymeapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button changeColorButton;
    private View windowView;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        changeColorButton = (Button) findViewById(R.id.changeColorButton);
        windowView = findViewById(R.id.backgroundColor);

        int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.RED, Color.BLACK, Color.WHITE};

        windowView.setBackgroundColor(Color.WHITE);

        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int randomNum = random.nextInt(colors.length);

                windowView.setBackgroundColor(colors[randomNum]);

            }
        });

    }
}