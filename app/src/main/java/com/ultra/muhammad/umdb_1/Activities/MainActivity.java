package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ultra.muhammad.umdb_1.Adapters.MoviesAdapter;
import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.MovieUtils.RecyclerItemClickListener;
import com.ultra.muhammad.umdb_1.R;

import static com.ultra.muhammad.umdb_1.Activities.SplashActivity.mMostPopularMoviesList;
import static com.ultra.muhammad.umdb_1.Activities.SplashActivity.mTopRatedMoviesList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mTopRatedMoviesRecycler;
    private RecyclerView mMostPopularMoviesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() has been instantiated");

        mTopRatedMoviesRecycler = findViewById(R.id.recycler_view_top_rated);
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

        mMostPopularMoviesRecycler = findViewById(R.id.recycler_view_most_popular);
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

        loadMoviesData();
    }

    private void loadMoviesData() {
        Log.d(TAG, "loadMoviesData() has been instantiated");
        MoviesAdapter topRatedMoviesAdapter = new MoviesAdapter(this, mTopRatedMoviesList);
        mTopRatedMoviesRecycler.setAdapter(topRatedMoviesAdapter);

        MoviesAdapter mostPopularMovieAdapter = new MoviesAdapter(this, mMostPopularMoviesList);
        mMostPopularMoviesRecycler.setAdapter(mostPopularMovieAdapter);

    }
}
