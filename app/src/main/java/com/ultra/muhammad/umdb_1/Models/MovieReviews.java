package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieReviews implements Serializable {

    @SerializedName("id")
    @Expose
    private Long mId;
    @SerializedName("page")
    @Expose
    private Long mPage;
    @SerializedName("results")
    @Expose
    private List<ReviewResult> mReviewResults;
    @SerializedName("total_pages")
    @Expose
    private Long mTotalPages;
    @SerializedName("total_results")
    @Expose
    private Long mTotalResults;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getPage() {
        return mPage;
    }

    public void setPage(Long page) {
        mPage = page;
    }

    public List<ReviewResult> getResults() {
        return mReviewResults;
    }

    public void setResults(List<ReviewResult> reviewResults) {
        mReviewResults = reviewResults;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

    public Long getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Long totalResults) {
        mTotalResults = totalResults;
    }

}
