package com.example.meetthemembers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class exitDialog extends AppCompatDialogFragment {
    private exitDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("You sure about this?")
            .setMessage("Are you 100% sure that you want to exit the game?")
            .setNegativeButton("Nah, imma keep playing.", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onNoClicked();
                }
            })
                .setPositiveButton("Aight imma head out.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onYesClicked();
                    }
                });
        return builder.create();
    }

    public interface exitDialogListener{
        void onYesClicked();
        void onNoClicked();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            listener = (exitDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement exitDialogListener.");
        }
    }
}
