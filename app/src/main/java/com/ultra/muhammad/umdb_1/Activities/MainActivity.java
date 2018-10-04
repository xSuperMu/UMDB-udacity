package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ultra.muhammad.umdb_1.Adapters.MoviesAdapter;
import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.MovieUtils.RecyclerItemClickListener;
import com.ultra.muhammad.umdb_1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ultra.muhammad.umdb_1.Activities.SplashActivity.mMostPopularMoviesList;
import static com.ultra.muhammad.umdb_1.Activities.SplashActivity.mTopRatedMoviesList;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recycler_view_top_rated)
    RecyclerView mTopRatedMoviesRecycler;
    @BindView(R.id.recycler_view_most_popular)
    RecyclerView mMostPopularMoviesRecycler;
    @BindView(R.id.top_rated_layout)
    ConstraintLayout mMostPopularMoviesLayout;
    @BindView(R.id.most_popular_layout)
    ConstraintLayout mTopRatedMoviesLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate() has been instantiated");
        prepareRecyclerViews();

        loadMoviesData();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    private void prepareRecyclerViews() {
        Log.d(TAG, "prepareRecyclerViews() has been instantiated");

        LinearLayoutManager mLinearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mTopRatedMoviesRecycler.setLayoutManager(mLinearLayoutManager);
        mTopRatedMoviesRecycler.setDrawingCacheEnabled(true);
        mTopRatedMoviesRecycler.setHasFixedSize(true);
        mTopRatedMoviesRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
        mTopRatedMoviesRecycler.setItemViewCacheSize(200);
        mTopRatedMoviesRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, mTopRatedMoviesRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = mTopRatedMoviesList.get(position);
                Log.i(TAG, "Selected Movie --> " + movie.toString());
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("selected_movie", movie);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        LinearLayoutManager mUpComingMovieLinearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mMostPopularMoviesRecycler.setLayoutManager(mUpComingMovieLinearLayoutManager);
        mMostPopularMoviesRecycler.setDrawingCacheEnabled(true);
        mMostPopularMoviesRecycler.setHasFixedSize(true);
        mMostPopularMoviesRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
        mMostPopularMoviesRecycler.setItemViewCacheSize(200);
        mMostPopularMoviesRecycler.addOnItemTouchListener(new RecyclerItemClickListener(this, mMostPopularMoviesRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Movie movie = mMostPopularMoviesList.get(position);
                Log.i(TAG, "Selected Movie --> " + movie.toString());
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("selected_upcoming_movie", movie);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    private void loadMoviesData() {
        Log.d(TAG, "loadMoviesData() has been instantiated");
        MoviesAdapter topRatedMoviesAdapter = new MoviesAdapter(this, mTopRatedMoviesList);
        mTopRatedMoviesRecycler.setAdapter(topRatedMoviesAdapter);

        MoviesAdapter mostPopularMovieAdapter = new MoviesAdapter(this, mMostPopularMoviesList);
        mMostPopularMoviesRecycler.setAdapter(mostPopularMovieAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.item_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_enable_top_rated_movies_key))) {
            if (!sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.show_most_popular_by_default)))
                mMostPopularMoviesLayout.setVisibility(View.GONE);
            else
                mMostPopularMoviesLayout.setVisibility(View.VISIBLE);
        } else if (key.equals(getString(R.string.pref_enable_most_popular_movies_key))) {
            if (!sharedPreferences.getBoolean(key, getResources().getBoolean(R.bool.show_top_rated_by_default)))
                mTopRatedMoviesLayout.setVisibility(View.GONE);
            else
                mTopRatedMoviesLayout.setVisibility(View.VISIBLE);
        }
    }
}
