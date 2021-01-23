package com.squarebaot.newsapp.model;

import java.io.Serializable;

public class Source implements Serializable {
    private String id;
    private String name;

    public Source() {
    }

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

