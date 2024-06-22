package com.gohool.firstlook.tipcalculation;

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

public class MainActivity extends AppCompatActivity
{

    private EditText principalAmountID;
    private SeekBar percentageTipID;
    private TextView showPercentageTipID;
    private Button calculateButtonID;
    private TextView showTipID;
    private TextView showTotalAmountID;

    private float principalAmount;
    private int percentageTip;
    private float totalAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        principalAmountID = (EditText) findViewById(R.id.principalAmountID);
        percentageTipID = (SeekBar) findViewById(R.id.percentageTipID);
        showPercentageTipID = (TextView) findViewById(R.id.showPercentageTipID);
        calculateButtonID = (Button) findViewById(R.id.calculateButtonID);
        showTipID = (TextView) findViewById(R.id.showTipID);
        showTotalAmountID = (TextView) findViewById(R.id.showTotalAmountID);



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

        calculateButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.showPercentageTipID), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calculate()
    {
        if (!principalAmountID.getText().toString().equals(""))
        {
            principalAmount = Float.parseFloat(principalAmountID.getText().toString());
            float tip = 0.0f;
            tip = principalAmount * percentageTip / 100;
            showTipID.setText("Tip: " + String.valueOf(String.format("%.4f", tip)) + "$");

            totalAmount = principalAmount + tip;
            showTotalAmountID.setText("Total bill (about tip): " + String.valueOf(totalAmount) + "$");
        } else {
            showTipID.setText("Tip: 0$");
            showTotalAmountID.setText("Total bill (about tip): 0$");
            Toast.makeText(this, "Please enter valid amount!", Toast.LENGTH_SHORT).show();
        }


    }
}