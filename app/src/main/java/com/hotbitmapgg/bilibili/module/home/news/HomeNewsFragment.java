package com.hotbitmapgg.bilibili.module.home.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hotbitmapgg.bilibili.adapter.NewsAdapter;
import com.hotbitmapgg.bilibili.base.RxLazyFragment;
import com.hotbitmapgg.bilibili.entity.news.NewsInfo;
import com.hotbitmapgg.bilibili.module.common.BrowserActivity;
import com.hotbitmapgg.bilibili.network.RetrofitHelper;
import com.hotbitmapgg.bilibili.utils.ConstantUtil;
import com.hotbitmapgg.bilibili.utils.SnackbarUtil;
import com.hotbitmapgg.bilibili.widget.CustomEmptyView;
import com.hotbitmapgg.ohmybilibili.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;

public class HomeNewsFragment extends RxLazyFragment {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_layout)
    CustomEmptyView mCustomEmptyView;

    private boolean mIsRefreshing = false;
    private NewsAdapter mNewsAdapter;
    private List<NewsInfo.Result> infoList = new ArrayList<>();

    public static HomeNewsFragment newInstance() {
        return new HomeNewsFragment();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_news;
    }

    @Override
    public void finishCreateView(Bundle state) {
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible){
            return ;
        }
        initRecyclerView();
        initRefreshLayout();
        isPrepared = false;
    }

    @Override
    protected void initRecyclerView() {
        mNewsAdapter = new NewsAdapter(mRecyclerView, infoList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mNewsAdapter);
        mNewsAdapter.setOnItemClickListener(((position, holder) -> {
            Intent intent = new Intent(getActivity(), BrowserActivity.class);
            intent.putExtra(ConstantUtil.EXTRA_TITLE, infoList.get(position).getTitle());
            intent.putExtra(ConstantUtil.EXTRA_URL, infoList.get(position).getPath());
            startActivity(intent);
        }));
        setRecycleNoScroll();
    }

    @Override
    protected void initRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.post(()->{
            mIsRefreshing = true;
            mSwipeRefreshLayout.setRefreshing(true);
            loadData();
        });
        mSwipeRefreshLayout.setOnRefreshListener(()->{
            clearData();
            loadData();
        });
    }

    private void clearData() {
        mIsRefreshing = true;
        infoList.clear();
        mNewsAdapter.removeAll();
    }

    @Override
    protected void loadData() {
        RetrofitHelper.getNewsAPI()
                .getNews()
                .compose(bindToLifecycle())
                .delay(1000, TimeUnit.MILLISECONDS)
                .map(NewsInfo::getResult)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result != null && result.size() != 0){
                        infoList.addAll(result);
                        finishTask();
                    }
                }, throwable -> {
                    initEmptyView();
                    System.out.println("-----throwable=" + throwable.getMessage());
                });
    }

    @Override
    protected void finishTask() {
        super.finishTask();
        mSwipeRefreshLayout.setRefreshing(false);
        mNewsAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(0);
        hideEmptyView();
    }

    private void initEmptyView() {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.setVisibility(View.GONE);
        mCustomEmptyView.setVisibility(View.VISIBLE);
        mCustomEmptyView.setEmptyImage(R.drawable.img_tips_error_load_error);
        mCustomEmptyView.setEmptyText("加载失败~(≧▽≦)~啦啦啦.");
        SnackbarUtil.showMessage(mRecyclerView,"数据加载失败,请重新加载或者检查网络是否链接");
    }

    private void hideEmptyView() {
        mCustomEmptyView.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void setRecycleNoScroll() {
        mRecyclerView.setOnTouchListener((v,event) -> mIsRefreshing);
    }
}
