package com.example.frecyclerviewlibrary;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.frecyclerviewlibrary.contract.OnBaseListener;
import com.example.frecyclerviewlibrary.contract.OnSwitchStateListener;
import com.example.frecyclerviewlibrary.decoration.DividerDecoration;
import com.example.frecyclerviewlibrary.decoration.SpaceDecoration;
import com.example.frecyclerviewlibrary.ptrlib.ptr.PtrDefaultHandler;
import com.example.frecyclerviewlibrary.ptrlib.ptr.PtrHandler;
import com.example.frecyclerviewlibrary.util.DensityUtil;
import com.example.frecyclerviewlibrary.widget.FPtrFrameLayout;
import com.example.frecyclerviewlibrary.widget.FRefreshLayout;

/**
 * Created by feng on 2017/4/25.
 */

public class FRefreshManager {

    Context context;
    FRefreshLayout refreshLayout;
    BaseAdapter adapter;

    RecyclerView.LayoutManager layoutManager;
    int animationType=BaseAdapter.SLIDEIN_LEFT;

    OnBaseListener.OnRefreshListener onRefreshListener;
    OnBaseListener.OnLoadMoreListener onLoadMoreListener;

    public FRefreshManager(Context context, BaseAdapter adapter, FRefreshLayout refreshLayout) {
        this.context = context;
        this.adapter = adapter;
        this.refreshLayout = refreshLayout;
    }

    public FRefreshManager setOnRefreshListener(OnBaseListener.OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
        return this;
    }

    public FRefreshManager setOnLoadMoreListener(OnBaseListener.OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        return this;
    }

    public FRefreshManager setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        return this;
    }

    public void setAnimationType(int animationType) {
        this.animationType = animationType;
    }

    public FRefreshManager build() {
        if (null==layoutManager){
            refreshLayout.getRecyclerview().setLayoutManager(new LinearLayoutManager(context));
        }else {
            refreshLayout.getRecyclerview().setLayoutManager(layoutManager);
        }

        adapter.openLoadAnimation(BaseAdapter.SLIDEIN_LEFT);

        if (null==onRefreshListener){
            refreshLayout.setEnabled(false);
        }else {
            refreshLayout.setEnabled(true);
            refreshLayout.setLastUpdateTimeRelateObject(context);
            refreshLayout.setPtrHandler(new PtrHandler() {
                @Override
                public boolean checkCanDoRefresh(FPtrFrameLayout frame, View content, View header) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, refreshLayout.getRecyclerview(), header);
                }

                @Override
                public void onRefreshBegin(FPtrFrameLayout frame) {
                    onRefreshListener.onRefresh();
                }
            });
        }

        if (null==onLoadMoreListener){
            adapter.setEnableLoadMore(false);
        }else {
            adapter.setEnableLoadMore(true);
            refreshLayout.getRecyclerview().setOnLoadMoreListener(new OnBaseListener.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    adapter.showLoadMoreLoding();
                    onLoadMoreListener.onLoadMore();
                }
            });
        }

        refreshLayout.getRecyclerview().setAdapter(adapter);

        adapter.setOnSwitchStateListener(new OnSwitchStateListener() {
            @Override
            public void showEmpty() {
                refreshLayout.showEmptyView();
            }

            @Override
            public void showProgress() {
                refreshLayout.showProgressView();
            }

            @Override
            public void showContent() {
                refreshLayout.showRecyclerView();
            }
        });

        return this;
    }

    public void refreshComplete() throws Exception{
        if (null!=onRefreshListener){
            refreshLayout.refreshComplete();
        }else {
            throw new Exception("没有启用刷新功能");
        }
    }

    public FRefreshManager dividerDecoration(int color, int height, int paddingLeft, int paddingRight){
        DividerDecoration itemDecoration = new DividerDecoration(color, DensityUtil.dp2px(context,height), DensityUtil.dp2px(context,paddingLeft),DensityUtil.dp2px(context,paddingRight));
        itemDecoration.setDrawLastItem(true);//有时候你不想让最后一个item有分割线,默认true.
        itemDecoration.setDrawHeaderFooter(false);//是否对Header于Footer有效,默认false.

        refreshLayout.getRecyclerview().addItemDecoration(itemDecoration);

        return this;
    }

    public FRefreshManager spaceDecoration(int space){
        SpaceDecoration itemDecoration = new SpaceDecoration(DensityUtil.dp2px(context,space));
        itemDecoration.setPaddingEdgeSide(true);//是否为左右2边添加padding.默认true.
        itemDecoration.setPaddingStart(true);//是否在给第一行的item添加上padding(不包含header).默认true.
        itemDecoration.setPaddingHeaderFooter(false);//是否对Header于Footer有效,默认false.

        refreshLayout.getRecyclerview().addItemDecoration(itemDecoration);

        return this;
    }

}
