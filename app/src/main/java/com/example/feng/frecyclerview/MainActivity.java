package com.example.feng.frecyclerview;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.example.frecyclerviewlibrary.BaseAdapter;
import com.example.frecyclerviewlibrary.FRefreshManager;
import com.example.frecyclerviewlibrary.contract.OnBaseListener;
import com.example.frecyclerviewlibrary.contract.OnSwitchStateListener;
import com.example.frecyclerviewlibrary.ptrlib.ptr.PtrDefaultHandler;
import com.example.frecyclerviewlibrary.ptrlib.ptr.PtrHandler;
import com.example.frecyclerviewlibrary.util.LogUtil;
import com.example.frecyclerviewlibrary.widget.FPtrFrameLayout;
import com.example.frecyclerviewlibrary.widget.FRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    HomeAdapter adapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    FRefreshLayout fBaseRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        List<String> data = new ArrayList<>();

        adapter = new HomeAdapter(data);
        View headView = LayoutInflater.from(this).inflate(R.layout.view_head, null, false);
        adapter.addHeaderView(headView);
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData("hello");
            }
        });
        adapter.setOnItemClick(new HomeAdapter.OnItemClick() {
            @Override
            public void onItemClick(String data, int position) {
                adapter.remove(position);
            }
        });

        new FRefreshManager(this,adapter,fBaseRefreshLayout).setOnLoadMoreListener(new OnBaseListener.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                fBaseRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 3; i++) {
                            adapter.addData("sd");
                        }
                        adapter.showLoadMoreEnd();
                    }
                },2000);
            }
        }).setOnRefreshListener(new OnBaseListener.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clearData();
                for (int i = 0; i < 5; i++) {
                    adapter.addData("fs");
                }
                fBaseRefreshLayout.refreshComplete();
            }
        }).build();

        fBaseRefreshLayout.showProgressView();
    }

}
