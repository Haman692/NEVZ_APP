package com.example.nevz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.nevz.data.DataBaseHandler;
import com.example.nevz.model.User;

/**
 *Registration activity
 */
public class MainActivity extends AppCompatActivity {

    EditText userName, userSurname, userID;
    DataBaseHandler dataBaseHandler = new DataBaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (dataBaseHandler.empty()){
            Intent intent = new Intent(this,MainMenuActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    /**
     * Registration
     * @param view lib
     */
    public void Registration(View view) {

        userName = findViewById(R.id.editTextTextPersonName);
        userSurname = findViewById(R.id.editTextTextPersonSurname);
        userID = findViewById(R.id.editTextTextPersonID);

        dataBaseHandler.addUser(new User(Integer.parseInt(
                userID.getText().toString()),
                userName.getText().toString(),
                userSurname.getText().toString()));
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}