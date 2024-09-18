package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Utils.Util;

public class DatabaseHandle extends SQLiteOpenHelper
{

    public DatabaseHandle(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                                        Util.KEY_ID + " INTEGER PRIMARY KEY," +
                                        Util.KEY_NAME + " TEXT," +
                                        Util.KEY_PHONE_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Dropping is deleting table
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        // Create table again
        onCreate(db);
    }
}
