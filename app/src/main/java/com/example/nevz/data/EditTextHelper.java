package com.example.nevz.data;

import android.annotation.SuppressLint;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class EditTextHelper {
    @SuppressLint("SetTextI18n")
    public static void refillingDataText(EditText editText) {
        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("MM.yyyy", Locale.ENGLISH);
        if (!editText.getText().toString().equals(".")
                && Integer.parseInt(editText.getText().toString()) > 0
                && Integer.parseInt(editText.getText().toString()) < 31) {
            editText.setText(editText.getText().toString() + "." + df.format(calendar.getTime()));
        }
    }
}
