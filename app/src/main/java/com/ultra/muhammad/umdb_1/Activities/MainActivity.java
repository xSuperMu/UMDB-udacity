package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ultra.muhammad.umdb_1.Adapters.MoviesAdapter;
import com.ultra.muhammad.umdb_1.Adapters.OfflineMoviesAdapter;
import com.ultra.muhammad.umdb_1.Database.AppDatabase;
import com.ultra.muhammad.umdb_1.Database.MovieEntry;
import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.MovieUtils.RecyclerItemClickListener;
import com.ultra.muhammad.umdb_1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ultra.muhammad.umdb_1.Activities.SplashActivity.mMostPopularMoviesList;
import static com.ultra.muhammad.umdb_1.Activities.SplashActivity.mTopRatedMoviesList;
import static com.ultra.muhammad.umdb_1.Network.NetworkUtils.isNetworkConnected;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, OfflineMoviesAdapter.ItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.recycler_view_top_rated)
    RecyclerView mTopRatedMoviesRecycler;
    @BindView(R.id.recycler_view_most_popular)
    RecyclerView mMostPopularMoviesRecycler;
    @BindView(R.id.top_rated_layout)
    ConstraintLayout mMostPopularMoviesLayout;
    @BindView(R.id.most_popular_layout)
    ConstraintLayout mTopRatedMoviesLayout;
    @BindView(R.id.favorite_layout)
    ConstraintLayout mFavoriteMoviesLayout;
    @BindView(R.id.recycler_view_favorite)
    RecyclerView mFavoriteMoviesRecycler;

    private ActionBar actionBar;
    private AppDatabase mDb;
    private OfflineMoviesAdapter mOfflineMoviesAdapter;
    private boolean isOffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate() has been instantiated");

        actionBar = getSupportActionBar();
        // COMPLETED (2) Check connectivity, if offline show the favorite recycler only
        // COMPLETED (3) change the activity name to "Offline mode"
        // COMPLETED (4) Read the movies from the Room database

        if (isNetworkConnected(getApplicationContext())) {
            Log.d(TAG, "There is an Internet connection available!");
            isOffline = false;
            prepareRecyclerViews();
            loadMoviesData();
        } else {
            Log.d(TAG, "There is no an Internet connection available!");
            if (actionBar != null)
                actionBar.setTitle(R.string.umdb_offline_mode);
            isOffline = true;
            mTopRatedMoviesLayout.setVisibility(View.GONE);
            mMostPopularMoviesLayout.setVisibility(View.GONE);
            mFavoriteMoviesLayout.setVisibility(View.VISIBLE);

        }

        mOfflineMoviesAdapter = new OfflineMoviesAdapter(getApplicationContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        mFavoriteMoviesRecycler.setLayoutManager(layoutManager);
        mFavoriteMoviesRecycler.setHasFixedSize(true);
        mFavoriteMoviesRecycler.setAdapter(mOfflineMoviesAdapter);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

        Log.d(TAG, "The app will try to create the Database");
        mDb = AppDatabase.getsInstance(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() has been instantiated");
        mOfflineMoviesAdapter.setMovieEntries(mDb.movieDao().loadAllMovies());
    }

    private void prepareRecyclerViews() {
        Log.d(TAG, "prepareRecyclerViews() has been instantiated");

        GridLayoutManager gridLayoutManagerTopRated =
                new GridLayoutManager(this, 3);
        mTopRatedMoviesRecycler.setLayoutManager(gridLayoutManagerTopRated);
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
        GridLayoutManager gridLayoutManagerMostPopular =
                new GridLayoutManager(this, 3);
        mMostPopularMoviesRecycler.setLayoutManager(gridLayoutManagerMostPopular);
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

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String s = preferences.getString(getString(R.string.pref_movies_key), "");
        if (s.equals(getString(R.string.pref_enable_top_rated_movies_key))) {
            mTopRatedMoviesLayout.setVisibility(View.GONE);
            mMostPopularMoviesLayout.setVisibility(View.VISIBLE);
        } else if (s.equals(getString(R.string.pref_enable_most_popular_movies_key))) {
            mMostPopularMoviesLayout.setVisibility(View.GONE);
            mTopRatedMoviesLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // COMPLETED (3): Check connectivity state, if offline hide the main menu
        if (isOffline) {
            return false;
        }

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

        if (key.equals(getString(R.string.pref_movies_key))) {
            String s = sharedPreferences.getString(key, getString(R.string.pref_enable_most_popular_movies_key));
            if (s.equals(getString(R.string.pref_enable_top_rated_movies_key))) {
                mTopRatedMoviesLayout.setVisibility(View.GONE);
                mMostPopularMoviesLayout.setVisibility(View.VISIBLE);
            } else if (s.equals(getString(R.string.pref_enable_most_popular_movies_key))) {
                mMostPopularMoviesLayout.setVisibility(View.GONE);
                mTopRatedMoviesLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onItemClickListener(MovieEntry movieEntry) {
        Toast.makeText(this, "Movie Clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MovieOfflineDetailsActivity.class);
        intent.putExtra("selected_favorite_movie", movieEntry);
        startActivity(intent);
    }
}
