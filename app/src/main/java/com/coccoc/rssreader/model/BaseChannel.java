package com.coccoc.rssreader.model;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class BaseChannel {
    private String title;
    private String description;
    private String image;
    private String link;
    private MutableLiveData<ArrayList<Article>> articles;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MutableLiveData<ArrayList<Article>> getArticles() {
        return articles;
    }

    public void setArticles(MutableLiveData<ArrayList<Article>> articles) {
        this.articles = articles;
    }
}
