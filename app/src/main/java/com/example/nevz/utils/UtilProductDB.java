package com.example.nevz.utils;

import android.annotation.SuppressLint;

public class UtilProductDB {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Products.db";
    public static final String TABLE_ARMOR_PLATE = "armorePLate";
    public static final String TABLE_INSULATOR = "insulator";
    public static final String COLUMN_DRAWING = "drawingNumber";
    public static final String COLUMN_COFF = "coefficient";
    public static final String COLUMN_PRESS_LATHE = "pressOrLathe";

    public static final String[] TABLES =
            {UtilProductDB.TABLE_ARMOR_PLATE, UtilProductDB.TABLE_INSULATOR};

    @SuppressLint("SdCardPath")
    public static final String DATABASE_PATH = "/data/data/com.example.nevz/databases/";

}