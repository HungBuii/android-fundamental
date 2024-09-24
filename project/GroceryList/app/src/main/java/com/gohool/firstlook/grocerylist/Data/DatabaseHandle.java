package com.gohool.firstlook.grocerylist.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.gohool.firstlook.grocerylist.Model.Grocery;
import com.gohool.firstlook.grocerylist.Util.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandle extends SQLiteOpenHelper
{
    private Context ctx; // Context là một khái niệm quan trọng đại diện cho môi trường hiện tại của
                         // ứng dụng hoặc một thành phần cụ thể trong ứng dụng. Nó cung cấp quyền
                         // truy cập vào các tài nguyên của hệ thống và ứng dụng

    public DatabaseHandle(@Nullable Context context) {
//        super(context, name, factory, version);
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_GROCERY_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "("
                                      + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                                      + Constants.KEY_GROCERY_ITEM + " TEXT,"
                                      + Constants.KEY_QTY_NUMBER + " TEXT,"
                                      + Constants.KEY_DATE_NAME + " LONG);";

        db.execSQL(CREATE_GROCERY_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    /*
        CRUD Operations (Create, Read, Update, Delete)
    */

    // Add grocery
    public void addGrocery(Grocery grocery)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());

        // Insert the row
        db.insert(Constants.TABLE_NAME, null, values);

        Log.d("Saved!!", "Saved to DB");
    }

    // Get a grocery
    public Grocery getGrocery(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID,
                                Constants.KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER,
                                Constants.KEY_DATE_NAME},
                                Constants.KEY_ID + "=?",
                                new String[] {String.valueOf(id)}, null, null, null, null);

        Grocery grocery = new Grocery();
        if (cursor != null)
        {
            cursor.moveToFirst();

            // Get grocery
            int idIndex = cursor.getColumnIndex(Constants.KEY_ID);
            if (idIndex >= 0)
            {
                grocery.setId(Integer.parseInt(cursor.getString(idIndex)));
            }

            int nameIndex = cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM);
            if (nameIndex >= 0) {
                grocery.setName(cursor.getString(nameIndex));
            }

            int quantityIndex = cursor.getColumnIndex(Constants.KEY_QTY_NUMBER);
            if (quantityIndex >= 0) {
                grocery.setQuantity(cursor.getString(quantityIndex));
            }

            // convert timestamp to something readable
            int dateIndex = cursor.getColumnIndex(Constants.KEY_DATE_NAME);
            if (dateIndex >= 0) {
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(dateIndex)).getTime());
                grocery.setDateItemAdded(formattedDate);
            }
        }




//        grocery.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
//        grocery.setName(cursor.getString(cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM)));
//        grocery.setQuantity(cursor.getString(cursor.getColumnIndex(Constants.KEY_QTY_NUMBER)));

        // convert timestamp to something readable
//        java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
//        String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME))).getTime());

//        grocery.setDateItemAdded(formattedDate);

        return grocery;
    }

    // Get all groceries
    public List<Grocery> getAllGroceries()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Grocery> groceryList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {
                                Constants.KEY_ID, Constants.KEY_GROCERY_ITEM, Constants.KEY_QTY_NUMBER, Constants.KEY_DATE_NAME},
                                null, null, null, null, Constants.KEY_DATE_NAME + " DESC", null);

        if (cursor.moveToFirst())
        {
            do {
                Grocery grocery = new Grocery();
                int idIndex = cursor.getColumnIndex(Constants.KEY_ID);
                if (idIndex >= 0)
                {
                    grocery.setId(Integer.parseInt(cursor.getString(idIndex)));
                }

                int nameIndex = cursor.getColumnIndex(Constants.KEY_GROCERY_ITEM);
                if (nameIndex >= 0) {
                    grocery.setName(cursor.getString(nameIndex));
                }

                int quantityIndex = cursor.getColumnIndex(Constants.KEY_QTY_NUMBER);
                if (quantityIndex >= 0) {
                    grocery.setQuantity(cursor.getString(quantityIndex));
                }

                // convert timestamp to something readable
                int dateIndex = cursor.getColumnIndex(Constants.KEY_DATE_NAME);
                if (dateIndex >= 0) {
                    java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                    String formattedDate = dateFormat.format(new Date(cursor.getLong(dateIndex)).getTime());
                    grocery.setDateItemAdded(formattedDate);
                }

                // Add to the groceryList
                groceryList.add(grocery);
            } while (cursor.moveToNext());
        }
        return groceryList;
    }

    // Update grocery
    public int updateGrocery(Grocery grocery)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GROCERY_ITEM, grocery.getName());
        values.put(Constants.KEY_QTY_NUMBER, grocery.getQuantity());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());

        // update row
        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?",
                        new String[] {String.valueOf(grocery.getId())}); // return ID of row updated
    }

    // Delete grocery
    public void deleteGrocery(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + "=?",
                new String[] {String.valueOf(id)});
        db.close();
    }

    // Get count
    public int getGroceriesCount()
    {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null); // rawQuery: Runs the provided SQL and returns a "Cursor" over the result set (multiple results).

        return cursor.getCount();
    }
}
