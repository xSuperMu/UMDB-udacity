package com.ultra.muhammad.umdb_1.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovieDetails implements Serializable {
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;
    @SerializedName("belongs_to_collection")
    @Expose
    private BelongsToCollection belongs_to_collection;
    @SerializedName("budget")
    @Expose
    private Long budget;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("imdb_id")
    @Expose
    private String imdb_id;
    @SerializedName("original_language")
    @Expose
    private String original_language;
    @SerializedName("original_title")
    @Expose
    private String original_title;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String poster_path;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> production_companies;
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountry> production_countries;
    @SerializedName("release_date")
    @Expose
    private String release_date;
    @SerializedName("revenue")
    @Expose
    private Long revenue;
    @SerializedName("runtime")
    @Expose
    private Long runtime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> spoken_languages;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double vote_average;
    @SerializedName("vote_count")
    @Expose
    private Long vote_count;

    public MovieDetails() {
    }

    public MovieDetails(Boolean adult, String backdrop_path, BelongsToCollection belongs_to_collection, Long budget, List<Genre> genres, String homepage, Long id, String imdb_id, String original_language, String original_title, String overview, Double popularity, String poster_path, List<ProductionCompany> production_companies, List<ProductionCountry> production_countries, String release_date, Long revenue, Long runtime, List<SpokenLanguage> spoken_languages, String status, String tagline, String title, Boolean video, Double vote_average, Long vote_count) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.belongs_to_collection = belongs_to_collection;
        this.budget = budget;
        this.genres = genres;
        this.homepage = homepage;
        this.id = id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.production_companies = production_companies;
        this.production_countries = production_countries;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spoken_languages = spoken_languages;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "adult=" + adult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", belongs_to_collection=" + belongs_to_collection +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", imdb_id='" + imdb_id + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + poster_path + '\'' +
                ", production_companies=" + production_companies +
                ", production_countries=" + production_countries +
                ", release_date='" + release_date + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", spoken_languages=" + spoken_languages +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                '}';
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        backdrop_path = backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongs_to_collection;
    }

    public void setBelongsToCollection(BelongsToCollection belongsToCollection) {
        belongs_to_collection = belongsToCollection;
    }

    public Long getBudget() {
        return budget;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdb_id;
    }

    public void setImdbId(String imdbId) {
        imdb_id = imdbId;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        original_language = originalLanguage;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String originalTitle) {
        original_title = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        poster_path = posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return production_companies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        production_companies = productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return production_countries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        production_countries = productionCountries;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        release_date = releaseDate;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spoken_languages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        spoken_languages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(Double voteAverage) {
        vote_average = voteAverage;
    }

    public Long getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(Long voteCount) {
        vote_count = voteCount;
    }

}
