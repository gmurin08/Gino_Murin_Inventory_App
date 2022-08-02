package com.zybooks.gino_murin_inventory_app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class LoginActivity extends AppCompatActivity{

    private InventoryDatabase db;
    private EditText mUsernameField;
    private EditText mPasswordField;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = InventoryDatabase.getInstance(getApplicationContext());
        mUsernameField = findViewById(R.id.usernameEditText);
        mPasswordField = findViewById(R.id.passwordEditText);
    }


    public void onLoginClick(View view){
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();
        List<User> users = db.getUsers();
        User login = new User(username, password);
        for(User account: users){

            if (login.getUsername().equals(account.getUsername()) &&
                    login.getPassword().equals(account.getPassword())){
                //Display toast success notification
                Context context = getApplicationContext();
                CharSequence text = "Welcome " + login.getUsername() + "!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Navigate to grid display
                Intent intent = new Intent(this, DataGridActivity.class);
                startActivity(intent);
            }
        }
        //Display toast success notification
        Context context = getApplicationContext();
        CharSequence text = "Enter a valid username or password.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onSignUpClick(View view){
        String username = mUsernameField.getText().toString();
        String password = mPasswordField.getText().toString();
        User user = new User(username, password);
        List<User> users = db.getUsers();


        if(users.contains(user.getUsername())){
            mUsernameField.setError("Username taken");

        }
        if(TextUtils.isEmpty(username)){
            mUsernameField.setError("Enter a username");
            return;
        }
        if(TextUtils.isEmpty(password)
        ||password.length() < 8){
            mPasswordField.setError("Enter a valid password: \n\n8 characters minimum \n" +
                    "1 or more uppercase letters \n1 or more lowercase letters \n" +
                    "1 or more special characters (&,@,#,!,%,,*)");
            return;
        }

        //Display toast success notification
        Context context = getApplicationContext();
        CharSequence text = "Account Created Successfully!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        db.addUser(user);

    }

    public void dummyClick(View view){
        Intent intent = new Intent(this, DataGridActivity.class);
        startActivity(intent);
    }


}
