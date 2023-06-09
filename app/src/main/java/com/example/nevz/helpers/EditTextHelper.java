package com.example.nevz.helpers;

import android.annotation.SuppressLint;
import android.widget.EditText;

import com.example.nevz.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class EditTextHelper {
    /**
     * refilling Data(work, but no finish)
     *
     * @param editText full data
     */
    @SuppressLint("SetTextI18n")
    public static void refillingDataText(EditText editText) {
        Calendar calendar = new GregorianCalendar();
        if (!editText.getText().toString().contains(".")
                && Integer.parseInt(editText.getText().toString()) > 0
                && Integer.parseInt(editText.getText().toString()) < 31) {
            DateFormat df = new SimpleDateFormat("MM.yyyy", Locale.ENGLISH);
            editText.setText(editText.getText().toString() + "." + df.format(calendar.getTime()));
        } else {
            DateFormat df = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
            editText.setText(df.format(calendar.getTime()));
        }
    }

    /**
     * add yer now
     *
     * @param yearText input field
     */
    public void year(EditText yearText) {
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("yyyy", Locale.ENGLISH);
        yearText.setText(df.format(calendar.getTime()));
    }
}