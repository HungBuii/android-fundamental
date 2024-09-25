package com.gohool.firstlook.grocerylist.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.gohool.firstlook.grocerylist.Data.DatabaseHandle;
import com.gohool.firstlook.grocerylist.Model.Grocery;
import com.gohool.firstlook.grocerylist.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.gohool.firstlook.grocerylist.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    /*  AlertDialog.Builder được sử dụng để cấu hình dialog (phần bên trong),
        còn AlertDialog là dialog thực tế được hiển thị trên màn hình (phần ngoài)
        sau khi cấu hình dialog.
    */
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText groceryItem;
    private EditText quantity;
    private Button saveButton;

    private DatabaseHandle db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHandle(this);

        byPassActivity();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
                createPopupDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createPopupDialog()
    {
        dialogBuilder = new AlertDialog.Builder(this); // Tạo một AlertDialog.Builder để xây dựng cửa sổ popup.
                                                              // this ở đây đại diện cho Context hiện tại (thường là một Activity).

        View view = getLayoutInflater().inflate(R.layout.popup, null); //Tạo layout cho cửa sổ popup bằng cách inflate file layout R.layout.popup.

        groceryItem = (EditText) view.findViewById(R.id.groceryItem);
        quantity = (EditText) view.findViewById(R.id.groceryQty);
        saveButton = (Button) view.findViewById(R.id.saveButton);

        dialogBuilder.setView(view); // Thiết lập layout cho cửa sổ popup.
        dialog = dialogBuilder.create(); // Tạo cửa sổ popup từ AlertDialog.Builder.
        dialog.show();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Todo: Save to db
                // Todo: Go to next screen

                if (!groceryItem.getText().toString().isEmpty() &&
                        !quantity.getText().toString().isEmpty())
                {
                    saveGroceryToDB(v);
                }
            }
        });

    }

    private void saveGroceryToDB(View v)
    {
        Grocery grocery = new Grocery();
        String newGrocery = groceryItem.getText().toString();
        String newGroceryQuantity = quantity.getText().toString();

        grocery.setName(newGrocery);
        grocery.setQuantity(newGroceryQuantity);

        // Save to db
        db.addGrocery(grocery);

        Snackbar.make(v, "Item saved to database", Snackbar.LENGTH_LONG).show(); // Same "Toast"

//        Log.d("Item added ID: ", String.valueOf(db.getGroceriesCount()));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                // start a new activity
                startActivity(new Intent(MainActivity.this, ListActivity.class));
            }
        }, 2000); // Delay 2 second
    }

    public void byPassActivity()
    {
        // Check if database is not empty, go to the ListActivity screen.
        // Otherwise, go to the MainActivity screen.

        if (db.getGroceriesCount() > 0)
        {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
            finish();
        }
    }

}