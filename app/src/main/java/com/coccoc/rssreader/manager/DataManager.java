package com.coccoc.rssreader.manager;

import com.coccoc.rssreader.application.MyApplication;

public class DataManager {
    static DataManager mInstance;

    RSSPreferences mAppPreferences;

    public static DataManager getInstance() {
        if (mInstance == null)
            mInstance = new DataManager();
        return mInstance;
    }

    public DataManager() {
        mAppPreferences = new RSSPreferences(MyApplication.getInstance());
    }

    public RSSPreferences getAppPreferences() {
        return mAppPreferences;
    }

}
