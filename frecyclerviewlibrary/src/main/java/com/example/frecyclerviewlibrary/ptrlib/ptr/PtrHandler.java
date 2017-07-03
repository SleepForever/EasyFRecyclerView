package com.example.frecyclerviewlibrary.ptrlib.ptr;

import android.view.View;

import com.example.frecyclerviewlibrary.widget.FPtrFrameLayout;

public interface PtrHandler {

    /**
     * Check can do refresh or not. For example the content is empty or the first child is in view.
     * <p/>
     */
    public boolean checkCanDoRefresh(final FPtrFrameLayout frame, final View content, final View header);

    /**
     * When refresh begin
     *
     * @param frame
     */
    public void onRefreshBegin(final FPtrFrameLayout frame);
}