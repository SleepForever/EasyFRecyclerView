package com.example.frecyclerviewlibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.frecyclerviewlibrary.R;
import com.example.frecyclerviewlibrary.header.DefaultHeader;

public class FRefreshLayout extends FPtrFrameLayout {

    NestedScrollView progressView;
    FRecyclerView recyclerview;
    NestedScrollView emytyView;

    private DefaultHeader mPtrClassicHeader;
    private Context context;
    private boolean isWithProgress;
    private FrameLayout contentView = null;

    public FRefreshLayout(Context context) {
        super(context, null);
    }

    public FRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //获取所有的自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FBaseRefreshLayout);
        isWithProgress = a.getBoolean(R.styleable.FBaseRefreshLayout_with_progrss, false);
        initViews();

        a.recycle();
    }

    public FRefreshLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initViews() {
        mPtrClassicHeader = new DefaultHeader(getContext());
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);

        contentView = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.view_content_with_progress, null, false);
        progressView= (NestedScrollView) contentView.findViewById(R.id.progress_view);
        recyclerview= (FRecyclerView) contentView.findViewById(R.id.recyclerview);
        emytyView= (NestedScrollView) contentView.findViewById(R.id.emyty_view);

        if (!isWithProgress) {
            contentView.removeViewAt(0);
            progressView=null;
        }
        addView(contentView);
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

    public void removeProgressView() {
        if (isWithProgress) {
            contentView.removeViewAt(0);
            progressView=null;
            isWithProgress = false;
        }
    }

    public void showProgressView() {
        if (isWithProgress&&null!=progressView) {
            progressView.setVisibility(VISIBLE);
            recyclerview.setVisibility(GONE);
            emytyView.setVisibility(GONE);
        }
    }

    public void showRecyclerView() {
        if (isWithProgress&&null!=progressView) {
            progressView.setVisibility(GONE);
        }
        recyclerview.setVisibility(VISIBLE);
        emytyView.setVisibility(GONE);
    }

    public void showEmptyView() {
        if (isWithProgress&&null!=progressView) {
            progressView.setVisibility(GONE);
        }
        recyclerview.setVisibility(GONE);
        emytyView.setVisibility(VISIBLE);
    }

    public NestedScrollView getProgressView() {
        return progressView;
    }

    public FRecyclerView getRecyclerview() {
        return recyclerview;
    }

    public NestedScrollView getEmytyView() {
        return emytyView;
    }
}
