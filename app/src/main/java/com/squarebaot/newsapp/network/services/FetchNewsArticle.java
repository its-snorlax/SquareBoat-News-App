package com.squarebaot.newsapp.network.services;

import com.squarebaot.newsapp.network.response.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FetchNewsArticle {
    @GET("/v2/top-headlines")
    Call<ApiResponse> fetchTopHeadlines(@Query("Country") String country, @Query("apikey") String apiKey);
}