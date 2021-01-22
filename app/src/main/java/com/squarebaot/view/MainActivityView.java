package com.squarebaot.view;

import com.squarebaot.newsapp.model.Article;

import java.util.List;

public interface MainActivityView {
    void articlesFetchSuccessful(List<Article> article);

    void articlesFetchFail();

    void showProgressBar();

    void hideProgressBar();
}
