package com.ultra.muhammad.umdb_1.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("original_language")
    @Expose
    private String original_language;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("video")
    @Expose
    private boolean video;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genre_ids;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("release_date")
    @Expose
    private String release_date;
    @SerializedName("popularity")
    @Expose
    private double popularity;
    @SerializedName("vote_average")
    @Expose
    private double vote_average;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("adult")
    @Expose
    private boolean adult;
    @SerializedName("vote_count")
    @Expose
    private int vote_count;


    public Movie() {
    }

    public Movie(String overview, String original_language, String original_title, boolean video, String title, List<Integer> genre_ids, String poster_path, String backdrop_path, String release_date, double popularity, double vote_average, int id, boolean adult, int vote_count) {
        this.overview = overview;
        this.original_language = original_language;
        this.original_title = original_title;
        this.video = video;
        this.title = title;
        this.genre_ids = genre_ids;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.id = id;
        this.adult = adult;
        this.vote_count = vote_count;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getGenreIds() {
        return genre_ids;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genre_ids = genreIds;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(double voteAverage) {
        this.vote_average = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = voteCount;
    }

    @NonNull
    @Override
    public String toString() {
        return
                "Movie{" +
                        "overview = '" + overview + '\'' +
                        ",original_language = '" + original_language + '\'' +
                        ",original_title = '" + original_title + '\'' +
                        ",video = '" + video + '\'' +
                        ",title = '" + title + '\'' +
                        ",genre_ids = '" + genre_ids + '\'' +
                        ",poster_path = '" + poster_path + '\'' +
                        ",backdrop_path = '" + backdrop_path + '\'' +
                        ",release_date = '" + release_date + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",vote_average = '" + vote_average + '\'' +
                        ",id = '" + id + '\'' +
                        ",adult = '" + adult + '\'' +
                        ",vote_count = '" + vote_count + '\'' +
                        "}";
    }
}