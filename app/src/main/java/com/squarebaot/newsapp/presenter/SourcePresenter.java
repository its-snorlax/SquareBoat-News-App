package com.squarebaot.newsapp.presenter;

import com.squarebaot.newsapp.network.response.SourceResponse;
import com.squarebaot.newsapp.network.services.FetchArticleSource;
import com.squarebaot.newsapp.view.SourceListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.squarebaot.newsapp.network.Constant.API_KEY;

public class SourcePresenter {
    private final FetchArticleSource fetchArticleSourceService;
    private final SourceListView sourceListView;

    public SourcePresenter(FetchArticleSource fetchArticleSourceService, SourceListView sourceListView) {
        this.fetchArticleSourceService = fetchArticleSourceService;
        this.sourceListView = sourceListView;
    }

    public void fetchSource() {
        fetchArticleSourceService.getAllSources(API_KEY).enqueue(new Callback<SourceResponse>() {
            @Override
            public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                if (response.isSuccessful()) {
                    sourceListView.onSourceFetchSuccessful(response.body().getSources());
                    return;
                }
                sourceListView.onSourceFetchFail();
            }

            @Override
            public void onFailure(Call<SourceResponse> call, Throwable t) {
                sourceListView.onSourceFetchFail();
            }
        });
    }
}
