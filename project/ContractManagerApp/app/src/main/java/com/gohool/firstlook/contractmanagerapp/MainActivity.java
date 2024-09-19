package com.gohool.firstlook.contractmanagerapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import Data.DatabaseHandle;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        DatabaseHandle db = new DatabaseHandle(this);

        // Insert contacts
        Log.d("Insert: ", "Inserting...");
        db.addContact(new Contact("Nguyễn Văn A", "0123456789"));
        db.addContact(new Contact("Nguyễn Văn B", "0123456789"));
        db.addContact(new Contact("Nguyễn Văn C", "0123456789"));
        db.addContact(new Contact("Nguyễn Văn D", "0123456789"));
        db.addContact(new Contact("Nguyễn Văn E", "0123456789"));
        db.addContact(new Contact("Nguyễn Văn F", "0123456789"));
        db.addContact(new Contact("Nguyễn Văn G", "0123456789"));

        // Read them back
        Log.d("Reading: ", "Reading all contacts...");
        List<Contact> contactList = db.getAllContacts();
        for (Contact c : contactList) {
//            String log = "Id: " + c.getId() + ", Name: " + c.getName() + ", Phone: " + c.getPhoneNumber();
            Log.d("Contact:", "Id: " + c.getId() + ", Name: " + c.getName() + ", Phone: " + c.getPhoneNumber());
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}