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
    private TextView _showAmountTax;
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
        _showAmountTax = (TextView) findViewById(R.id.showAmountTax);
        _showAmountTip = (TextView) findViewById(R.id.showAmountTip);
        _showTotalBill = (TextView) findViewById(R.id.showTotalBill);

        // Seekbar Tax VAT Percent
        _taxPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _showTaxPercent.setText(_taxPercent.getProgress() + "%");
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
                _showTipPercent.setText(_tipPercent.getProgress() + "%");
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

                // Enter your bill here:
                double _amountBill_ = Double.parseDouble(_amountBill.getText().toString());

                // Show amount Tax
                int resultTax = 0;
                resultTax = (int) (_amountBill_ * _taxPercent.getProgress() / 100);
                _showAmountTax.setText("Tax in your bill: " + String.format("%,d", resultTax) + "$");

                // Show amount tip
                int resultTip = 0;
                resultTip = (int) (_amountBill_ * _tipPercent.getProgress() / 100);
                _showAmountTip.setText("Your bill after tip: " + String.format("%,d", resultTip) + "$");

                // Show total bill
                int resultBill = 0;
                resultBill = (int) (_amountBill_ + resultTax + resultTip);
                _showTotalBill.setText("Your bill after tax and tip: " + String.format("%,d", resultBill) + "$");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}