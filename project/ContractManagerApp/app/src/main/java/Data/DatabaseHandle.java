package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
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

    /*
        CRUD operations (Create, Read, Update, Delete)
     */

    // Add contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase(); // write data

        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME, contact.getName());
        value.put(Util.KEY_PHONE_NUMBER, contact.getPhoneNumber());

        // Insert to row
        db.insert(Util.TABLE_NAME, null, value);
        db.close(); // close database connection
    }

    // Get a contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase(); // only read data

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID,
                                Util.KEY_NAME, Util.KEY_PHONE_NUMBER}, Util.KEY_ID + "=?",
                                new String[] {String.valueOf(id)}, null, null, null, null);

        /*
            Cursor: Cursor là một interface cung cấp truy cập đến kết quả của một truy vấn cơ sở dữ liệu.
                Có thể hình dung nó giống như một con trỏ trỏ đến các hàng dữ liệu trong kết quả truy vấn.
         */

        /*
            Thực hiện truy vấn SELECT để lấy dữ liệu contact từ bảng Util.TABLE_NAME.

            Tham số đầu tiên là tên bảng.
            Tham số thứ hai là mảng các cột cần lấy (id, name, phone_number).
            Tham số thứ ba là điều kiện WHERE (id=?).
            Tham số thứ tư là mảng các giá trị thay thế cho dấu ? trong điều kiện WHERE.
            Các tham số còn lại là groupBy, having, orderBy, limit (đều là null trong trường hợp này).

        */

        if (cursor != null)
        {
            cursor.moveToFirst(); // Kiểm tra nếu cursor không phải null (tức là có dữ liệu trả về)
                                  // và di chuyển con trỏ đến dòng đầu tiên.

        }

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                                     cursor.getString(1), cursor.getString(2));

        /*
            Integer.parseInt(cursor.getString(0)): Chuyển đổi giá trị id từ String sang int.
            cursor.getString(1): Lấy giá trị name.
            cursor.getString(2): Lấy giá trị phone_number.
         */

        return contact;
    }

    // Get all contacts
    public List<Contact> getAllContacts()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Contact> contactList = new ArrayList<>();

        // Select all contacts
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        // Loop through our contacts
        if (cursor.moveToFirst())
        {
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                // add contact object to our contact list
                contactList.add(contact);


            } while (cursor.moveToNext());
        }

        return contactList;

    }
}
