package com.squarebaot.newsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarebaot.newsapp.model.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder> {
    private final List<Article> articles;

    public NewsArticleAdapter(List<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_recycler_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Article article = articles.get(position);
        Picasso.get().load(article.getUrlToImage()).
                resize(200, 200).centerCrop()
                .into(viewHolder.articleImageView);
        viewHolder.sourceTextView.setText(article.getSource().getName());
        viewHolder.titleTextView.setText(article.getTitle());
    }

    @Override
    public int getItemCount() {
        return articles == null ? 0 : articles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.source)
        TextView sourceTextView;

        @BindView(R.id.title)
        TextView titleTextView;

        @BindView(R.id.article_image)
        ImageView articleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
