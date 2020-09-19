package com.coccoc.rssreader.presentation.activity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.webkit.WebSettingsCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.coccoc.rssreader.R;
import com.coccoc.rssreader.databinding.ActivityWebViewBinding;
import com.coccoc.rssreader.manager.DataManager;
import com.coccoc.rssreader.presentation.base.BaseActivity;
import com.coccoc.rssreader.util.CommonUtil;

import java.util.Map;

public class WebViewActivity extends BaseActivity {
    private ActivityWebViewBinding mActivityWebViewBinding;
    private String mUrl;

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        mActivityWebViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);

        mUrl = getIntent().getStringExtra(TRANSIT_KEY_URL);

        if (CommonUtil.isNullOrEmpty(mUrl))
            finish();

        onSetupWebView();
    }

    @Override
    public void initData() {
        if (!CommonUtil.isNullOrEmpty(mUrl)) {
            loadUrl();
        }

        mActivityWebViewBinding.mImageSettings.setOnClickListener(v -> {
            onShowHideBottomSheet(mActivityWebViewBinding.mWebView);
        });

        mActivityWebViewBinding.mLayoutClose.setOnClickListener(v -> finish());
    }

    @SuppressLint({"SetJavaScriptEnabled", "RequiresFeature"})
    protected void onSetupWebView() {
        if (DataManager.getInstance().getAppPreferences().isNightMode()) {
            WebSettingsCompat.setForceDark(mActivityWebViewBinding.mWebView.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
        } else {
            WebSettingsCompat.setForceDark(mActivityWebViewBinding.mWebView.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
        }
        mActivityWebViewBinding.mWebView.getSettings().setJavaScriptEnabled(true);
        mActivityWebViewBinding.mWebView.getSettings().setLoadWithOverviewMode(true);
        mActivityWebViewBinding.mWebView.getSettings().setUseWideViewPort(false);
        mActivityWebViewBinding.mWebView.setWebViewClient(new WebViewClient());
        mActivityWebViewBinding.mWebView.requestFocus(View.FOCUS_DOWN | View.FOCUS_UP);
        mActivityWebViewBinding.mWebView.setWebChromeClient(new WebChromeClient());
        mActivityWebViewBinding.mWebView.getSettings().setLoadsImagesAutomatically(true);
        mActivityWebViewBinding.mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    /**
     * request load url
     */
    protected void loadUrl() {
        Map<String, String> headers = getHeaders();
        mActivityWebViewBinding.mWebView.stopLoading();
        if (headers != null) {
            mActivityWebViewBinding.mWebView.loadUrl(mUrl, headers);
        } else {
            mActivityWebViewBinding.mWebView.loadUrl(mUrl);
        }
    }

    public Map<String, String> getHeaders() {
        return null;
    }

}
