package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ultra.muhammad.umdb_1.Adapters.MovieReviewsAdapter;
import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.Models.MovieDetails;
import com.ultra.muhammad.umdb_1.Models.MovieReviews;
import com.ultra.muhammad.umdb_1.Models.MovieTrailer;
import com.ultra.muhammad.umdb_1.Models.ReviewResult;
import com.ultra.muhammad.umdb_1.MovieUtils.Utils;
import com.ultra.muhammad.umdb_1.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserReviewsActivity extends AppCompatActivity {
    private static final String TAG = UserReviewsActivity.class.getSimpleName();
    String movieId, numberOfReviews;
    Movie movie;
    Map<String, Object> options = new HashMap<>();
    boolean bool, f1 = false, f2 = false, f3 = false;
    ProgressBar mLoadingProgressBar;
    RecyclerView mReviewsRecycler;
    TextView mNumberOfReviews;
    ConstraintLayout mReviewsLayout;
    List<String> mReviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reviews);
        Log.d(TAG, "onCreate() has been instantiated");
        setUpWidgets();
        showProgressBar();

        Intent intent = getIntent();
        if (intent != null)
            if (intent.hasExtra("movie_id")) {
                movieId = intent.getStringExtra("movie_id");
                getMovieReviewsFromInternet();
            }
    }

    private void getMovieReviewsFromInternet() {
        Log.d(TAG, "getMovieReviewsFromInternet() has been instantiated");
        options.put("api_key", getString(R.string.api_key));
        options.put("page", "1");
        Utils.getMovieReviews(movieId, options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {

            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {

            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {

            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {
                Log.i(TAG, movieReviews.toString());
                if (movieReviews.getResults().size() > 0)
                    for (ReviewResult review : movieReviews.getResults()) {
                        mReviews.add(review.getContent());
                        Log.i(TAG, review.getContent());
                    }
                f1 = true;
                hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "No Reviews Found");
            }
        });
//        options.put("page", "2");
//        Utils.getMovieReviews(movieId, options, new Utils.retrofitCallback() {
//            @Override
//            public void onSuccess(List<Movie> movies) {
//
//            }
//
//            @Override
//            public void onSuccess(MovieDetails movieDetails) {
//
//            }
//
//            @Override
//            public void onSuccess(MovieTrailer movieTrailer) {
//
//            }
//
//            @Override
//            public void onSuccess(MovieReviews movieReviews) {
//                Log.i(TAG, movieReviews.getResults().toString());
//                if (movieReviews.getResults().size() > 0)
//                    if (movieReviews.getResults().size() > 0)
//                        for (ReviewResult review : movieReviews.getResults()) {
//                            mReviews.add(review.getContent());
//                        }
//                f2 = true;
//                hideProgressBar();
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.e(TAG, "No Reviews Found");
//            }
//        });
//        options.put("page", "3");
//        Utils.getMovieReviews(movieId, options, new Utils.retrofitCallback() {
//            @Override
//            public void onSuccess(List<Movie> movies) {
//
//            }
//
//            @Override
//            public void onSuccess(MovieDetails movieDetails) {
//
//            }
//
//            @Override
//            public void onSuccess(MovieTrailer movieTrailer) {
//
//            }
//
//            @Override
//            public void onSuccess(MovieReviews movieReviews) {
//                Log.i(TAG, movieReviews.getResults().toString());
//                if (movieReviews.getResults().size() > 0)
//                    if (movieReviews.getResults().size() > 0)
//                        for (ReviewResult review : movieReviews.getResults()) {
//                            mReviews.add(review.getContent());
//                        }
//                f3 = true;
//                hideProgressBar();
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.e(TAG, "No Reviews Found");
//            }
//        });
    }

    private void setUpWidgets() {
        Log.d(TAG, "setUpWidgets() has been instantiated");
        mNumberOfReviews = findViewById(R.id.reviews_number_tv);
        mReviewsLayout = findViewById(R.id.total_reviews_layout);
        mLoadingProgressBar = findViewById(R.id.reviews_progress_bar);
        mReviewsRecycler = findViewById(R.id.reviews_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mReviewsRecycler.setLayoutManager(layoutManager);
        mReviewsRecycler.setHasFixedSize(true);
    }

    private void showProgressBar() {
        Log.d(TAG, "showProgressBar() has been instantiated");
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        mReviewsLayout.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
//        bool = f1 && f2 && f3;
        bool = f1;
        if (bool) {
            Log.d(TAG, "hideProgressBar() has been Activated");
            MovieReviewsAdapter adapter = new MovieReviewsAdapter(getApplicationContext(), mReviews);
            mReviewsRecycler.setAdapter(adapter);
            numberOfReviews = String.valueOf(mReviews.size());
            Log.i(TAG, "Number Of Reviews --> " + numberOfReviews);
            mNumberOfReviews.setText(numberOfReviews + " " + getString(R.string.reviews_s));
            mLoadingProgressBar.setVisibility(View.GONE);
            mReviewsLayout.setVisibility(View.VISIBLE);
        }
    }
}
