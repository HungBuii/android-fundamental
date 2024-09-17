package com.gohool.firstlook.sharedprefsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.SharedPreferencesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private TextView result;
    private EditText enterMessage;
//    private SharedPreferencesCompat myPrefs;
    private SharedPreferences myPrefs;
    private static final String PREFS_NAME = "myPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        enterMessage = (EditText) findViewById(R.id.enterName);
        result = (TextView) findViewById(R.id.resultTextView);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myPrefs = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString("message", enterMessage.getText().toString());
                editor.commit();
            }
        });

        // Get data back
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, 0);
        if (prefs.contains("message"))
        {
            String message = prefs.getString("message", "Not found!");
            result.setText(message);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}