package com.zybooks.gino_murin_inventory_app;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;


public class ConfirmDeleteDialog extends DialogFragment {

    static ConfirmDeleteDialog newInstance(int num) {
        ConfirmDeleteDialog f = new ConfirmDeleteDialog();
        System.out.println("Got " + num + " from ConfirmDeleteDialog newInstance()");
        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int mNum = getArguments().getInt("num");
        System.out.println("Got " + mNum + " from ConfirmDeleteDialog onCreate()");
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_confirm_delete, null);

        //Setup for dialog view
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("Confirm")
                //TODO: Pass item name into view for template string
                .setMessage("\nAre you sure you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: Handle logic for dynamically removing item from list
                        System.out.println("You chose yes");
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

        return view;
    }
}
