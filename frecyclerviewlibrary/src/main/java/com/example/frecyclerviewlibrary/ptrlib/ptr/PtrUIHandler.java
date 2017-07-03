package com.example.frecyclerviewlibrary.ptrlib.ptr;

import com.example.frecyclerviewlibrary.ptrlib.indicator.PtrIndicator;
import com.example.frecyclerviewlibrary.widget.FPtrFrameLayout;

/**
 *
 */
public interface PtrUIHandler {

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     *
     * @param frame
     */
    public void onUIReset(FPtrFrameLayout frame);

    /**
     * prepare for loading
     *
     * @param frame
     */
    public void onUIRefreshPrepare(FPtrFrameLayout frame);

    /**
     * perform refreshing UI
     */
    public void onUIRefreshBegin(FPtrFrameLayout frame);

    /**
     * perform UI after refresh
     */
    public void onUIRefreshComplete(FPtrFrameLayout frame);

    public void onUIPositionChange(FPtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator);
}
