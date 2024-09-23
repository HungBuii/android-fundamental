package com.gohool.firstlook.grocerylist.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.gohool.firstlook.grocerylist.Model.Grocery;
import com.gohool.firstlook.grocerylist.Util.Constants;

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

    }

    // Get a grocery
    private Grocery getGrocery(int id)
    {
        return null;
    }

    // Get all groceries
    public List<Grocery> getAllGroceries()
    {
        return null;
    }

    // Update grocery
    public int updateGrocery(Grocery grocery)
    {
        return 0;
    }

    // Delete grocery
    public void deleteGrocery(int id)
    {

    }

    // Get count
    public int getGroceriesCount()
    {
        return 0;
    }
}
