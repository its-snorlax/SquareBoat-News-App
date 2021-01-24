package com.squarebaot.newsapp.network.services;


import com.squarebaot.newsapp.network.response.SourceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FetchArticleSource {
    @GET("/v2/sources")
    Call<SourceResponse> getAllSources(@Query("apikey") String apiKey);
}
