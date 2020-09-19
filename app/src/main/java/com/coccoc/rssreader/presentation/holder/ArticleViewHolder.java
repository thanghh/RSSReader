package com.coccoc.rssreader.presentation.holder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.coccoc.rssreader.databinding.ItemArticleMainBinding;
import com.coccoc.rssreader.presentation.activity.WebViewActivity;
import com.coccoc.rssreader.presentation.base.BaseRecycledHolder;
import com.coccoc.rssreader.util.LoadImageUtil;
import com.prof.rssparser.Article;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Objects;

public class ArticleViewHolder extends BaseRecycledHolder<Article> {
    private ItemArticleMainBinding viewDataBinding;

    public ArticleViewHolder(ItemArticleMainBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }

    @Override
    public void bind(Article article) {
        Document document = Jsoup.parse(Objects.requireNonNull(article.getDescription()));
        String url = document.getElementsByTag("img").attr("src");
        String description = document.text();
        Log.d("bind url", url);
        Log.d("bind description", description);
        LoadImageUtil.get(getContext()).loadNormal(url, viewDataBinding.mImageArticle);

        viewDataBinding.mTextTitle.setText(article.getTitle());
        viewDataBinding.mTextDescription.setText(description);

        viewDataBinding.getRoot().setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra(TRANSIT_KEY_URL, article.getLink());
            getActivity().startActivity(intent);
        });
    }
}
