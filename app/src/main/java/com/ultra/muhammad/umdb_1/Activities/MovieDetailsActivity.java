package com.ultra.muhammad.umdb_1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ultra.muhammad.umdb_1.Models.Genre;
import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.Models.MovieDetails;
import com.ultra.muhammad.umdb_1.MovieUtils.Utils;
import com.ultra.muhammad.umdb_1.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ultra.muhammad.umdb_1.Network.RetrofitClientInstance.BACKGROUND_BASE_URL;
import static com.ultra.muhammad.umdb_1.Network.RetrofitClientInstance.POSTER_BASE_URL;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @BindView(R.id.details_movie_poster_img)
    ImageView mPosterImage;
    @BindView(R.id.details_movie_background_img)
    ImageView mBackgroundImage;
    @BindView(R.id.details_movie_name)
    TextView mTitle;
    @BindView(R.id.details_movie_rating)
    TextView mRating;
    @BindView(R.id.production_year_tv)
    TextView mYear;
    @BindView(R.id.movie_type_tv)
    TextView mType;
    @BindView(R.id.overview_tv)
    TextView mOverview;
    @BindView(R.id.activity_movie_details_layout)
    ConstraintLayout mDetailsActivity;
    @BindView(R.id.loading_progress_bar)
    ProgressBar mLoadingProgressBar;

    String title, poster, background, productionYear, rate, genre, year, movieId, overview;
    Movie movie = null;
    boolean bool, f1 = false;
    Map<String, Object> options = new HashMap<>();

    private static String formatTime(String tripTime) throws ParseException {
        Log.d(TAG, "formatTime() has been instantiated");
        DateFormat formatter
                = new SimpleDateFormat("yyyy");
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = originalFormat.parse(tripTime);
        return formatter.format(date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate() has been instantiated");
        showProgressBar();

        Intent intent = getIntent();
        if (intent != null)
            if (intent.hasExtra("selected_movie"))
                movie = (Movie) intent.getSerializableExtra("selected_movie");
            else if (intent.hasExtra("selected_upcoming_movie"))
                movie = (Movie) intent.getSerializableExtra("selected_upcoming_movie");
        title = movie.getTitle();
        poster = movie.getPosterPath();
        background = movie.getBackdropPath();
        productionYear = movie.getReleaseDate();
        rate = String.valueOf(movie.getVoteAverage());
        overview = movie.getOverview();
        movieId = String.valueOf(movie.getId());
        try {
            year = formatTime(movie.getReleaseDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getExtraMovieDetailsFromInternet(movieId);
    }

    private void getExtraMovieDetailsFromInternet(String movieId) {
        Log.d(TAG, "getExtraMovieDetailsFromInternet() has been instantiated");
        bool = false;
        options.put("api_key", getString(R.string.api_key));
        Utils.getMovieDetails(movieId, options, new Utils.retrofitCallback() {
            @Override
            public void onSuccess(List<Movie> movies) {

            }

            @Override
            public void onSuccess(MovieDetails movieDetails) {
                Log.i(TAG, movieDetails.toString());
                f1 = true;
                genre = getMovieGenres(movieDetails.getGenres());
                hideProgressBar();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void fillViewsWithData() {
        Log.d(TAG, "fillViewsWithData() has been instantiated");
        Picasso.get().load(POSTER_BASE_URL + poster).fit().centerInside().into(mPosterImage);
        Picasso.get().load(BACKGROUND_BASE_URL + background).fit().centerInside().into(mBackgroundImage);
        mTitle.setText(title);
        mRating.setText(rate);
        mYear.setText(year);
        mType.setText(genre);
        mOverview.setText(overview);
    }

    private String getMovieGenres(List<Genre> genres) {
        Log.d(TAG, "getMovieGenres() has been instantiated");
        StringBuilder movieGenre = new StringBuilder();

        for (int i = 0; i < genres.size() && i < 3; i++) {
            movieGenre.append(genres.get(i).getName());
            movieGenre.append(", ");
        }
        movieGenre.deleteCharAt(movieGenre.length() - 1);
        movieGenre.deleteCharAt(movieGenre.length() - 1);
        return movieGenre.toString();
    }

    private void showProgressBar() {
        Log.d(TAG, "showProgressBar() has been instantiated");
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        mDetailsActivity.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        Log.d(TAG, "hideProgressBar() has been instantiated");
        bool = f1;
        if (bool) {
            mLoadingProgressBar.setVisibility(View.GONE);
            mDetailsActivity.setVisibility(View.VISIBLE);
            fillViewsWithData();
        }
    }
}
