package com.dev.hassan.devchallenge.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dev.hassan.devchallenge.R;

/**
 * Created by Hassan on 2/22/2016.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {
    public final TextView mTvLastName;
    public final TextView mTvFirstName;

    public CustomViewHolder(View view) {
        super(view);
        this.mTvFirstName = (TextView) view.findViewById(R.id.first_name);
        this.mTvLastName = (TextView) view.findViewById(R.id.last_name);
    }
}
