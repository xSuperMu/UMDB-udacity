package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.ultra.muhammad.umdb_1.Adapters.MovieTrailerAdapter;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieTrailersActivity extends AppCompatActivity {
    private static final String TAG = MovieTrailersActivity.class.getSimpleName();
    String movieId;
    int numberOfTrailers;
    Movie movie;
    Map<String, Object> options = new HashMap<>();
    boolean bool, f1 = false;
    @BindView(R.id.trailer_loading_progress_bar)
    ProgressBar mLoadingProgressBar;
    @BindView(R.id.trailers_recycler_view)
    RecyclerView mTrailersRecycler;
    List<String> mTrailers = new ArrayList<>();

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
        options.put("api_key", getString(R.string.api_key));
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
