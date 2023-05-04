package com.example.nevz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nevz.data.ProductsDBHelper;
import com.example.nevz.helpers.EditTextHelper;
import com.example.nevz.helpers.MonthMapHelper;
import com.example.nevz.utils.UtilProductDB;

import java.io.File;
import java.util.Map;

public class SalaryActivity extends AppCompatActivity {
    EditTextHelper helper = new EditTextHelper();
    TextView prodText, errorText;
    Cursor cursor;
    EditText yearText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);

        Spinner spin = findViewById(R.id.spinner_month);
        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.month,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin.setAdapter(adapter);


        yearText = findViewById(R.id.edit_text_year);
        helper.year(yearText);
        yearText.setSelectAllOnFocus(true);

    }


    @SuppressLint({"NonConstantResourceId", "SetTextI18n", "DefaultLocale"})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_menu_button2:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
            case R.id.createReceipt:
                String year = (yearText.getText().toString());
                if (new File(UtilProductDB.DATABASE_PATH + year).exists()) {
                    ProductsDBHelper productsDBHelper = new ProductsDBHelper(this);
                    double sum = 0;
                    SQLiteDatabase db = getBaseContext().openOrCreateDatabase(year, MODE_PRIVATE, null);
                    Spinner spinner = findViewById(R.id.spinner_month);
                    String selected = spinner.getSelectedItem().toString();
                    Map<String, String> monthMap = MonthMapHelper.monthMap();
                    cursor = db.rawQuery("SELECT* FROM " + monthMap.get(selected) + ";", null);
                    while (cursor.moveToNext()) {
                        sum += cursor.getInt(3) / 1000.0 * cursor.getInt(2)
                                * 29.03 * productsDBHelper.getCoefficient(cursor.getString(1), cursor.getInt(2));
                    }
                    prodText = findViewById(R.id.text_count_prod);
                    String prodPay = String.format("%.2f", sum);
                    prodText.setText(prodPay);
                    prodText = findViewById(R.id.text_count_cofficent);
                    String coffPay = String.format("%.2f", sum * 0.2);
                    prodText.setText(coffPay);
                    prodText = findViewById(R.id.count_tax);
                    String pit = String.format("%.2f", (sum - sum * 0.2) * 0.13);
                    prodText.setText(pit);
                    prodText= findViewById(R.id.count_sum);
                    String sumText = String.format("%.2f", sum + (sum * 0.2)-((sum - sum * 0.2) * 0.13));
                    prodText.setText(sumText);
                    cursor.close();
                    db.close();
                } else {
                    errorText = findViewById(R.id.error_text);
                    errorText.setText("Ошибка");
                }
                break;
        }
    }
}