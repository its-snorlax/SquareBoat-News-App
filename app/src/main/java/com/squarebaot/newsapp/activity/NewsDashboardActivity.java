package com.squarebaot.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squarebaot.newsapp.R;
import com.squarebaot.newsapp.adapter.NewsArticleDashboardAdapter;
import com.squarebaot.newsapp.model.Article;
import com.squarebaot.newsapp.network.Constant;
import com.squarebaot.newsapp.network.ServiceBuilder;
import com.squarebaot.newsapp.network.services.FetchNewsArticle;
import com.squarebaot.newsapp.presenter.MainActivityPresenter;
import com.squarebaot.newsapp.view.MainActivityView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDashboardActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    MainActivityPresenter mainActivityPresenter;
    private List<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_dashboard);
        ButterKnife.bind(this);

        mainActivityPresenter = new MainActivityPresenter(
                ServiceBuilder.build(FetchNewsArticle.class), this);
        mainActivityPresenter.fetchTopHeadlines();
    }

    @Override
    public void articlesFetchSuccessful(List<Article> articles) {
        this.articles = articles;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NewsArticleDashboardAdapter adapter = new NewsArticleDashboardAdapter(articles, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void articlesFetchFail() {
        Toast.makeText(getApplicationContext(), "Request Fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onArticleSelect(View listItemView) {
        Intent intent = new Intent(this, ArticleBrowseActivity.class);
        intent.putExtra(Constant.ARTICLE, articles.get(recyclerView.getChildLayoutPosition(listItemView)));
        startActivity(intent);
    }

}