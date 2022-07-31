package com.zybooks.gino_murin_inventory_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

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

}
