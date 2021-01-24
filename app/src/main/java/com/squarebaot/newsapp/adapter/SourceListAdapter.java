package com.squarebaot.newsapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squarebaot.newsapp.R;
import com.squarebaot.newsapp.network.response.Source;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.squarebaot.newsapp.R.id.source_name;

public class SourceListAdapter extends RecyclerView.Adapter<SourceListAdapter.ViewHolder> {
    private List<Source> sources;

    public SourceListAdapter(List<Source> sources) {
        this.sources = sources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Source currentSource = sources.get(position);
        holder.sourceNameCheckBox.setText(currentSource.getName());
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(source_name)
        CheckBox sourceNameCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
