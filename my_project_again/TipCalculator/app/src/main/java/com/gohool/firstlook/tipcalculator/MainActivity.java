package com.gohool.firstlook.tipcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText _amountBill;
    private SeekBar _taxPercent;
    private TextView _showTaxPercent;
    private SeekBar _tipPercent;
    private TextView _showTipPercent;
    private Button _calculateButton;
    private TextView _showAmountTip;
    private TextView _showTotalBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        _amountBill = (EditText) findViewById(R.id.amountBill);
        _taxPercent = (SeekBar) findViewById(R.id.taxPercent);
        _showTaxPercent = (TextView) findViewById(R.id.showTaxPercent);
        _tipPercent = (SeekBar) findViewById(R.id.tipPercent);
        _showTipPercent = (TextView) findViewById(R.id.showTipPercent);
        _calculateButton = (Button) findViewById(R.id.calculateButton);
        _showAmountTip = (TextView) findViewById(R.id.showAmountTip);
        _showTotalBill = (TextView) findViewById(R.id.showTotalBill);


        // Enter your bill here:
        float amountTip = Float.parseFloat(String.valueOf(_amountTip));

        // Seekbar Tax VAT Percent
        _taxPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Seekbar Tip Percent
        _tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Button Calculate Tip
        _calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show amount tip

                // Show total bill
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}