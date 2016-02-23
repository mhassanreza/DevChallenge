package com.dev.hassan.devchallenge.utils;

/**
 * Created by Hassan on 2/22/2016.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.dev.hassan.devchallenge.R;
import com.dev.hassan.devchallenge.interfaces.YesNoListener;
import com.dev.hassan.devchallenge.model.NameModel;

public class MyDialogFragment extends DialogFragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!(activity instanceof YesNoListener)) {
            throw new ClassCastException(activity.toString() + " must implement YesNoListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AppCompatAlertDialogStyle);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setTitle(AppConstants.DIALOG_TITLE);
        View dialogView = inflater.inflate(R.layout.fragment_edit_name, null);
        final EditText mEtFirstName = (EditText) dialogView.findViewById(R.id.et_first_name);
        final EditText mEtLastName = (EditText) dialogView.findViewById(R.id.et_last_name);
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.setPositiveButton("ADD",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do nothing here because we override this button later to change the close behaviour.
                        //However, we still need this because on older versions of Android unless we
                        //pass a handler the button doesn't get instantiated
                    }
                });
        builder.setNegativeButton("CANCEL", null);
        final AlertDialog dialog = builder.create();
        dialog.show();
//Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEtFirstName.getEditableText().toString().trim().length() <= 0) {
                    showError(mEtFirstName, R.string.error_first_name);
                } else if (mEtLastName.getEditableText().toString().trim().length() <= 0) {
                    showError(mEtLastName, R.string.error_last_name);
                } else {
                    NameModel fi = new NameModel();
                    fi.setFirstName(mEtFirstName.getEditableText().toString());
                    fi.setLastName(mEtLastName.getEditableText().toString());
                    ((YesNoListener) getActivity()).onYes(fi);
                    dialog.dismiss();
                }
            }
        });

        return dialog;

    }

    protected void showError(EditText et, int msg) {
        et.setError(getResources().getString(msg));
        et.requestFocus();
    }
}