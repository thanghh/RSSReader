package com.coccoc.rssreader.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coccoc.rssreader.domain.Domain;
import com.prof.rssparser.Channel;
import com.prof.rssparser.OnTaskCompleted;
import com.prof.rssparser.Parser;


public class MainViewModel extends ViewModel {
    private MutableLiveData<Channel> articleListLive = null;

    private MutableLiveData<String> snackbar = new MutableLiveData<>();

    public MutableLiveData<Channel> getChannel() {
        if (articleListLive == null) {
            articleListLive = new MutableLiveData<>();
        }
        return articleListLive;
    }

    private void setChannel(Channel channel) {
        this.articleListLive.postValue(channel);
    }

    public LiveData<String> getSnackbar() {
        return snackbar;
    }

    public void onSnackbarShowed() {
        snackbar.setValue(null);
    }

    public void fetchArticle() {

        Parser parser = new Parser();
        parser.onFinish(new OnTaskCompleted() {

            @Override
            public void onTaskCompleted(Channel channel) {
                setChannel(channel);
            }

            @Override
            public void onError(@NonNull Exception e) {
                snackbar.postValue("An error has occurred. Please try again");
            }
        });
        parser.execute(Domain.URL_RSS);
    }
}
