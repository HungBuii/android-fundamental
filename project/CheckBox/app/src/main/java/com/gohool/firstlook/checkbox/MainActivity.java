package com.gohool.firstlook.checkbox;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private CheckBox momID;
    private CheckBox dadID;
    private CheckBox grandpaID;
    private Button buttonID;
    private TextView showPersonID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        momID = (CheckBox) findViewById(R.id.momID);
        dadID = (CheckBox) findViewById(R.id.dadID);
        grandpaID = (CheckBox) findViewById(R.id.grandpaID);
        showPersonID = (TextView) findViewById(R.id.showPersonID);
        buttonID = (Button) findViewById(R.id.buttonID);

        buttonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder stringBuilder = new StringBuilder();

                if (momID.isChecked()) {
                    stringBuilder.append(momID.getText().toString());
                } else if (dadID.isChecked()) {
                    stringBuilder.append(dadID.getText().toString());
                } else if (grandpaID.isChecked()) {
                    stringBuilder.append(grandpaID.getText().toString());
                }

                showPersonID.setText(stringBuilder);

            }
        });

//        buttonID.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String msg = " ";
//
//                if (momID.isChecked())
//                {
//                    msg += "Mom";
//                }
//                if (dadID.isChecked()) {
//                    msg += "\nDad";
//                }
//                if (grandpaID.isChecked()) {
//                    msg += "\nGrandpa";
//                }
//
//                Toast.makeText(MainActivity.this, msg + "\nis selected", Toast.LENGTH_SHORT).show();
//            }
//        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}