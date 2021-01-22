package com.squarebaot.newsapp.network.response;

import com.squarebaot.newsapp.model.Article;

import java.util.List;

public class ApiResponse {
    private String status;
    private int totalResult;
    private List<Article> articles;

    public ApiResponse(String status, int totalResult, List<Article> articles) {
        this.status = status;
        this.totalResult = totalResult;
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
