
package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieTrailer implements Serializable {

    @SerializedName("id")
    @Expose
    private Long mId;
    @SerializedName("results")
    @Expose
    private List<TrailerResult> mTrailerResults;

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public List<TrailerResult> getResults() {
        return mTrailerResults;
    }

    public void setResults(List<TrailerResult> trailerResults) {
        mTrailerResults = trailerResults;
    }

}
