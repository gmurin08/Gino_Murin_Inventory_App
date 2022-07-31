package com.zybooks.gino_murin_inventory_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatDialogFragment;

public class EditDialog extends AppCompatDialogFragment {
    private NumberPicker numPicker;

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_inventory, null);

        //Setup for dialog view
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("Enter New Quantity")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        //Setup for number picker min/max and default
        numPicker = view.findViewById(R.id.num_picker);
        numPicker.setMaxValue(0);
        numPicker.setMaxValue(500);
        //Dummy value
        //TODO:Pull data from grid_view for default value
        numPicker.setValue(5);

        //TODO:Return value from numPicker and assign to grid element
        return builder.create();
    }
}
