package com.lpf.tools.feature.widgets.recyclerviewdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lpf.tools.R;
import com.lpf.tools.feature.networkdemo.ResponseEntity;
import com.lpf.tools.feature.widgets.recyclerviewdemo.itemdecoration.SpaceItemDecoration;
import com.lpf.tools.feature.widgets.recyclerviewdemo.viewholders.ResponseEntityViewBinder;
import com.lpf.tools.network.RequestMethod;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.drakeet.multitype.MultiTypeAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    MXRecyclerView recyclerview;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.rv_empty_layout)
    LinearLayout rvEmptyLayout;

    private ResponseEntity responseEntity;
    private boolean swipeToRefresh = true;
    private boolean loadMore = true;

    // request network
    private int start = 100, count = 10, total = 0;
    private int lastRefreshStartPos = -1, lastLoadMorePos = -1;
    private int firstPosition = 100;
    private MultiTypeAdapter adapter;
    private List<ResponseEntity.SubjectsBean> recyclerDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);

        requestData(true, firstPosition, count);
        initViews();
    }

    private void requestData(final boolean refresh, final int start, final int count) {
        //request data.
        RequestMethod.getInstance().getResponseData(start, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseEntity>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseEntity responseData) {
                        responseEntity = responseData;
                        total = responseData.getTotal();

                        swipe.setRefreshing(false);
                        recyclerview.finishRefreshing();
                        recyclerview.finishLoadingMore();

                        if (recyclerDatas == null) {
                            recyclerDatas = responseData.getSubjects();
                        } else {
                            if (refresh) {
                                recyclerDatas.addAll(0, responseData.getSubjects());
                            } else {
                                recyclerDatas.addAll(responseData.getSubjects());
                            }
                        }
                        adapter.setItems(recyclerDatas);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        disposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        disposable.dispose();

//                        if (start + count <= total) {
//                            recyclerview.enableLoadingMore();
//                        } else {
//                            recyclerview.disableLoadingMore();
//                        }

                    }
                });
    }

    private void initViews() {

        initLayoutManager();
        swipe.setEnabled(swipeToRefresh);
        adapter = new MultiTypeAdapter(recyclerDatas);
        initAdapter(adapter);
//        recyclerview.disableLoadingMore();
//        recyclerview.disableRefresh();
        recyclerview.setOnActionListener(new MXRecyclerView.OnActionListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }

            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    private void initAdapter(MultiTypeAdapter adapter) {
        adapter.register(ResponseEntity.SubjectsBean.class, new ResponseEntityViewBinder());
        recyclerview.setAdapter(adapter);
    }

    private void initLayoutManager() {
        RecyclerView.ItemDecoration decoration = new SpaceItemDecoration(
                0, 0, 0, 0,
                0, 0, 0, 0);
        recyclerview.addItemDecoration(decoration);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void loadMoreData() {
        if (responseEntity != null) {
            start = responseEntity.getStart();
            count = responseEntity.getCount();
            // 实际情况，实际分析
            if (lastLoadMorePos == -1) {
                lastLoadMorePos = firstPosition;
            }
            if (lastLoadMorePos + count <= responseEntity.getTotal()) {
                requestData(false, lastLoadMorePos + count, count);
                lastLoadMorePos = lastLoadMorePos + count;
            } else {
                recyclerview.finishLoadingMore();
                recyclerview.disableLoadingMore();
            }
        } else {
            Toast.makeText(RecyclerViewActivity.this, "responseEntity is null", Toast.LENGTH_SHORT).show();
        }
    }

    private void refreshData() {
        // 这是模拟的情况，从100往前加载数据
        if (responseEntity != null) {
            start = responseEntity.getStart();
            count = responseEntity.getCount();
            // 实际情况，实际分析
            if (lastRefreshStartPos == -1) {
                lastRefreshStartPos = firstPosition;
            }
            if (lastRefreshStartPos - count >= 0) {
                requestData(true, lastRefreshStartPos - count, count);
                lastRefreshStartPos = lastRefreshStartPos - count;
            } else {
                recyclerview.finishRefreshing();
            }
        } else {
            Toast.makeText(RecyclerViewActivity.this, "responseEntity is null", Toast.LENGTH_SHORT).show();
        }
    }
}