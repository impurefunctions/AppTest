package com.kesegotumisang.apptest.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.kesegotumisang.apptest.BR;

import java.io.Serializable;

public class Article extends BaseObservable implements Serializable {
    @SerializedName("title")
    @Bindable
    private final String title;

    @SerializedName("byline")
    @Bindable
    private final String byline;

    @SerializedName("published_date")
    @Bindable
    private final String published_date;

    public Article(String byline, String title, String published_date) {
        this.title = title;
        this.byline = byline;
        this.published_date = published_date;

        notifyPropertyChanged(BR.title);
        notifyPropertyChanged(BR.byline);
        notifyPropertyChanged(BR.published_date);
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }
}
