package com.example.nevz;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


import com.example.nevz.helpers.EditTextHelper;
import com.example.nevz.data.ProductsDBHelper;
import com.example.nevz.data.UserProdDBHelper;
import com.example.nevz.helpers.ValidityState;
import com.example.nevz.model.ProductUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class ProductionDayActivity extends AppCompatActivity {

    EditText dataText, countText;
    Calendar calendar = new GregorianCalendar();
    int type;
    AutoCompleteTextView autoCompleteTextView;
    UserProdDBHelper userProd = new UserProdDBHelper(this);
    ProductsDBHelper myDBHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_production_day);

        DateFormat df = new SimpleDateFormat("d.MM.yyyy", Locale.ENGLISH);
        EditText dateText = findViewById(R.id.editTextDate);
        dateText.setText(df.format(calendar.getTime()));
        dateText.setSelectAllOnFocus(true);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.drawingNumber);
        myDBHelper = new ProductsDBHelper(this);
        List<String> drawings = myDBHelper.getAllDrawingNumbers();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawings);
        autoCompleteTextView.setAdapter(adapter);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.clearCheck();
        myDBHelper.close();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.press:
                        type = 7;
                        break;
                    case R.id.lathe:
                        type = 6;
                        break;
                }
            }
        });
    }
    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editTextDate:
                EditText dateText = findViewById(R.id.editTextDate);
                EditTextHelper.refillingDataText(dateText);
                break;
            case R.id.main_menu_button:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
            case R.id.task_add_button:
                startActivity(new Intent(this, ProductionTaskActivity.class));
                break;
            case R.id.button:
                dataText = findViewById(R.id.editTextDate);
                countText = findViewById(R.id.editTextCount);
                String[] dataStr = dataText.getText().toString().split("\\.");
                List<String> drawings = myDBHelper.getAllDrawingNumbers();
                autoCompleteTextView = findViewById(R.id.drawingNumber);
                TextView errorText = (TextView) findViewById(R.id.errorMessage);
                if (ValidityState.valid(dataStr,
                        autoCompleteTextView,
                        type,
                        countText,
                        drawings)) {
                    userProd.addProduct(new ProductUser(Integer.parseInt(dataStr[0]),
                            autoCompleteTextView.getText().toString(),
                            type,
                            Integer.parseInt(countText.getText().toString())));
                    errorText.setText("Данные добавлены");
                }else {
                    errorText.setText("Ошибка в заполнении");
                }
                break;
        }
    }
}