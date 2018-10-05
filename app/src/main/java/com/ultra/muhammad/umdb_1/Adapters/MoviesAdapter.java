package com.ultra.muhammad.umdb_1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.ultra.muhammad.umdb_1.Models.Movie;
import com.ultra.muhammad.umdb_1.Network.RetrofitClientInstance;
import com.ultra.muhammad.umdb_1.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {
    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private Context mContext;
    private List<Movie> mMovieList;

    public MoviesAdapter(Context context, List<Movie> moviesList) {
        mContext = context;
        mMovieList = moviesList;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() has been instantiated");
        View itemViewNormal = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_main, parent, false);
        return new MoviesAdapterViewHolder(itemViewNormal);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder() has been instantiated");
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class MoviesAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_poster_img_main)
        ImageView mPosterImage;
        @BindView(R.id.movie_rating_main)
        TextView mRating;

        private MoviesAdapterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(int position) {
            Log.d(TAG, "bind() has been instantiated");
            Movie movie = mMovieList.get(position);
            Log.i(TAG, movie.toString());
            String rating = String.valueOf(movie.getVoteAverage());
            String poster = RetrofitClientInstance.POSTER_BASE_URL + movie.getPosterPath();
            Picasso.get().load(poster).placeholder(R.drawable.no_image).error(R.drawable.no_image).fit().centerInside().into(mPosterImage);
            mRating.setText(rating);
        }
    }
}
