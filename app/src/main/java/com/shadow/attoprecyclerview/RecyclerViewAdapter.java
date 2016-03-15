package com.shadow.attoprecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shadow on 2016/3/15.
 */
public class RecyclerViewAdapter extends RecyclerArrayAdapter<Session,RecyclerViewAdapter.RecyclerViewHolder>{


    public RecyclerViewAdapter(List<Session> objects) {
        super(objects);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(View itemView) {
            super(itemView);
        }
    }

}
