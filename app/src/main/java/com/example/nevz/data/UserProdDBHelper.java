package com.example.nevz.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.nevz.model.ProductUser;
import com.example.nevz.utils.UtilProductUserDB;

import java.util.ArrayList;
import java.util.List;


public class UserProdDBHelper extends SQLiteOpenHelper {
    /**
     * Crate DB
     *
     * @param context lib
     */
    public UserProdDBHelper(Context context) {
        super(context, UtilProductUserDB.DATABASE_NAME, null, UtilProductUserDB.DATABASE_VERSION);
    }

    /**
     * Create table in DB
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCT_USER_TABLE = "CREATE TABLE " + UtilProductUserDB.TABLE_NAME + " ("
                + UtilProductUserDB.DATE_KEY + " INTEGER, "
                + UtilProductUserDB.PRODUCT_KEY + " TEXT, "
                + UtilProductUserDB.MACHINE_KEY + " INTEGER, "
                + UtilProductUserDB.COUNT_KEY + " INTEGER" + " )";

        sqLiteDatabase.execSQL(CREATE_PRODUCT_USER_TABLE);
    }

    /**
     * Upgrade DB
     *
     * @param sqLiteDatabase The database.
     * @param i              The old database version.
     * @param i1             The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilProductUserDB.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /**
     * add new product to DB
     *
     * @param product {day, drawingNumber, machine(coefficient), count}
     */
    public void addProduct(ProductUser product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UtilProductUserDB.DATE_KEY, product.getDay());
        contentValues.put(UtilProductUserDB.PRODUCT_KEY, product.getProduct());
        contentValues.put(UtilProductUserDB.MACHINE_KEY, product.getMachine());
        contentValues.put(UtilProductUserDB.COUNT_KEY, product.getCount());

        db.insert(UtilProductUserDB.TABLE_NAME, null, contentValues);
        db.close();
    }

    /**
     * get product from DB
     *
     * @param day
     * @param month
     * @param year
     * @return Product User by date
     */
    public ProductUser getProduct(int day, String month, int year) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(month + String.valueOf(year),
                new String[]{UtilProductUserDB.DATE_KEY, UtilProductUserDB.PRODUCT_KEY,
                        UtilProductUserDB.MACHINE_KEY, UtilProductUserDB.COUNT_KEY},
                UtilProductUserDB.DATE_KEY + "=?", new String[]{String.valueOf(day)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return new ProductUser(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));
    }
}
