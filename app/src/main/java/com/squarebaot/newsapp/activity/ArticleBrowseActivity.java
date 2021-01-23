package com.squarebaot.newsapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squarebaot.newsapp.R;
import com.squarebaot.newsapp.model.Article;
import com.squarebaot.newsapp.network.Constant;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleBrowseActivity extends AppCompatActivity {

    @BindView(R.id.source)
    TextView sourceTextView;

    @BindView(R.id.article_image)
    ImageView articleImageView;

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.created_at)
    TextView createdAtTextView;

    @BindView(R.id.description)
    TextView descriptionTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_article);
        ButterKnife.bind(this);
        Article article = (Article) getIntent().getSerializableExtra(Constant.ARTICLE);
        bindDataWithViews(article);
    }

    private void bindDataWithViews(Article article) {
        Picasso.get().load(article.getUrlToImage()).into(articleImageView);
        titleTextView.setText(article.getTitle());
        sourceTextView.setText(article.getSource().getName());
        createdAtTextView.setText(article.getPublishedAt());
        descriptionTextView.setText(article.getDescription());
    }
}
