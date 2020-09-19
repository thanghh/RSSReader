package com.coccoc.rssreader.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.coccoc.rssreader.R;

public class RSSPreferences {
    public final String KEY_SWICH_MODE = "KEY_SWICH_MODE";

    private SharedPreferences.Editor mEditor;
    private SharedPreferences mPref;
    private Context mContext;


    public RSSPreferences(Context context) {
        this.mContext = context;
        this.mPref = mContext.getSharedPreferences(mContext.getResources().getString(R.string.app_name),
                Context.MODE_PRIVATE);
        this.mEditor = mPref.edit();
    }

    public void clear() {
        mEditor.clear();
        mEditor.commit();
    }

    public boolean isNightMode() {
        return mPref.getBoolean(KEY_SWICH_MODE, false);
    }

    public void saveNightMode(boolean isNightMode) {
        mEditor.putBoolean(KEY_SWICH_MODE, isNightMode);
        mEditor.commit();
    }
}
