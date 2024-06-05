package com.gohool.firstlook.alertdialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder alertDialog;
    private Button showDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        showDialog = (Button) findViewById(R.id.showDialogID);
        showDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the actual dialog (alert dialog)
                alertDialog = new AlertDialog.Builder(MainActivity.this);

                // set things up - setup title
                alertDialog.setTitle(R.string.title);
//                alertDialog.setTitle(getResources().getString(R.string.title))
                alertDialog.setIcon(android.R.drawable.btn_star_big_on);

                // Set message
                alertDialog.setMessage(getResources().getString(R.string.message));

                // Set Cancelable
                alertDialog.setCancelable(false);

                // Set positive button
                alertDialog.setPositiveButton(getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MainActivity.this.finish();
                            }
                        });

                // set negative button
                alertDialog.setNegativeButton(getResources().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                // create the actual dialog
                AlertDialog dialog = alertDialog.create();

                // show the dialog
                dialog.show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}