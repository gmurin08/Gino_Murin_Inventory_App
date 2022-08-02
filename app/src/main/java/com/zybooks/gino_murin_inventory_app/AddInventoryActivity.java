package com.zybooks.gino_murin_inventory_app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddInventoryActivity extends AppCompatActivity {

    private InventoryDatabase db;
    private EditText mItemName;
    private EditText mItemQuantity;
    private EditText mItemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_inventory);
        db = InventoryDatabase.getInstance(getApplicationContext());
        mItemName = findViewById(R.id.nameEditText);
        mItemQuantity = findViewById(R.id.qtyEditText);
        mItemDescription = findViewById(R.id.descEditText);
    }

    public void onAddButtonClick(View view){
        //Pull data from view to create inventory object for db
        Inventory item = new Inventory(mItemName.getText().toString(),
                mItemDescription.getText().toString(),
                Integer.parseInt(mItemQuantity.getText().toString()));
        db.addInventory(item);

        //Navigate back to data grid
        Intent intent = new Intent(this, DataGridActivity.class);
        startActivity(intent);

        //Display toast success notification
        Context context = getApplicationContext();
        CharSequence text = "Successfully Added "+
                item.getName()+ " to Inventory.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
