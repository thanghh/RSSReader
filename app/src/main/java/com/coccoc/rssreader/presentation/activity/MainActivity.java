package com.coccoc.rssreader.presentation.activity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.coccoc.rssreader.R;
import com.coccoc.rssreader.databinding.ActivityMainBinding;
import com.coccoc.rssreader.manager.DataManager;
import com.coccoc.rssreader.presentation.adapter.ArticleAdapter;
import com.coccoc.rssreader.presentation.base.BaseActivity;
import com.coccoc.rssreader.presentation.fragment.BottomSheetFragment;
import com.coccoc.rssreader.presentation.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private MainViewModel mViewModel;
    private ActivityMainBinding mActivityMainBinding;
    private ArticleAdapter mArticleAdapter;

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mActivityMainBinding.mSwipeRefreshLayout.setOnRefreshListener(this);
        mActivityMainBinding.mSwipeRefreshLayout.setRefreshing(true);
        mArticleAdapter = new ArticleAdapter();
        mActivityMainBinding.mRecyclerViewRSS.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        onCheckMode();
        fetchArticle();

        mViewModel.getChannel().observe(this, channel -> {
            if (channel != null) {
                if (channel.getTitle() != null) {
                    mActivityMainBinding.mTextTitle.setText(channel.getTitle());
                }
                if (channel.getArticles().size() > 0) {
                    mArticleAdapter.addAll(channel.getArticles());
                    mActivityMainBinding.mRecyclerViewRSS.setAdapter(mArticleAdapter);
                    mActivityMainBinding.mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        mActivityMainBinding.mImageSettings.setOnClickListener(v -> {
            onShowHideBottomSheet();
        });
    }

    @Override
    public void onRefresh() {
        fetchArticle();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void fetchArticle() {
        if (isNetworkAvailable()) {
            mViewModel.fetchArticle();
        } else {
            showDialogWarningConnect();
        }
    }
}
