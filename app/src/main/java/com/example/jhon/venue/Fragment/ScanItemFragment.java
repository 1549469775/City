package com.example.jhon.venue.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.example.jhon.venue.Adapter.QuickAdapter;
import com.example.jhon.venue.Bean.Test;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by John on 2017/3/24.
 */

public class ScanItemFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.srl_scan)
    SwipeRefreshLayout srlScan;
    private QuickAdapter quickAdapter;

    private List<Test> list;

    public static ScanItemFragment getInstance() {
        ScanItemFragment fra = new ScanItemFragment();
        return fra;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.scan_recycle_layout, container, false);
        ButterKnife.bind(this, v);
        initSWL();
        initRecycleView();
        return v;
    }

    private void initSWL() {
        srlScan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO 模拟刷新数据
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(0,addSampleData(2));
                        quickAdapter.notifyDataSetChanged();
                        srlScan.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    private void initRecycleView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        quickAdapter = new QuickAdapter(getContext(),getSampleData(20));
        recyclerview.setAdapter(quickAdapter);

        quickAdapter.isFirstOnly(false);
        quickAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view, "scaleY", 1, 1.1f, 1),
                        ObjectAnimator.ofFloat(view, "scaleX", 1, 1.1f, 1)
                };
            }
        });

        quickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Snackbar.make(view, "" + position + adapter.getItem(position).toString(), Snackbar.LENGTH_SHORT).show();
                return false;
            }
        });

        quickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                //TODO 模拟加载数据
                recyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<Test> tests = new ArrayList<>();
                        for (int i = 0; i < 5; i++) {
                            Test status = new Test();
                            status.setName("Chad" + i);
                            tests.add(status);
                        }
                        list.addAll(tests);
                        quickAdapter.notifyDataSetChanged();
                        quickAdapter.loadMoreComplete();
                    }
                }, 1000);
            }
        }, recyclerview);
    }

    public List<Test> getSampleData(int lenth) {
        list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Test status = new Test();
            status.setName("Chad" + i);
            list.add(status);
        }
        return list;
    }
    public List<Test> addSampleData(int lenth) {
        List<Test> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Test status = new Test();
            status.setName("ggg" + i);
            list.add(status);
        }
        return list;
    }

}
