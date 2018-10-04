package com.ultra.muhammad.umdb_1.Network;

import com.ultra.muhammad.umdb_1.Models.MovieDetails;
import com.ultra.muhammad.umdb_1.Models.MovieResponse;

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
}
