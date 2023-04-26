package com.example.nevz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.nevz.model.User;
import com.example.nevz.utils.UtilUserDB;


public class DataBaseHandler extends SQLiteOpenHelper {
    public DataBaseHandler(Context context) {
        super(context, UtilUserDB.DATABASE_NAME, null, UtilUserDB.DATABASE_VERSION);
    }

    /**
     * Create table
     * @param sqLiteDatabase lib
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " + UtilUserDB.TABLE_NAME + " ("
                + UtilUserDB.KEY_ID + " INTEGER PRIMARY KEY, "
                + UtilUserDB.KEY_NAME + " TEXT, "
                + UtilUserDB.KEY_SURNAME + " TEXT" + " )";

        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
    }

    /**
     *
     * @param sqLiteDatabase lib
     * @param i version
     * @param i1 new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilUserDB.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * Add new user to DB
     * @param user New user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UtilUserDB.KEY_ID, user.getId());
        contentValues.put(UtilUserDB.KEY_NAME, user.getName());
        contentValues.put(UtilUserDB.KEY_SURNAME, user.getSurName());

        db.insert(UtilUserDB.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * User information from the database
     * @param id userID
     * @return User information
     */
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UtilUserDB.TABLE_NAME, new String[]{UtilUserDB.KEY_ID, UtilUserDB.KEY_NAME, UtilUserDB.KEY_SURNAME},
                UtilUserDB.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
    }

    /**
     * User name from the database
     * @return User name
     */
    public String getUserName() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UtilUserDB.TABLE_NAME, new String[]{UtilUserDB.KEY_NAME},
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor.getString(0);
    }

    /**
     * User ID from the database
     * @return UserID
     */
    public int getUserID() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UtilUserDB.TABLE_NAME, new String[]{UtilUserDB.KEY_ID},
                null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return Integer.parseInt(cursor.getString(0));
    }

    /**
     * Check for emptiness
     * @return True or False
     */
    public boolean empty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(UtilUserDB.TABLE_NAME, new String[]{UtilUserDB.KEY_ID, UtilUserDB.KEY_NAME, UtilUserDB.KEY_SURNAME},
                null, null, null, null, null);
        return cursor.getCount() > 0;
    }
}