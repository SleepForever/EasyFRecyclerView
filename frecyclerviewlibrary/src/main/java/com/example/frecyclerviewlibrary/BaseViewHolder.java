package com.example.frecyclerviewlibrary;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frecyclerviewlibrary.util.LogUtil;

import java.lang.reflect.Field;

public abstract class BaseViewHolder<M> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
    }

    public abstract void setData(M data, int position);

    protected Context getContext() {
        return itemView.getContext();
    }

    protected int getDataPosition() {
        try {
            RecyclerView.Adapter adapter = getOwnerAdapter();
            if (adapter != null && adapter instanceof BaseAdapter) {
                return getAdapterPosition() - ((BaseAdapter) adapter).getHeaderLayoutCount();
            }
        } catch (Exception e) {
            LogUtil.e("BaseViewHolder.java", "getDataPosition(行数：50)-->>[]" + e);
        }

        return getAdapterPosition();
    }

    @Nullable
    protected <T extends RecyclerView.Adapter> T getOwnerAdapter() {
        RecyclerView recyclerView = getOwnerRecyclerView();
        return recyclerView == null ? null : (T) recyclerView.getAdapter();
    }

    @Nullable
    protected RecyclerView getOwnerRecyclerView() {
        try {
            Field field = RecyclerView.ViewHolder.class.getDeclaredField("mOwnerRecyclerView");
            field.setAccessible(true);
            return (RecyclerView) field.get(this);
        } catch (NoSuchFieldException e) {
        } catch (IllegalAccessException e) {
        }
        return null;
    }

}