package com.squarebaot.newsapp.activity;

import android.content.Intent;
import android.net.Uri;
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
import butterknife.OnClick;

import static com.squarebaot.newsapp.R.id.article_image;
import static com.squarebaot.newsapp.R.id.created_at;
import static com.squarebaot.newsapp.R.id.description;
import static com.squarebaot.newsapp.R.id.read_full_story;
import static com.squarebaot.newsapp.R.id.source;
import static com.squarebaot.newsapp.R.id.title;

public class ArticleBrowseActivity extends AppCompatActivity {

    @BindView(source)
    TextView sourceTextView;

    @BindView(article_image)
    ImageView articleImageView;

    @BindView(title)
    TextView titleTextView;

    @BindView(created_at)
    TextView createdAtTextView;

    @BindView(description)
    TextView descriptionTextView;

    private Article article;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_article);
        ButterKnife.bind(this);
        article = (Article) getIntent().getSerializableExtra(Constant.ARTICLE);
        bindDataWithViews(article);
    }

    private void bindDataWithViews(Article article) {
        Picasso.get().load(article.getUrlToImage()).fit().centerCrop().into(articleImageView);
        titleTextView.setText(article.getTitle());
        sourceTextView.setText(article.getSource().getName());
        createdAtTextView.setText(article.getPublishedAt());
        descriptionTextView.setText(article.getContent() == null ? article.getDescription()
                : article.getContent().split("[+]")[0]);
    }

    @OnClick(read_full_story)
    public void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(article.getUrl()));
        startActivity(intent);
    }
}
