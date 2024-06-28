package com.gohool.firstlook.petpio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView catView;
    private ImageView dogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        catView = (ImageView) findViewById(R.id.catID);
        dogView = (ImageView) findViewById(R.id.dogID);

        catView.setOnClickListener(this);
        dogView.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.petBioImageViewID), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onClick(View v)
    {
//        switch (v.getId())
//        {
//            case R.id.catID:
//                Toast.makeText(MainActivity.this, "Cat", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.dogID:
//                Toast.makeText(MainActivity.this, "Dog", Toast.LENGTH_LONG).show();
//                break;
//        } // can't use switch case because error from Android Studio update
        if (v.getId() == R.id.catID)
        {
            // go to second activity
            Intent catIntent = new Intent(MainActivity.this, BioActivity.class);
            catIntent.putExtra("name", "Jarvis");
            catIntent.putExtra("bio", "Great cat. Loves people and meows a lot!");
            startActivity(catIntent);

//            Toast.makeText(MainActivity.this, "Cat", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.dogID)
        {
            // go to second activity
            Intent dogIntent = new Intent(MainActivity.this, BioActivity.class);
            dogIntent.putExtra("name", "Dufus");
            dogIntent.putExtra("bio", "Great dog. Loves people barks and eat a lot!");
            startActivity(dogIntent);

//            Toast.makeText(MainActivity.this, "Dog", Toast.LENGTH_SHORT).show();
        }
    }
}