package com.example.nevz.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class UtilProductUserDB {
    static Month month = Month.from(LocalDate.now());
    static Year year = Year.from(LocalDate.now());
    public static final int DATABASE_VERSION = 1;
    public static String TABLE_NAME = month.name();
    public static final String DATABASE_NAME = year.toString();
    public static final String DATE_KEY = "Day";
    public static final String PRODUCT_KEY = "Product";
    public static final String MACHINE_KEY = "Machine";
    public static final String COUNT_KEY = "Count";
}