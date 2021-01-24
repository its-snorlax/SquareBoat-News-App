package com.squarebaot.newsapp.network.response;

import java.util.List;

public class SourceResponse {
    private String status;
    private List<Source> sources;

    public SourceResponse() {
    }

    public SourceResponse(String status, List<Source> sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public List<Source> getSources() {
        return sources;
    }
}
