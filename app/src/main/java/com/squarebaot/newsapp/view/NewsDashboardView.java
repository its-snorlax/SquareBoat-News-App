package com.squarebaot.newsapp.view;

import android.view.View;

import com.squarebaot.newsapp.model.Article;

import java.util.List;

public interface NewsDashboardView {
    void articlesFetchSuccessful(List<Article> article);

    void articlesFetchFail();

    void showProgressBar();

    void hideProgressBar();

    void onArticleSelect(View listItemView, List<Article> articles);

    void onArticleListIsEmpty();

    void onArticleListIsNotEmpty();

    void updateListBySources();
}