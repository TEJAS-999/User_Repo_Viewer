package com.example.assingment_github_api.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import androidx.room.Ignore;
import androidx.room.PrimaryKey;


public class Repository {

    private String name;

    private String description;

    private String html_url;

    public Repository(String name, String description, String html_url) {
        this.name = name;
        this.description = description;
        this.html_url = html_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}

