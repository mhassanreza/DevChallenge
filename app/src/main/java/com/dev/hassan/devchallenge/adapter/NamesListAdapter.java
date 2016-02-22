package com.dev.hassan.devchallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.hassan.devchallenge.R;

import java.util.ArrayList;

import com.dev.hassan.devchallenge.holders.CustomViewHolder;
import com.dev.hassan.devchallenge.model.NameModel;

public class NamesListAdapter extends RecyclerView.Adapter<CustomViewHolder> {


    private ArrayList<NameModel> mArrFeedItemList;
    private Context mContext;

    public NamesListAdapter(Context context, ArrayList<NameModel> mArrFeedItemlist) {
        this.mArrFeedItemList = mArrFeedItemlist;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);
        CustomViewHolder mh = new CustomViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder feedListRowHolder, int i) {
        NameModel feedItem = mArrFeedItemList.get(i);
        feedListRowHolder.mTvFirstName.setText(getFirstLetterCapital(feedItem.getFirstName()));
        feedListRowHolder.mTvLastName.setText(getFirstLetterCapital(feedItem.getLastName()) + ", ");
    }

    private String getFirstLetterCapital(String value) {
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }

    @Override
    public int getItemCount() {
        return (null != mArrFeedItemList ? mArrFeedItemList.size() : 0);
    }
}
