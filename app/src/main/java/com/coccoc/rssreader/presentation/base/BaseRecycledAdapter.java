package com.coccoc.rssreader.presentation.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecycledAdapter<T, H> extends RecyclerView.Adapter<BaseRecycledHolder> {

    protected List<T> items;

    protected H h;

    public BaseRecycledAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecycledHolder holder, int position) {
        assert items != null;

        if (items.size() > 0) {
            final T t = items.get(position);
            onBindHolder((H) holder, t, position);
        }
    }

    protected void onBindHolder(H h, T t, int position) {
        this.h = h;
        ((BaseRecycledHolder) h).bind(t);
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        return 0;
    }

    public T getItem(int position) {
        if (items != null && items.size() > position)
            return items.get(position);
        return null;
    }

    public void addAll(List<T> list) {
        if (items != null) {
            items.clear();
            items.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void addMoreItem(List<T> moreItems) {
        int startItem = items.size() - 1;
        items.addAll(moreItems);
        notifyItemRangeChanged(startItem, items.size());
    }

}
