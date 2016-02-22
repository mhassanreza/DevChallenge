package com.dev.hassan.devchallenge.utils;

/**
 * Created by Hassan on 2/22/2016.
 */

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.hassan.devchallenge.R;

public class AlertDFragment extends DialogFragment {

    private EditText mEtFirstName;
    private EditText mEtLastName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        return new AlertDialog.Builder(getActivity())
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_edit_name, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        initComponents(dialogView);

        // Positive button
        return dialogBuilder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (mEtFirstName.getText().toString().trim().length() != 0 && mEtLastName.getText().toString().trim().length() != 0) {
                } else {
                    Toast.makeText(getActivity(), "Enter First and Last Name both.", Toast.LENGTH_SHORT).show();
                }
            }
        })
                // Negative Button
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create();
    }

    private void initComponents(View view) {
        mEtFirstName = (EditText) view.findViewById(R.id.et_first_name);
        mEtLastName = (EditText) view.findViewById(R.id.et_last_name);
    }
}