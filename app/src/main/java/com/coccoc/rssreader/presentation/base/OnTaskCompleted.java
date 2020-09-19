package com.coccoc.rssreader.presentation.base;

import com.coccoc.rssreader.model.BaseChannel;

public interface OnTaskCompleted {
    void onTaskCompleted(BaseChannel channel);
    void onError(Exception e);
}
