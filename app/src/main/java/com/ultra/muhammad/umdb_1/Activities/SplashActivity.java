package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.Models.MovieDetails;
import com.ultra.muhammad.umdb_1.Models.MovieReviews;
import com.ultra.muhammad.umdb_1.Models.MovieTrailer;
import com.ultra.muhammad.umdb_1.MovieUtils.Utils;
import com.ultra.muhammad.umdb_1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ultra.muhammad.umdb_1.Network.NetworkUtils.isNetworkConnected;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final String LANGUAGE = "language";
    private static final String PAGE = "page";
    private static final String API_KEY = "api_key";
    private static final String LANGUAGE_VALUE = "en-US";
    public static List<Movie> mMostPopularMoviesList = new ArrayList<>();
    public static List<Movie> mTopRatedMoviesList = new ArrayList<>();
    Map<String, Object> options = new HashMap<>();
    Map<String, Object> UpComingOptions = new HashMap<>();
    boolean bool, f1 = false, f2 = false, f3 = false, f4 = false, f5 = false, f6 = false, f7 = false, f8 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() has been instantiated");

        // COMPLETED (1) Check connectivity
        if (isNetworkConnected(getApplicationContext()))
            getData();
        else
            moveToMainOffline();
    }

    private void getData() {
        Log.wtf(TAG, "getData(): has been instantiated");

        bool = false;

        options.put(PAGE, "1");
        options.put(LANGUAGE, LANGUAGE_VALUE);
        options.put(API_KEY, getString(R.string.api_key));
        Utils.getMostPopularMovies(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mMostPopularMoviesList = movies;
                f1 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        options.put(PAGE, "2");
        Utils.getMostPopularMovies(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mMostPopularMoviesList.addAll(movies);
                f2 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        options.put(PAGE, "3");
        Utils.getMostPopularMovies(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mMostPopularMoviesList.addAll(movies);
                f3 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        options.put(PAGE, "4");
        Utils.getMostPopularMovies(options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mMostPopularMoviesList.addAll(movies);
                f4 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        UpComingOptions.put(PAGE, "2");
        UpComingOptions.put(LANGUAGE, LANGUAGE_VALUE);
        UpComingOptions.put(API_KEY, getString(R.string.api_key));
        Utils.getTopRatedMovies(UpComingOptions, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mTopRatedMoviesList = movies;
                f5 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        UpComingOptions.put(PAGE, "3");
        Utils.getTopRatedMovies(UpComingOptions, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mTopRatedMoviesList.addAll(movies);
                f6 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        UpComingOptions.put(PAGE, "4");
        Utils.getTopRatedMovies(UpComingOptions, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mTopRatedMoviesList.addAll(movies);
                f7 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        UpComingOptions.put(PAGE, "5");
        Utils.getTopRatedMovies(UpComingOptions, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                mTopRatedMoviesList.addAll(movies);
                f8 = true;
                moveToMain();
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void moveToMain() {
        Log.wtf(TAG, "moveToMain() has been instantiated");
        bool = f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8;
        if (bool) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void moveToMainOffline() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
