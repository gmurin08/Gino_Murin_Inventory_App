package com.zybooks.gino_murin_inventory_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddInventoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
    }

    public void onAddButtonClick(View view){
        //Navigate back to data grid
        Intent intent = new Intent(this, DataGridActivity.class);
        startActivity(intent);

        //Display toast success notification
        Context context = getApplicationContext();
        CharSequence text = "Successfully Added to Inventory.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

}
