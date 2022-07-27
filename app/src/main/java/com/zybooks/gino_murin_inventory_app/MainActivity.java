/*
Gino Murin - CS360 - 7/26/22
Module 5 Assignment

The following changes have been made to our previously created app template

-Implemented a TextWatcher to listen for and handle changes to our edit text field.
-Added sayHello() method that outputs a custom greeting on button click if our
 EditText view is not null.
-Added strings to facilitate this functionality to res/values/strings.xml

 */

package com.zybooks.gino_murin_inventory_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Declaring view objects
    private TextView mGreetingTextField;
    private EditText mNameEditText;
    private Button mGreetingButton;

    //Declaring TextWatcher object to dynamically enable/disable button on input
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Enable button when text is entered
            mGreetingButton.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String name = mNameEditText.getText().toString();
            //IF name field is empty
            if (TextUtils.isEmpty(name)) {
                //Set default greeting and disable button
                mGreetingTextField.setText(getString(R.string.default_greeting));
                mGreetingButton.setEnabled(false);
            }else{
                //else button stays enabled
                mGreetingButton.setEnabled(true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register UI elements to their respective views
        mGreetingTextField = findViewById(R.id.textGreeting);
        mNameEditText = findViewById(R.id.nameText);
        mGreetingButton = findViewById(R.id.buttonSayHello);

        //Attach a text change listener to nameText view
        mNameEditText.addTextChangedListener(textWatcher);
    }

    //Outputs a custom greeting if nameText is not null
    public void sayHello(View view){
        //Get string data from nameText
        String name = mNameEditText.getText().toString();

        //If name field is NOT empty
        if (!TextUtils.isEmpty(name)) {
            //fetch template greeting from res/values/strings.xml and output to view
            mGreetingTextField.setText(getString(R.string.name_greeting, name));
        }

    }
}