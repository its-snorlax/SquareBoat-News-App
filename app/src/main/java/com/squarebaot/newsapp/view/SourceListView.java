package com.squarebaot.newsapp.view;

import com.squarebaot.newsapp.network.response.Source;

import java.util.List;

public interface SourceListView {
    void onSourceFetchSuccessful(List<Source> sources);

    void onSourceFetchFail();
}
