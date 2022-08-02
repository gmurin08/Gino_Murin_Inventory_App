package com.zybooks.gino_murin_inventory_app;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class DataGridActivity extends AppCompatActivity {

    private List<Inventory> inventory;
    private InventoryDatabase db;
    private ConstraintLayout layout;
    private GridLayout header;
    private FloatingActionButton mAddButton;

    public void createGrid(){
        final GridLayout view = (GridLayout) getLayoutInflater().inflate(R.layout.grid, null);
        int i = 0;
        System.out.println(inventory.get(0).getQuantity());
        for(Inventory item: inventory){
            System.out.println("Created item at index" + i +" with db id " + item.getId());
            GridLayout.LayoutParams nameParams = new GridLayout.LayoutParams();
            nameParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    70, getResources().getDisplayMetrics());
            nameParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            nameParams.leftMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    4, getResources().getDisplayMetrics());
            nameParams.columnSpec = GridLayout.spec(1);
            TextView newName = new TextView(getApplicationContext());
            newName.setText(item.getName());
            newName.setLayoutParams(nameParams);

            GridLayout.LayoutParams qtyParams = new GridLayout.LayoutParams();
            qtyParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    60, getResources().getDisplayMetrics());
            qtyParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            qtyParams.columnSpec = GridLayout.spec(2);
            TextView newQty = new TextView(getApplicationContext());
            newQty.setText(String.valueOf(item.getQuantity()));
            newQty.setLayoutParams(qtyParams);

            GridLayout.LayoutParams descParams = new GridLayout.LayoutParams();
            descParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    229, getResources().getDisplayMetrics());
            descParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            descParams.columnSpec = GridLayout.spec(3, 1);
            TextView newDesc = new TextView(getApplicationContext());
            newDesc.setText(item.getDescription());
            newDesc.setLayoutParams(descParams);

            GridLayout.LayoutParams btnParams = new GridLayout.LayoutParams();
            btnParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    50, getResources().getDisplayMetrics());
            btnParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            btnParams.columnSpec = GridLayout.spec(4);
            Button newBtn = new Button(getApplicationContext());
            newBtn.setText(R.string.overflow);
            newBtn.setBackgroundColor(0);
            newBtn.setLayoutParams(btnParams);
            newBtn.setOnClickListener(this::onEditClick);
            newBtn.setId(i);

            view.addView(newName);
            view.addView(newQty);
            view.addView(newDesc);
            view.addView(newBtn);
            i++;
        }
        layout.addView(view);
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_grid);
        mAddButton=findViewById(R.id.add_button);
        layout = findViewById(R.id.grid_container);
        header = findViewById(R.id.header_grid);

        //Load inventory data from db
        db = InventoryDatabase.getInstance(getApplicationContext());
        inventory = db.getInventory();

        //Request ability to send SMS for low inventory alerts
        final int REQUEST_WRITE_CODE= 0;
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        if(permission != 0){
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.SEND_SMS}, REQUEST_WRITE_CODE);
        }

        if(inventory.size() > 0) {
            createGrid();
        }else{
            new EmptyInventoryDialog().show(getSupportFragmentManager(), "empty dialog");
            mAddButton.startAnimation(AnimationUtils.
                    loadAnimation(this, R.anim.bounce));
        }
    }

    public void onEditClick(View view){
        Intent intent = new Intent(this, EditInventoryActivity.class);
        System.out.println("Called activity from view # " + view.getId());
        intent.putExtra("id", view.getId());
        startActivity(intent);
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.edit_inventory_menu, popup.getMenu());

        System.out.println("PopUp clicked from view id: "+ view.getId());
        popup.show();
    }

    public void onAddClick(View view){
        Intent intent = new Intent(this, AddInventoryActivity.class);
        startActivity(intent);
    }

    public void openEditDialog(MenuItem item){
        System.out.println("Opened menu from item with id " + item.getItemId());
        EditDialog dialog = new EditDialog();

        dialog.show(getSupportFragmentManager(),"edit dialog");
    }

    public void openDeleteDialog(MenuItem item){

        ConfirmDeleteDialog dialog = ConfirmDeleteDialog.newInstance(3);
        dialog.show(getSupportFragmentManager(),"delete dialog");
    }
}
