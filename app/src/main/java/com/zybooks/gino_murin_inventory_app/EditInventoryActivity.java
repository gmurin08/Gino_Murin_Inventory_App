package com.zybooks.gino_murin_inventory_app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class EditInventoryActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private List<Inventory> inventory;
    private Inventory item;
    private InventoryDatabase db;
    private EditText mName;
    private EditText mQuantity;
    private EditText mDescription;
    private Button mUpdate;
    private Button mDelete;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_inventory);
        mName = findViewById(R.id.nameEditText);
        mQuantity = findViewById(R.id.qtyEditText);
        mDescription = findViewById(R.id.descEditText);
        mUpdate = findViewById(R.id.btn_save);
        mDelete = findViewById(R.id.btn_delete);

        int id = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id = extras.getInt("id");
            System.out.println("Got extra from view # " + id );
        }

        db = InventoryDatabase.getInstance(getApplicationContext());
        inventory = db.getInventory();
        item = inventory.get(id);

        mName.setText(item.getName());
        mQuantity.setText(String.valueOf(item.getQuantity()));
        mDescription.setText(item.getDescription());
    }

    protected void sendSMS(String msg) {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+14128600864",
                null, msg, null, null);

    }

    public void makeChanges(View view){
        item.setName(mName.getText().toString());
        item.setQuantity(Integer.parseInt(mQuantity.getText().toString()));
        item.setDescription(mDescription.getText().toString());
        db.updateInventory(item);
        inventory = db.getInventory();

        //Display toast success notification
        Context context = getApplicationContext();
        CharSequence text = "Successfully Updated Inventory!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        Intent intent = new Intent(this, DataGridActivity.class);
        startActivity(intent);
    }

    public void deleteView(View view){
        db.deleteInventory(item.getId());
        inventory = db.getInventory();

        //Display toast success notification
        Context context = getApplicationContext();
        CharSequence text = "Item '" + item.getName() + "' Successfully Deleted.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        sendSMS("Item was deleted");
        Intent intent = new Intent(this, DataGridActivity.class);
        startActivity(intent);
    }
}
