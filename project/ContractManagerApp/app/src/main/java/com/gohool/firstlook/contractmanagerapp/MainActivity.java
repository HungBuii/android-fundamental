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

        // Contacts count
        Log.d("Contacts count: ", String.valueOf(db.getContactsCount()));

        // Insert contacts
//        Log.d("Insert: ", "Inserting...");
//        db.addContact(new Contact(1, "Nguyễn Văn A", "0123456789"));
//        db.addContact(new Contact("Nguyễn Văn B", "0123456789"));
//        db.addContact(new Contact("Nguyễn Văn C", "0123456789"));
//        db.addContact(new Contact("Nguyễn Văn D", "0123456789"));
//        db.addContact(new Contact("Nguyễn Văn E", "0123456789"));
//        db.addContact(new Contact("Nguyễn Văn F", "0123456789"));
//        db.addContact(new Contact("Nguyễn Văn G", "0123456789"));

        // Get a contact
//        Contact oneContact = db.getContact(1);
//        Log.d("Contact:", "Id: " + oneContact.getId() + ", Name: " + oneContact.getName() + ", Phone: " + oneContact.getPhoneNumber());

        // Update contact
//        oneContact.setName("Hung ne");
//        oneContact.setPhoneNumber("12341324");
//        int newContact = db.updateContact(oneContact);
//        Log.d("Contact " + newContact + " update: " , "Id: " + oneContact.getId() + ", Name: " + oneContact.getName() + ", Phone: " + oneContact.getPhoneNumber() );

        // Delete contact
//        for (int i = ; i <= ; i++)
//        {
//            Contact oneContact = db.getContact(i);
//            db.deleteContact(oneContact);
//        }
//        db.deleteContact(oneContact);


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