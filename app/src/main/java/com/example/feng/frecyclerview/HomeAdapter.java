package com.example.feng.frecyclerview;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.example.frecyclerviewlibrary.BaseAdapter;
import com.example.frecyclerviewlibrary.BaseViewHolder;

import java.util.List;

import butterknife.ButterKnife;


public class HomeAdapter extends BaseAdapter<String> {

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public HomeAdapter(@Nullable List<String> data) {
        super(data);
    }

    @Override
    public BaseViewHolder<String> initViewHolder(ViewGroup parent, int viewType) {
        return new HomeViewHolder(parent, viewType);
    }

    class HomeViewHolder extends BaseViewHolder<String> {


        public HomeViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.view_text);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(final String data, final int position) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick!=null){
                        onItemClick.onItemClick(data,position);
                    }
                }
            });
        }
    }

    interface OnItemClick {
        void onItemClick(String data, int position);
    }

}
