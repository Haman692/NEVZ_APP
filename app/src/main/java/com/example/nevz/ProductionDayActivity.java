package com.example.nevz;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nevz.data.EditTextHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ProductionDayActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_day);

        Calendar calendar = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
        EditText dateText = findViewById(R.id.editTextDate);
        dateText.setText(df.format(calendar.getTime()));
        dateText.setSelectAllOnFocus(true);
            EditTextHelper.refillingDataText(dateText);
    }


    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_menu_button:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
            case R.id.task_add_button:
                startActivity(new Intent(this, ProductionTaskActivity.class));
                break;
        }

    }
}