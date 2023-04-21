package com.example.nevz;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View.OnClickListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nevz.data.DataBaseHandler;

public class MainMenuActivity extends AppCompatActivity {
    DataBaseHandler db = new DataBaseHandler(this);
    TextView header1;
    TextView header2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        header1 = findViewById(R.id.header1);
    }

    @SuppressLint("SetTextI18n")
    public void onResume() {
        super.onResume();
        header1.setText("Здравствуйте, " + db.getUserName() + '\n' + "(ID)" + db.getUserID());
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.production_button:
                startActivity(new Intent(this, ProductionDayActivity.class));
                break;
            case R.id.salary_button:
                startActivity(new Intent(this, SalaryActivity.class));
                break;
            case R.id.tool_button:
                startActivity(new Intent(this, ToolsActivity.class));
                break;
            case R.id.calendar_button:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case R.id.recommendations_button:
                startActivity(new Intent(this, RecommendationsActivity.class));
                break;
        }
    }
}
