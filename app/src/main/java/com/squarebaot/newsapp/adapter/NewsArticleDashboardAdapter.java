package com.squarebaot.newsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarebaot.newsapp.R;
import com.squarebaot.newsapp.model.Article;
import com.squarebaot.newsapp.view.NewsDashboardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsArticleDashboardAdapter extends RecyclerView.Adapter<NewsArticleDashboardAdapter.ViewHolder> {
    private List<Article> articles;
    private NewsDashboardView newsDashboardView;

    public NewsArticleDashboardAdapter(List<Article> articles, NewsDashboardView newsDashboardView) {
        this.articles = articles;
        this.newsDashboardView = newsDashboardView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.news_recycler_list_item, parent, false);
        view.setOnClickListener((v) -> newsDashboardView.onArticleSelect(v));
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

    public void updateDataSet(List<Article> filteredList) {
        articles = filteredList;
        notifyDataSetChanged();
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
