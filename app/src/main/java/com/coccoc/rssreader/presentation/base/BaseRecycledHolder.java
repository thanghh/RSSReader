package com.coccoc.rssreader.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.coccoc.rssreader.manager.Constants;

public class BaseRecycledHolder<T> extends RecyclerView.ViewHolder implements Constants {

    public BaseRecycledHolder(View itemView) {
        super(itemView);
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected Context getContext() {
        return itemView.getContext();
    }

    protected String getString(int stringId) {
        return itemView.getContext().getString(stringId);
    }

    public void bind(T t) {

    }

    public Activity getActivity() {
        return (Activity) getContext();
    }

}