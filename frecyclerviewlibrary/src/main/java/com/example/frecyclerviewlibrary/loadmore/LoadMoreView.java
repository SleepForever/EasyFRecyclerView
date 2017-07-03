package com.example.frecyclerviewlibrary.loadmore;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.example.frecyclerviewlibrary.BaseViewHolder;

/**
 * Created by BlingBling on 2016/11/11.
 */

public abstract class LoadMoreView {

    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;

    private int loadMoreStatus = STATUS_DEFAULT;

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.loadMoreStatus = loadMoreStatus;
    }

    public int getLoadMoreStatus() {
        return loadMoreStatus;
    }

    public void convert(BaseViewHolder holder) {
        switch (loadMoreStatus) {
            case STATUS_LOADING:
                showLoading(holder);
                break;
            case STATUS_FAIL:
                showFaild(holder);
                break;
            case STATUS_END:
                showEnd(holder);
                break;
            case STATUS_DEFAULT:
                showNone(holder);
                break;
        }
    }

    public void showLoading(BaseViewHolder holder) {
        loadMoreStatus = STATUS_LOADING;
        visibleLoading(holder, true);
        visibleLoadFail(holder, false);
        visibleLoadEnd(holder, false);
    }

    public void showFaild(BaseViewHolder holder) {
        loadMoreStatus = STATUS_FAIL;
        visibleLoading(holder, false);
        visibleLoadFail(holder, true);
        visibleLoadEnd(holder, false);
    }

    public void showEnd(BaseViewHolder holder) {
        loadMoreStatus = STATUS_END;
        visibleLoading(holder, false);
        visibleLoadFail(holder, false);
        visibleLoadEnd(holder, true);
    }

    public void showNone(BaseViewHolder holder) {
        loadMoreStatus = STATUS_DEFAULT;
        visibleLoading(holder, false);
        visibleLoadFail(holder, false);
        visibleLoadEnd(holder, false);
    }

    private void visibleLoading(BaseViewHolder holder, boolean visible) {
        int loadingViewId = getLoadingViewId();
        if (loadingViewId != 0) {
            holder.itemView.findViewById(loadingViewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void visibleLoadFail(BaseViewHolder holder, boolean visible) {
        int loadFailViewId = getLoadFailViewId();
        if (loadFailViewId != 0) {
            holder.itemView.findViewById(loadFailViewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    private void visibleLoadEnd(BaseViewHolder holder, boolean visible) {
        int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.itemView.findViewById(loadEndViewId).setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * load more layout
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * loading view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadingViewId();

    /**
     * load fail view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadFailViewId();

    /**
     * load end view, you can return 0
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadEndViewId();
}
