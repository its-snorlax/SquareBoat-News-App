package com.squarebaot.newsapp.presenter;

import com.squarebaot.newsapp.network.response.ApiResponse;
import com.squarebaot.newsapp.network.services.FetchNewsArticle;
import com.squarebaot.newsapp.view.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.squarebaot.newsapp.network.Constant.API_KEY;

public class MainActivityPresenter {
    private final FetchNewsArticle fetchNewsArticle;
    private final MainActivityView mainActivityView;

    public MainActivityPresenter(FetchNewsArticle fetchNewsArticle, MainActivityView mainActivityView) {
        this.fetchNewsArticle = fetchNewsArticle;
        this.mainActivityView = mainActivityView;
    }


    public void fetchTopHeadlines() {
        mainActivityView.showProgressBar();
        fetchNewsArticle.fetchTopHeadlines("IN", API_KEY).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                mainActivityView.hideProgressBar();
                if (response.isSuccessful()) {
                    mainActivityView.articlesFetchSuccessful(response.body().getArticles());
                    return;
                }
                mainActivityView.articlesFetchFail();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mainActivityView.hideProgressBar();
                mainActivityView.articlesFetchFail();
            }
        });
    }
}
