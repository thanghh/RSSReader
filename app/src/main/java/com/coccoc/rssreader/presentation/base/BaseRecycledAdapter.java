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

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private OnLoadMoreListener onLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    protected List<T> items;

    private OnItemClickListener onItemClickListener;

    protected H h;

    public BaseRecycledAdapter() {
        items = new ArrayList<>();
    }

    protected View inflate(@LayoutRes int id, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(id, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecycledHolder holder, int position) {
        assert items != null;

        if (items.size() > 0) {
            final T t = items.get(position);
            onBindHolder((H) holder, t, position);
            if (onItemClickListener != null && items.get(position) != null) {
                holder.itemView.setOnClickListener(v -> {
                    onItemClickListener.onItemClick(position);
                });
            }
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

    public void addAllAndNotify(List<T> moreItems) {
        int startItem = items.size() - 1;
        items.addAll(moreItems);
        int endItem = items.size() - 1;
        notifyItemRangeChanged(startItem, endItem);
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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
