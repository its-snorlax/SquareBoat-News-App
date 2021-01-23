package com.squarebaot.newsapp;

import android.text.Editable;
import android.text.TextWatcher;

import com.squarebaot.newsapp.adapter.NewsArticleDashboardAdapter;
import com.squarebaot.newsapp.model.Article;
import com.squarebaot.newsapp.view.NewsDashboardView;

import java.util.ArrayList;
import java.util.List;

public class ArticleSearchTextWatcher implements TextWatcher {
    private final List<Article> articles;
    private final NewsArticleDashboardAdapter adapter;
    private NewsDashboardView newsDashboardView;

    public ArticleSearchTextWatcher(List<Article> articles, NewsArticleDashboardAdapter adapter,
                                    NewsDashboardView newsDashboardView) {
        this.articles = articles;
        this.adapter = adapter;
        this.newsDashboardView = newsDashboardView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String searchKeyword = s.toString();
        if (searchKeyword.isEmpty()) adapter.updateDataSet(articles);
        List<Article> filteredList = new ArrayList<>();
        for (Article article : this.articles) {
            if (article.getTitle().contains(searchKeyword.toLowerCase()) ||
                    article.getTitle().contains(searchKeyword.toUpperCase()))
                filteredList.add(article);
        }

        if (filteredList.isEmpty()) newsDashboardView.onArticleListIsEmpty();
        else {
            newsDashboardView.onArticleListIsNotEmpty();
            adapter.updateDataSet(filteredList);
        }
    }
}
