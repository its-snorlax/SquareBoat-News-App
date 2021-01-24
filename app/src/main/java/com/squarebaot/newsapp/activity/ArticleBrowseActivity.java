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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.squarebaot.newsapp.R.id.article_image;
import static com.squarebaot.newsapp.R.id.created_at;
import static com.squarebaot.newsapp.R.id.description;
import static com.squarebaot.newsapp.R.id.read_full_story;
import static com.squarebaot.newsapp.R.id.source;
import static com.squarebaot.newsapp.R.id.title;
import static com.squarebaot.newsapp.network.Constant.DESIRED_STRING_FORMAT;
import static com.squarebaot.newsapp.network.Constant.ORIGINAL_STRING_FORMAT;

public class ArticleBrowseActivity extends BaseActivity {

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
        createdAtTextView.setText(formatDate(article.getPublishedAt()));
        descriptionTextView.setText(article.getContent() == null ? article.getDescription()
                : article.getContent().split("[+]")[0]);
    }

    private String formatDate(String publishedAt) {
        SimpleDateFormat readingFormat = new SimpleDateFormat(ORIGINAL_STRING_FORMAT);
        SimpleDateFormat outputFormat = new SimpleDateFormat(DESIRED_STRING_FORMAT);
        Date date = null;
        try {
            date = readingFormat.parse(publishedAt);
            System.out.println(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] formattedDate = date.toString().split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append(formattedDate[2])
                .append("-")
                .append(formattedDate[1])
                .append("-")
                .append(formattedDate[5])
                .append(" at ").append(formattedDate[3]).toString();
    }

    @OnClick(read_full_story)
    public void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(article.getUrl()));
        startActivity(intent);
    }
}
