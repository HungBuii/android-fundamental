package com.gohool.firstlook.petbioapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText fBillID;
    private SeekBar percentageTipID;
    private TextView showPercentageTipID;
    private Button calculateID;
    private TextView showTipID;
    private TextView showTotalBillID;
    /* --------------------- */

    private int percentageTip = 0;
    private float f_total = 0.0f;
    private float tip = 0.0f;
    private float a_total = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fBillID = (EditText) findViewById(R.id.fBillID);
        percentageTipID = (SeekBar) findViewById(R.id.percentageTipID);
        showPercentageTipID = (TextView) findViewById(R.id.showPercentageTipID);
        calculateID = (Button) findViewById(R.id.calculateID);
        showTipID = (TextView) findViewById(R.id.showTipID);
        showTotalBillID = (TextView) findViewById(R.id.showTotalBillID);

        percentageTipID.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                showPercentageTipID.setText(String.valueOf(percentageTipID.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                percentageTip = percentageTipID.getProgress();
            }
        });

        calculateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });



    }

    public void calculate()
    {
        if (!fBillID.getText().toString().equals(""))
        {
            f_total = Float.parseFloat(fBillID.getText().toString());

            tip = f_total * percentageTip / 100;

            showTipID.setText(String.valueOf(tip));

            a_total = f_total + tip;
            showTotalBillID.setText(String.valueOf(a_total));
        }
        else
        {
            Toast.makeText(this, "Please enter your bill", Toast.LENGTH_SHORT).show();
        }
    }


}