package com.ultra.muhammad.umdb_1.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReviewResult implements Serializable {

    @SerializedName("author")
    @Expose
    private String mAuthor;
    @SerializedName("content")
    @Expose
    private String mContent;
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("url")
    @Expose
    private String mUrl;

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
