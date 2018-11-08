package com.ultra.muhammad.umdb_1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ultra.muhammad.umdb_1.R;
import com.ultra.muhammad.umdb_1.database.MovieEntry;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OfflineMoviesAdapter extends RecyclerView.Adapter<OfflineMoviesAdapter.MoviesViewHolder> {

    private static final String LOG_TAG = OfflineMoviesAdapter.class.getSimpleName();
    private final ItemClickListener mItemClickListener;
    private List<MovieEntry> mMovieEntries;
    private final Context mContext;

    public OfflineMoviesAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(LOG_TAG, "onCreateViewHolder() has been instantiated!");
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.offline_movie_list_item, viewGroup, false);

        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int position) {
        Log.d(LOG_TAG, "onBindViewHolder() has been instantiated!");
        MovieEntry movieEntry = mMovieEntries.get(position);
        String movieTitle = movieEntry.getTitle();
        String movieRate = String.valueOf(movieEntry.getVote_average());

        moviesViewHolder.movieTitle.setText(movieTitle);
        moviesViewHolder.movieRate.setText(movieRate);
    }

    @Override
    public int getItemCount() {
        if (mMovieEntries == null) {
            return 0;
        }
        return mMovieEntries.size();
    }

    public List<MovieEntry> getMovieEntries() {
        return mMovieEntries;
    }

    public void setMovieEntries(List<MovieEntry> mMovieEntries) {
        Log.d(LOG_TAG, "setMovieEntries() has been instantiated!");
        this.mMovieEntries = mMovieEntries;
        notifyDataSetChanged();
        Log.d(LOG_TAG, "finished notifying changes!");
    }

    public interface ItemClickListener {
        void onItemClickListener(MovieEntry movieEntry);
    }


    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.offline_movie_name)
        TextView movieTitle;
        @BindView(R.id.offline_movie_rate)
        TextView movieRate;

        MoviesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MovieEntry movieEntry = mMovieEntries.get(getAdapterPosition());
            mItemClickListener.onItemClickListener(movieEntry);
        }
    }
}
