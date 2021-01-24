package com.squarebaot.newsapp.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squarebaot.newsapp.R;
import com.squarebaot.newsapp.adapter.SourceListAdapter;
import com.squarebaot.newsapp.network.ServiceBuilder;
import com.squarebaot.newsapp.network.response.Source;
import com.squarebaot.newsapp.network.services.FetchArticleSource;
import com.squarebaot.newsapp.presenter.SourcePresenter;
import com.squarebaot.newsapp.view.NewsDashboardView;
import com.squarebaot.newsapp.view.SourceListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.squarebaot.newsapp.R.id.source_list;

public class SourceFilterBottomSheetDialogFragment extends BottomSheetDialogFragment implements SourceListView {

    @BindView(source_list)
    RecyclerView sourceRecyclerView;

    private NewsDashboardView newsDashboardView;

    public SourceFilterBottomSheetDialogFragment(NewsDashboardView newsDashboardView) {
        this.newsDashboardView = newsDashboardView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.dialog_sources, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SourcePresenter sourcePresenter = new SourcePresenter(ServiceBuilder
                .build(FetchArticleSource.class), this);
        sourcePresenter.fetchSource();
    }

    @Override
    public void onSourceFetchSuccessful(List<Source> sources) {
        SourceListAdapter sourceListAdapter = new SourceListAdapter(sources);
        sourceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sourceRecyclerView.setAdapter(sourceListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        newsDashboardView.updateListBySources();
    }

    @Override
    public void onSourceFetchFail() {
        Toast.makeText(getContext(), "Sources not found", Toast.LENGTH_LONG).show();
    }

}
