package com.zybooks.gino_murin_inventory_app;

import android.Manifest;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DataGridActivity extends AppCompatActivity {
    FloatingActionButton mAddButton;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_grid);

        //Code to request ability send SMS from within app
        final int REQUEST_WRITE_CODE= 0;
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        if(permission != 0){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.SEND_SMS}, REQUEST_WRITE_CODE);
        }
        mAddButton=findViewById(R.id.add_button);

    }


    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.edit_inventory_menu, popup.getMenu());
        popup.show();
    }

    public void onAddClick(View view){
        Intent intent = new Intent(this, AddInventoryActivity.class);
        startActivity(intent);
    }

    public void openEditDialog(MenuItem item){
        EditDialog dialog = new EditDialog();
        dialog.show(getSupportFragmentManager(),"edit dialog");
    }

    public void openDeleteDialog(MenuItem item){
        ConfirmDeleteDialog dialog = new ConfirmDeleteDialog();
        dialog.show(getSupportFragmentManager(),"delete dialog");
    }
}
