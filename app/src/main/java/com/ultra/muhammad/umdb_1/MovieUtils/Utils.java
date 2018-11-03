package com.ultra.muhammad.umdb_1.MovieUtils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.Models.MovieDetails;
import com.ultra.muhammad.umdb_1.Models.MovieResponse;
import com.ultra.muhammad.umdb_1.Models.MovieReviews;
import com.ultra.muhammad.umdb_1.Models.MovieTrailer;
import com.ultra.muhammad.umdb_1.Network.MoviesClient;
import com.ultra.muhammad.umdb_1.Network.RetrofitClientInstance;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class Utils {
    private static final String TAG = "Utils";

    public static void getMostPopularMovies(Map<String, Object> options,
                                            final retrofitCallback callback) {
        MoviesClient service = RetrofitClientInstance.getRetrofitInstance().create(MoviesClient.class);
        Call<MovieResponse> call = service.getMostPopularMovies(options);
        Log.i(TAG, call.request().url().toString());
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    List<Movie> movies = response.body().getResults();
                    callback.onSuccess(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure() has been instantiated");
                callback.onFailure(t);
            }
        });
    }

    public static void getTopRatedMovies(Map<String, Object> options,
                                         final retrofitCallback callback) {
        MoviesClient service = RetrofitClientInstance.getRetrofitInstance().create(MoviesClient.class);
        Call<MovieResponse> call = service.getTopRatedMovies(options);
        Log.i(TAG, call.request().url().toString());
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    List<Movie> movies = response.body().getResults();
                    callback.onSuccess(movies);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure() has been instantiated");
                callback.onFailure(t);
            }
        });
    }

    public static void getMovieDetails(String movieId, Map<String, Object> options,
                                       final retrofitCallback callback) {
        MoviesClient service = RetrofitClientInstance.getRetrofitInstance().create(MoviesClient.class);
        Call<MovieDetails> call = service.getMovieDetails(movieId, options);
        Log.i(TAG, call.request().url().toString());
        call.enqueue(new Callback<MovieDetails>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetails> call, @NonNull Response<MovieDetails> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    MovieDetails movieDetails = response.body();
                    callback.onSuccess(movieDetails);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetails> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure() has been instantiated");
                callback.onFailure(t);
            }
        });
    }

    public static void getMovieTrailer(String movieId, Map<String, Object> options,
                                       final retrofitCallback callback) {
        MoviesClient service = RetrofitClientInstance.getRetrofitInstance().create(MoviesClient.class);
        Call<MovieTrailer> call = service.getMovieTrailer(movieId, options);
        Log.i(TAG, call.request().url().toString());
        call.enqueue(new Callback<MovieTrailer>() {
            @Override
            public void onResponse(@NonNull Call<MovieTrailer> call, @NonNull Response<MovieTrailer> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    MovieTrailer movieTrailer = response.body();
                    callback.onSuccess(movieTrailer);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieTrailer> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure() has been instantiated");
                callback.onFailure(t);
            }
        });
    }

    public static void getMovieReviews(String movieId, Map<String, Object> options,
                                       final retrofitCallback callback) {
        MoviesClient service = RetrofitClientInstance.getRetrofitInstance().create(MoviesClient.class);
        Call<MovieReviews> call = service.getMovieReviews(movieId, options);
        Log.i(TAG, call.request().url().toString());
        call.enqueue(new Callback<MovieReviews>() {
            @Override
            public void onResponse(@NonNull Call<MovieReviews> call, @NonNull Response<MovieReviews> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: " + response.body());
                    MovieReviews movieReviews = response.body();
                    callback.onSuccess(movieReviews);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieReviews> call, @NonNull Throwable t) {
                Log.e(TAG, "onFailure() has been instantiated");
                callback.onFailure(t);
            }
        });
    }

    public interface retrofitCallback {
        void onSuccess(List<Movie> movies);

        void onSuccess(MovieDetails movieDetails);

        void onSuccess(MovieTrailer movieTrailer);

        void onSuccess(MovieReviews movieReviews);

        void onFailure(Throwable t);
    }
}
