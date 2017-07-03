package com.example.frecyclerviewlibrary.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.example.frecyclerviewlibrary.header.DefaultHeader;

public class FBaseRefreshLayout extends FPtrFrameLayout {

    private DefaultHeader mPtrClassicHeader;

    public FBaseRefreshLayout(Context context) {
        super(context, null);
    }

    public FBaseRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public FBaseRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initViews() {
        mPtrClassicHeader = new DefaultHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
    }

    public DefaultHeader getHeader() {
        return mPtrClassicHeader;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeKey(key);
        }
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
            mPtrClassicHeader.setLastUpdateTimeRelateObject(object);
        }
    }

}
