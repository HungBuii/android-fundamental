package com.gohool.firstlook.firstlook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.mButton);
        mTextView = (TextView) findViewById(R.id.mTextView);
        mEditText = (EditText) findViewById(R.id.editTextText);

        //change the title of the button
        mButton.setText(R.string.button_name);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enteredText;
                enteredText = mEditText.getText().toString();

                mTextView.setVisibility(View.VISIBLE);
//                mTextView.setText(R.string.show_text);
                mTextView.setText(enteredText);
            }
        });

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }

//    public void ShowMe(View view)
//    {
//        mTextView.setVisibility(View.VISIBLE);
//        mTextView.setText(R.string.show_text);
//    }

}