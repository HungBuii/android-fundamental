package com.gohool.firstlook.petpio;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BioActivity extends AppCompatActivity {

    private ImageView petImageView;
    private TextView petName;
    private TextView petBio;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bio);

        petImageView = (ImageView) findViewById(R.id.petImageViewID);
        petName = (TextView) findViewById(R.id.nameID);
        petBio = (TextView) findViewById(R.id.bioTextID);

        extras = getIntent().getExtras();
        if (extras != null)
        {
            String name = extras.getString("name");
            String bio = extras.getString("bio");

            setUp(name, bio);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.petBioImageViewID), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setUp(String name, String bio)
    {
        if (name.equals("Dufus"))
        {
            // we show dog stuff
            petImageView.setImageDrawable(ContextCompat.getDrawable(BioActivity.this, R.drawable.icon_lg_dog));
            petBio.setText(bio);
            petName.setText(name);
        }
        else if (name.equals("Jarvis"))
        {
            // we show cat stuff
            petImageView.setImageDrawable(ContextCompat.getDrawable(BioActivity.this, R.drawable.icon_lg_cat));
            petBio.setText(bio);
            petName.setText(name);
        }
    }

}