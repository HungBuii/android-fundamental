package com.example.trymeoriginal;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View windowView;
    private Button tryMeButton;
    private int[] colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id, property
        windowView = findViewById(R.id.windowViewID);
        tryMeButton = findViewById(R.id.tryMeButton);
        colors = new int[] {Color.BLUE, Color.GREEN, Color.RED};

        // activity
        tryMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int colorArrayLength = colors.length;

                Random random = new Random();
                int colorNum = random.nextInt(colorArrayLength);

                windowView.setBackgroundColor(colors[colorNum]);

                Log.d("Random", String.valueOf(colorNum));
            }
        });


    }
}