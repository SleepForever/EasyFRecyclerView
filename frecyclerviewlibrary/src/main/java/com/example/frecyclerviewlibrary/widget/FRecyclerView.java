package com.example.frecyclerviewlibrary.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

import com.example.frecyclerviewlibrary.BaseAdapter;
import com.example.frecyclerviewlibrary.contract.OnBaseListener;
import com.example.frecyclerviewlibrary.loadmore.LoadMoreView;

public class FRecyclerView extends RecyclerView {

    OnBaseListener.OnLoadMoreListener onLoadMoreListener;

    public FRecyclerView(Context context) {
        this(context, null);
    }

    public FRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {

    }

    public void setOnLoadMoreListener(OnBaseListener.OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount() && null != onLoadMoreListener) {
                try{
                    if (((BaseAdapter)getAdapter()).getLoadMoreView().getLoadMoreStatus()== LoadMoreView.STATUS_LOADING){
                        return;
                    }
                    onLoadMoreListener.onLoadMore();
                }catch(Exception e){
                    Log.e("FRecyclerView.java","onScrollStateChanged(行数：59)-->>[state]"+e);
                }

            }
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


}