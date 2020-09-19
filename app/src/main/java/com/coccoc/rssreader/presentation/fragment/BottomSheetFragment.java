package com.coccoc.rssreader.presentation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.webkit.WebSettingsCompat;

import com.coccoc.rssreader.R;
import com.coccoc.rssreader.databinding.FragmentBottomSheetBinding;
import com.coccoc.rssreader.manager.DataManager;
import com.coccoc.rssreader.manager.RSSPreferences;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentBottomSheetBinding mFragmentBottomSheetBinding;
    private WebView mWebView;

    public BottomSheetFragment() {
    }

    public BottomSheetFragment(WebView webView) {
        mWebView = webView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentBottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bottom_sheet, container, false);
        return mFragmentBottomSheetBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onSwitchMode();
    }

    @SuppressLint("RequiresFeature")
    private void onSwitchMode() {
        if (DataManager.getInstance().getAppPreferences().isNightMode())
            mFragmentBottomSheetBinding.mSwitchMode.setChecked(true);
        else
            mFragmentBottomSheetBinding.mSwitchMode.setChecked(false);

        mFragmentBottomSheetBinding.mSwitchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                if (mWebView != null)
                    WebSettingsCompat.setForceDark(mWebView.getSettings(), WebSettingsCompat.FORCE_DARK_ON);

                DataManager.getInstance().getAppPreferences().saveNightMode(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                if (mWebView != null)
                    WebSettingsCompat.setForceDark(mWebView.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);

                DataManager.getInstance().getAppPreferences().saveNightMode(false);
            }
        });
    }

}
