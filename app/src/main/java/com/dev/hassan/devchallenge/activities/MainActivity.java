package com.dev.hassan.devchallenge.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.dev.hassan.devchallenge.R;
import com.dev.hassan.devchallenge.interfaces.YesNoListener;
import com.dev.hassan.devchallenge.adapter.NamesListAdapter;
import com.dev.hassan.devchallenge.model.NameModel;
import com.dev.hassan.devchallenge.utils.AppConstants;
import com.dev.hassan.devchallenge.utils.AppKeys;
import com.dev.hassan.devchallenge.utils.MyDialogFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener, YesNoListener {

    private FloatingActionButton mFAB;
    private ArrayList<NameModel> mArrFeedsList;
    private RecyclerView mRecyclerView;
    private NamesListAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        if (savedInstanceState != null) {
            mArrFeedsList = new ArrayList<NameModel>();
            mArrFeedsList = (ArrayList<NameModel>) savedInstanceState.getSerializable(AppKeys.SAVED_ARRAY_KEY.getKey());
        }


        initUiComponents();
        initListeners(this);

    }

    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putSerializable(AppKeys.SAVED_ARRAY_KEY.getKey(), mArrFeedsList);
    }

    private void initUiComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mFAB = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        addListData();
    }

    private void addListData() {
        if (null == mArrFeedsList) {
            mArrFeedsList = new ArrayList<NameModel>();
        }
        if (mArrFeedsList.size() == 0) {
            String response = "";
            response = AppConstants.JSON_OF_NAMES;
            Gson gson = new Gson();
            if (!TextUtils.isEmpty(response)) {
                try {
                    Object json = new JSONTokener(response).nextValue();
                    if (json instanceof JSONObject) {
                        Log.d(mContext.getClass().getName(), "JsonObject");
                    } else if (json instanceof JSONArray) {
                        Log.d(mContext.getClass().getName(), "JsonArray");
                        Type listType = new TypeToken<ArrayList<NameModel>>() {
                        }.getType();
                        mArrFeedsList = (ArrayList<NameModel>) gson.fromJson(response, listType);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            setNewAdapter();
        } else {
            setNewAdapter();
        }

    }

    private void setNewAdapter() {
        mAdapter = new NamesListAdapter(MainActivity.this, mArrFeedsList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListeners(MainActivity activity) {
        mFAB.setOnClickListener(activity);
    }

    protected void showError(EditText et, int msg) {
        et.setError(getResources().getString(msg));
        et.requestFocus();
    }

//    // this dialog works with portait mode.
//    public void showInputDialogCustomInvalidation() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle);
//        LayoutInflater inflater = this.getLayoutInflater();
//        builder.setTitle(AppConstants.DIALOG_TITLE);
//        View dialogView = inflater.inflate(R.layout.fragment_edit_name, null);
//        final EditText mEtFirstName = (EditText) dialogView.findViewById(R.id.et_first_name);
//        final EditText mEtLastName = (EditText) dialogView.findViewById(R.id.et_last_name);
//        builder.setView(dialogView);
//        builder.setCancelable(false);
//        builder.setPositiveButton("ADD",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //Do nothing here because we override this button later to change the close behaviour.
//                        //However, we still need this because on older versions of Android unless we
//                        //pass a handler the button doesn't get instantiated
//                    }
//                });
//        builder.setNegativeButton("CANCEL", null);
//        final AlertDialog dialog = builder.create();
//        dialog.show();
//
////Overriding the handler immediately after show is probably a better approach than OnShowListener as described below
//        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mEtFirstName.getEditableText().toString().trim().length() <= 0) {
//                    showError(mEtFirstName, R.string.error_first_name);
//                } else if (mEtLastName.getEditableText().toString().trim().length() <= 0) {
//                    showError(mEtLastName, R.string.error_last_name);
//                } else {
//                    dialog.dismiss();
//
//                    NameModel fi = new NameModel();
//                    fi.setFirstName(mEtFirstName.getEditableText().toString());
//                    fi.setLastName(mEtLastName.getEditableText().toString());
//
//                    mArrFeedsList.add(fi);
//                    mAdapter.notifyDataSetChanged();
//                }
//            }
//        });
//    }
//

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
//                showInputDialogCustomInvalidation();
                new MyDialogFragment().show(getSupportFragmentManager(), AppConstants.FragmentTags.TAG_DIALOG_FRAGMENT);
                break;
        }
    }


    @Override
    public void onYes(Object obj) {
        NameModel fi = (NameModel) obj;
        mArrFeedsList.add(fi);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNo() {
    }
}

