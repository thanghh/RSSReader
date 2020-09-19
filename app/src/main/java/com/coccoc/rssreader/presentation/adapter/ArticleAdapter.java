package com.coccoc.rssreader.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.coccoc.rssreader.R;
import com.coccoc.rssreader.databinding.ItemArticleMainBinding;
import com.coccoc.rssreader.presentation.base.BaseRecycledAdapter;
import com.coccoc.rssreader.presentation.base.BaseRecycledHolder;
import com.coccoc.rssreader.presentation.holder.ArticleViewHolder;
import com.prof.rssparser.Article;

public class ArticleAdapter extends BaseRecycledAdapter<Article, ArticleViewHolder> {

    @NonNull
    @Override
    public BaseRecycledHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemArticleMainBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_article_main, parent, false);

        return new ArticleViewHolder(binding);
    }

    @Override
    protected void onBindHolder(ArticleViewHolder articleViewHolder, Article article, int position) {
        super.onBindHolder(articleViewHolder, article, position);
    }
}
