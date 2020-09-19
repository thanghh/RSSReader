package com.coccoc.rssreader.util;

public class CommonUtil {
    private static final String TAG = CommonUtil.class.getName();

    public static boolean isNullOrEmpty(String content) {
        return content == null || content.isEmpty();
    }
}
