package com.gohool.firstlook.trymeoriginal;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private View windowView;
    private Button tryMeButton;
    private int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        windowView = findViewById(R.id.WindowViewID);

        colors = new int[] {Color.RED, Color.BLUE, Color.CYAN, Color.WHITE, Color.GREEN};

        tryMeButton = (Button) findViewById(R.id.tryMeButton);
        tryMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int colorsArrayLength = colors.length;
                int colorNum = random.nextInt(colorsArrayLength); // 0 -> x-1
                windowView.setBackgroundColor(colors[colorNum]);
                Log.d("Index", String.valueOf(colorNum));
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.WindowViewID), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}