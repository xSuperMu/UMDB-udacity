package com.ultra.muhammad.umdb_1.activities;

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

import com.ultra.muhammad.umdb_1.R;
import com.ultra.muhammad.umdb_1.adapters.MovieReviewsAdapter;
import com.ultra.muhammad.umdb_1.models.Movie;
import com.ultra.muhammad.umdb_1.models.MovieDetails;
import com.ultra.muhammad.umdb_1.models.MovieReviews;
import com.ultra.muhammad.umdb_1.models.MovieTrailer;
import com.ultra.muhammad.umdb_1.models.ReviewResult;
import com.ultra.muhammad.umdb_1.movie_utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ultra.muhammad.umdb_1.movie_utils.Constants.UMDB_API_KEY;

public class UserReviewsActivity extends AppCompatActivity {
    private static final String TAG = UserReviewsActivity.class.getSimpleName();
    private final Map<String, Object> options = new HashMap<>();
    private final List<String> mReviews = new ArrayList<>();
    private String movieId;
    private boolean f1 = false;
    private ProgressBar mLoadingProgressBar;
    private RecyclerView mReviewsRecycler;
    private TextView mNumberOfReviews;
    private ConstraintLayout mReviewsLayout;

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
        options.put("api_key", UMDB_API_KEY);
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
        boolean bool = f1;
        if (bool) {
            Log.d(TAG, "hideProgressBar() has been Activated");
            MovieReviewsAdapter adapter = new MovieReviewsAdapter(getApplicationContext(), mReviews);
            mReviewsRecycler.setAdapter(adapter);
            String numberOfReviews = String.valueOf(mReviews.size());
            Log.i(TAG, "Number Of Reviews --> " + numberOfReviews);
            String numOfReviewsString = numberOfReviews + getString(R.string.reviews_s);
            mNumberOfReviews.setText(numOfReviewsString);
            mLoadingProgressBar.setVisibility(View.GONE);
            mReviewsLayout.setVisibility(View.VISIBLE);
        }
    }
}
