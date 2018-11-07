package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ultra.muhammad.umdb_1.Database.AppDatabase;
import com.ultra.muhammad.umdb_1.Database.MovieEntry;
import com.ultra.muhammad.umdb_1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieOfflineDetailsActivity extends AppCompatActivity {

    private static final String TAG = MovieOfflineDetailsActivity.class.getSimpleName();
    @BindView(R.id.add_to_favorite_btn_offline)
    Button mAddToFavorite;
    @BindView(R.id.offline_details_movie_name)
    TextView mTitle;
    @BindView(R.id.offline_details_movie_rating)
    TextView mRating;
    @BindView(R.id.production_year_tv_offline)
    TextView mYear;
    @BindView(R.id.movie_type_tv_offline)
    TextView mType;
    @BindView(R.id.overview_tv_offline)
    TextView mOverview;
    boolean mIsFavorite = false;
    String title, rate, genre, year, movieId, overview;
    private AppDatabase mDb;

    private static String formatTime(String tripTime) throws ParseException {
        Log.d(TAG, "formatTime() has been instantiated");
        DateFormat formatter
                = new SimpleDateFormat("MM-dd-yyyy");
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = originalFormat.parse(tripTime);
        return formatter.format(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_offline_details);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate() has been instantiated");

        mDb = AppDatabase.getsInstance(getApplicationContext());

        MovieEntry movieEntry = null;
        Intent intent = getIntent();

        if (intent != null)
            if (intent.hasExtra("selected_favorite_movie"))
                movieEntry = (MovieEntry) intent.getSerializableExtra("selected_favorite_movie");

        if (movieEntry != null) {
            movieId = movieEntry.getMovieId();

            boolean alreadyInTheDatabase = movieAlreadyInTheDatabase(movieId);
            if (alreadyInTheDatabase) {
                Log.d(TAG, "Movie Already in the Database");
                mAddToFavorite.setBackground(getResources().getDrawable(R.drawable.transparent_button));
                mAddToFavorite.setText(getResources().getString(R.string.remove_from_favorite));
                mIsFavorite = true;
            }

            title = movieEntry.getTitle();
            rate = String.valueOf(movieEntry.getVote_average());
            overview = movieEntry.getOverview();
            try {
                year = formatTime(movieEntry.getRelease_date());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            genre = movieEntry.getMovieGenres();

            fillViewsWithData();
        }
    }

    private void fillViewsWithData() {
        Log.d(TAG, "fillViewsWithData() has been instantiated");
        mTitle.setText(title);
        mRating.setText(rate);
        mYear.setText(year);
        mType.setText(genre);
        mOverview.setText(overview);
    }

    @OnClick(R.id.add_to_favorite_btn_offline)
    public void handleFavoriteMovie(Button b) {
        if (mIsFavorite) {
            b.setBackground(getResources().getDrawable(R.drawable.blue_trading_button));
            mAddToFavorite.setText(getResources().getString(R.string.add_to_favorite));
            MovieEntry movieEntry = new MovieEntry(movieId, title, Double.parseDouble(rate), overview, year, genre);
            mDb.movieDao().deleteMovie(movieEntry);
            mIsFavorite = false;
        } else {
            b.setBackground(getResources().getDrawable(R.drawable.transparent_button));
            mAddToFavorite.setText(getResources().getString(R.string.remove_from_favorite));
            // Get movie details {id, name, rate, type, date, overview}
            MovieEntry movieEntry = new MovieEntry(movieId, title, Double.parseDouble(rate), overview, year, genre);
            mDb.movieDao().insertMovie(movieEntry);
            mIsFavorite = true;
        }
    }

    private boolean movieAlreadyInTheDatabase(String movieId) {
        MovieEntry movieEntry = mDb.movieDao().loadMovieById(movieId);
        return movieEntry != null;
    }
}
