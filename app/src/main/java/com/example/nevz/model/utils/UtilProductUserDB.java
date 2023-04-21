package com.example.nevz.model.utils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class UtilProductUserDB {
    static Calendar calendar = new GregorianCalendar();
    static DateFormat df = new SimpleDateFormat("MMMM-yyy ", Locale.ENGLISH);
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = df.format(calendar.getTime().toString());
    public static final String DATABASE_NAME = "ProductUserDB";
    public static final String DATE_KEY = "Day";
    public static final String PRODUCT_KEY = "Product";
    public static final String MACHINE_KEY = "Machine";
    public static final String COUNT_KEY = "Count";
}
