package com.example.frecyclerviewlibrary.contract;

/**
 * Created by feng on 2017/6/29.
 */

public interface OnBaseListener {

    interface OnRefreshListener{
        void onRefresh();
    }

    interface OnLoadMoreListener{
        void onLoadMore();
    }

    interface OnLoadMoreLodingListener {
        void showLoadMoreLoding();
    }

    interface OnLoadMoreFaildListener {
        void showLoadMoreFaild();
    }

    interface OnLoadMoreEndListener{
        void showLoadMoreEnd();
    }

    interface OnLoadMoreNoneListener {
        void showLoadMoreNone();
    }

    interface OnLoadMoreFaildClickListener {
        void onLoadMoreFaildClickListener();
    }
}
