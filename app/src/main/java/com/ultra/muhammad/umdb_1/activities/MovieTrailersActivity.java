package com.ultra.muhammad.umdb_1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ultra.muhammad.umdb_1.R;
import com.ultra.muhammad.umdb_1.adapters.MovieTrailerAdapter;
import com.ultra.muhammad.umdb_1.models.Movie;
import com.ultra.muhammad.umdb_1.models.MovieDetails;
import com.ultra.muhammad.umdb_1.models.MovieReviews;
import com.ultra.muhammad.umdb_1.models.MovieTrailer;
import com.ultra.muhammad.umdb_1.movie_utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ultra.muhammad.umdb_1.movie_utils.Constants.UMDB_API_KEY;

public class MovieTrailersActivity extends AppCompatActivity {
    private static final String TAG = MovieTrailersActivity.class.getSimpleName();
    private final Map<String, Object> options = new HashMap<>();
    private final List<String> mTrailers = new ArrayList<>();
    private String movieId;
    private int numberOfTrailers;
    @BindView(R.id.trailer_loading_progress_bar)
    ProgressBar mLoadingProgressBar;
    @BindView(R.id.trailers_recycler_view)
    RecyclerView mTrailersRecycler;
    private boolean bool, f1 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailers);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate() has been instantiated");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mTrailersRecycler.setLayoutManager(layoutManager);
        mTrailersRecycler.setHasFixedSize(true);

        showProgressBar();
        Intent intent = getIntent();
        if (intent != null)
            if (intent.hasExtra("movie_id")) {
                movieId = intent.getStringExtra("movie_id");
                loadTrailersFromInternet();
            }
    }

    private void loadTrailersFromInternet() {
        Log.d(TAG, "loadTrailersFromInternet() has been instantiated");
        options.put("api_key", UMDB_API_KEY);
        Utils.getMovieTrailer(movieId, options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {
                // Do nothing!
            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {
                // Do nothing!
            }

            @Override
            public void onSuccess(MovieTrailer movieTrailer) {
                Log.i(TAG, movieTrailer.toString());

                if (movieTrailer.getResults().size() > 0) {
                    numberOfTrailers = movieTrailer.getResults().size();
                    for (int i = 0; i < numberOfTrailers; i++) {
                        Log.i(TAG, movieTrailer.getResults().get(i).getKey());
                        mTrailers.add(movieTrailer.getResults().get(i).getKey());
                    }
                }
                f1 = true;
                hideProgressBar();
            }

            @Override
            public void onSuccess(MovieReviews movieReviews) {
                // Do nothing!
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "No Trailers Found");
            }
        });
    }

    private void showProgressBar() {
        Log.d(TAG, "showProgressBar() has been instantiated");
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        mTrailersRecycler.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        bool = f1;
        if (bool) {
            Log.d(TAG, "hideProgressBar() has been Activated");
            MovieTrailerAdapter adapter = new MovieTrailerAdapter(getApplicationContext(), mTrailers);
            mTrailersRecycler.setAdapter(adapter);
            numberOfTrailers = mTrailers.size();
            Log.i(TAG, "Number Of Trailers --> " + numberOfTrailers);
            mLoadingProgressBar.setVisibility(View.GONE);
            mTrailersRecycler.setVisibility(View.VISIBLE);
        }
    }


}
