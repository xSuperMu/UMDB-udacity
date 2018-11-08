package com.ultra.muhammad.umdb_1.network;

import com.ultra.muhammad.umdb_1.models.MovieDetails;
import com.ultra.muhammad.umdb_1.models.MovieResponse;
import com.ultra.muhammad.umdb_1.models.MovieReviews;
import com.ultra.muhammad.umdb_1.models.MovieTrailer;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MoviesClient {

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@QueryMap Map<String, Object> options);

    @GET("movie/popular")
    Call<MovieResponse> getMostPopularMovies(@QueryMap Map<String, Object> options);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") String id, @QueryMap Map<String, Object> options);

    @GET("movie/{movie_id}/videos")
    Call<MovieTrailer> getMovieTrailer(@Path("movie_id") String id, @QueryMap Map<String, Object> options);

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviews> getMovieReviews(@Path("movie_id") String id, @QueryMap Map<String, Object> options);

}
