package com.ultra.muhammad.umdb_1.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieResponse implements Serializable {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_pages")
    @Expose
    private int total_pages;
    @SerializedName("results")
    @Expose
    private List<Movie> results;
    @SerializedName("total_results")
    @Expose
    private int total_results;

    public MovieResponse(int page, int total_pages, List<Movie> results, int total_results) {
        this.page = page;
        this.total_pages = total_pages;
        this.results = results;
        this.total_results = total_results;
    }

    public MovieResponse() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "MovieResponse{" +
                        "page = '" + page + '\'' +
                        ",total_pages = '" + total_pages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results = '" + total_results + '\'' +
                        "}";
    }
}