package com.example.nevz.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.nevz.utils.UtilProductDB;
import com.example.nevz.utils.UtilUserDB;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProductsDBHelper extends SQLiteAssetHelper {

    Context context;
    private SQLiteDatabase myDataBase;

    /**
     * Create DB
     *
     * @param context lib
     */
    public ProductsDBHelper(Context context) {
        super(context, UtilProductDB.DATABASE_NAME, null, UtilProductDB.DATABASE_VERSION);
        this.context = context;
        myDataBase = this.getWritableDatabase();
        setForcedUpgrade();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    /**
     * get all drawing number in ArrayList
     *
     * @return List<String> drawings
     */
    public List<String> getAllDrawingNumbers() {
        Set<String> set = new HashSet<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] tables = UtilProductDB.TABLES;
        for (String table : tables) {
            Cursor cursor = db.query(table, new String[]{UtilProductDB.COLUMN_DRAWING},
                    null, null, null, null, null);
            cursor.moveToFirst();
            set.add(cursor.getString(0));
            while (cursor.moveToNext()) {
                set.add(cursor.getString(0));
            }
        }
        db.close();
        return new ArrayList<>(set);
    }

    /**
     * take all drawing with machine
     *
     * @return map
     */
    public Map<String, Integer> getAllDrawType() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] tables = UtilProductDB.TABLES;
        for (String table : tables) {
            Cursor cursor = db.query(table, new String[]{UtilProductDB.COLUMN_DRAWING, UtilProductDB.COLUMN_PRESS_LATHE},
                    null, null, null, null, null);
            cursor.moveToFirst();
            map.putIfAbsent(cursor.getString(0), Integer.parseInt(cursor.getString(1)));
            while (cursor.moveToNext()) {
                if (map.containsKey(cursor.getString(0))) {
                    map.replace(cursor.getString(0), 2);
                } else {
                    map.put(cursor.getString(0), Integer.parseInt(cursor.getString(1)));
                }
            }
        }
        db.close();
        return map;
    }

    public double getCoefficient(String drawing, int machine) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] tables = UtilProductDB.TABLES;
        for (String table : tables) {
            Cursor cursor = db.query(table,
                    new String[]{UtilProductDB.COLUMN_DRAWING, UtilProductDB.COLUMN_COFF, UtilProductDB.COLUMN_PRESS_LATHE},
                    null, null, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.getString(0).equals(drawing) &&
                        Integer.parseInt(cursor.getString(2)) == machine) {
                    return Double.parseDouble(cursor.getString(1));
                }
            }
        }
        return 0;
    }
}