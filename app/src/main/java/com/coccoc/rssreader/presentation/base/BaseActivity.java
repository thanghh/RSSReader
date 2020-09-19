package com.coccoc.rssreader.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.coccoc.rssreader.R;
import com.coccoc.rssreader.manager.Constants;
import com.coccoc.rssreader.manager.DataManager;
import com.coccoc.rssreader.presentation.fragment.BottomSheetFragment;

public abstract class BaseActivity extends AppCompatActivity implements Constants {
    protected Context mContext;
    protected boolean isInitialized = false;

    public abstract Activity getActivity();

    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isInitialized) {
            initView();
            initData();
            isInitialized = true;
        }

    }

    public void showDialogWarningConnect() {
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setTitle(R.string.dialog_warning_connection_title)
                .setMessage(R.string.dialog_warning_connection_message)
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_warning_connection_positive,
                        (dialog, id) -> finish());

        AlertDialog alert = builder.create();
        alert.show();
    }

    protected void onShowHideBottomSheet() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    protected void onShowHideBottomSheet(WebView webView) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(webView);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    protected void onCheckMode() {
        if (DataManager.getInstance().getAppPreferences().isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
