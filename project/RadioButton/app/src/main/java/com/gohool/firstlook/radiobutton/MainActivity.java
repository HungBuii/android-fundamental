package com.gohool.firstlook.radiobutton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button buttonCheck;
//    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupID);
        buttonCheck = (Button) findViewById(R.id.buttonID);
//        show = (TextView) findViewById(R.id.showID);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedButton = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedButton);

                if (selectedButton == -1)
                {
//                    show.setText("Nothing");
                    Toast.makeText(MainActivity.this, "Nothing", Toast.LENGTH_SHORT).show();
                }
                else {
//                    show.setText(radioButton.getText());
                    Toast.makeText(MainActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
                }

            }
        });

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                radioButton = (RadioButton) findViewById(checkedId);
//
//                switch (radioButton.getId())
//                {
//                    case R.id.yesID :
//                        if (radioButton.isChecked())
//                        {
//                            Log.d("RD", "YES!!");
//                        }
//                        break;
//
//                    case R.id.maybeID :
//                        if (radioButton.isChecked())
//                        {
//                            Log.d("RD", "MAYBE!!");
//                        }
//                        break;
//
//                    case R.id.noID :
//                        if (radioButton.isChecked())
//                        {
//                            Log.d("RD", "NOPE!!");
//                        }
//                        break;
//                }
//
//            }
//        });

//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @SuppressLint("NonConstantResourceId")
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                radioButton = (RadioButton) findViewById(checkedId);
//
//                if (radioButton == (RadioButton) findViewById(R.id.yesID))
//                {
//                    Log.d("RD", "YES!!");
//                }
//                else if (radioButton == (RadioButton) findViewById(R.id.noID))
//                {
//                    Log.d("RD", "NOPE!!");
//                }
//                else if (radioButton == (RadioButton) findViewById(R.id.maybeID))
//                {
//                    Log.d("RD", "MAYBE!!");
//                }
//
//            }
//        });
        

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}