package com.example.nevz.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.nevz.model.Product;
import com.example.nevz.model.utils.UtilProductUserDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class ProductDBHelper extends SQLiteOpenHelper {
    public ProductDBHelper(Context context) {
        super(context, UtilProductUserDB.DATABASE_NAME, null, UtilProductUserDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCT_USER_TABLE = "CREATE TABLE" + UtilProductUserDB.TABLE_NAME + " ("
                + UtilProductUserDB.DATE_KEY + " INTEGER, "
                + UtilProductUserDB.PRODUCT_KEY + " TEXT, "
                + UtilProductUserDB.MACHINE_KEY + " INTEGER, "
                + UtilProductUserDB.COUNT_KEY + " INTEGER" + " )";

        sqLiteDatabase.execSQL(CREATE_PRODUCT_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UtilProductUserDB.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UtilProductUserDB.DATE_KEY, product.getDay());
        contentValues.put(UtilProductUserDB.PRODUCT_KEY, product.getProduct());
        contentValues.put(UtilProductUserDB.MACHINE_KEY, product.getMachine());
        contentValues.put(UtilProductUserDB.COUNT_KEY, product.getCount());

        db.insert(UtilProductUserDB.TABLE_NAME, null, contentValues);
        db.close();
    }

    public Product getProduct(int day, String month, int year) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(month + String.valueOf(year),
                new String[] {UtilProductUserDB.DATE_KEY,UtilProductUserDB.PRODUCT_KEY,
                        UtilProductUserDB.MACHINE_KEY,UtilProductUserDB.COUNT_KEY},
                UtilProductUserDB.DATE_KEY + "=?", new String[]{String.valueOf(day)},
                null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return new Product(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)));
    }
    public List<Product> getAllToMonth (String moth, int year){
        List<Product> productsList = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            productsList.add(getProduct(i,moth,year));
        }
        return productsList;
    }
}
