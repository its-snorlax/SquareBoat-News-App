package com.squarebaot.newsapp.presenter;

import com.squarebaot.newsapp.model.Article;
import com.squarebaot.newsapp.network.response.ApiResponse;
import com.squarebaot.newsapp.network.services.FetchNewsArticle;
import com.squarebaot.newsapp.view.NewsDashboardView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.squarebaot.newsapp.network.Constant.API_KEY;

public class MainActivityPresenter {
    private final FetchNewsArticle fetchNewsArticle;
    private final NewsDashboardView newsDashboardView;

    public MainActivityPresenter(FetchNewsArticle fetchNewsArticle, NewsDashboardView newsDashboardView) {
        this.fetchNewsArticle = fetchNewsArticle;
        this.newsDashboardView = newsDashboardView;
    }


    public void fetchTopHeadlines() {
        newsDashboardView.showProgressBar();
        fetchNewsArticle.fetchTopHeadlines("IN", API_KEY).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                newsDashboardView.hideProgressBar();
                if (response.isSuccessful()) {
                    List<Article> articles = response.body().getArticles();
                    if (articles.isEmpty()) newsDashboardView.onArticleListIsEmpty();
                    else {
                        newsDashboardView.onArticleListIsNotEmpty();
                        newsDashboardView.articlesFetchSuccessful(articles);
                    }
                    return;
                }
                newsDashboardView.articlesFetchFail();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                newsDashboardView.hideProgressBar();
                newsDashboardView.articlesFetchFail();
            }
        });
    }
}
