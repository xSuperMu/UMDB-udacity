package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BelongsToCollection implements Serializable {

    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    public BelongsToCollection() {
    }

    public BelongsToCollection(String backdrop_path, Long id, String name, String poster_path) {
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
    }

    @Override
    public String toString() {
        return "BelongsToCollection{" +
                "backdrop_path='" + backdrop_path + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", poster_path='" + poster_path + '\'' +
                '}';
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        backdrop_path = backdropPath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        poster_path = posterPath;
    }

}
