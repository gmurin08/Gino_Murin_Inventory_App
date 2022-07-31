package com.zybooks.gino_murin_inventory_app;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{

    private EditText mUsernameField;
    private EditText mPasswordField;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameField = findViewById(R.id.usernameEditText);
        mPasswordField = findViewById(R.id.passwordEditText);
    }

    //Dummy click handler for the sake of navigating UI
    //TODO: Implement logic to validate username & password
    public void onLoginClick(View view){
        Intent intent = new Intent(this, DataGridActivity.class);
        startActivity(intent);
    }

    public void onSignUpClick(View view){
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();

    }

}
