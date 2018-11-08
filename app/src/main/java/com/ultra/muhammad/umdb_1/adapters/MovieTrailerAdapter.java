package com.ultra.muhammad.umdb_1.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ultra.muhammad.umdb_1.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ultra.muhammad.umdb_1.network.RetrofitClientInstance.TRAILER_BASE_URL;

public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.TrailerViewHolder> {
    private static final String TAG = MovieTrailerAdapter.class.getSimpleName();
    private final Context context;
    private final List<String> mTrailers;

    public MovieTrailerAdapter(Context context, List<String> mTrailers) {
        this.context = context;
        this.mTrailers = mTrailers;
    }

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Log.d(TAG, "onCreateViewHolder() has been instantiated!");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_list_item, parent, false);
        return new TrailerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder trailerViewHolder, int i) {
        Log.d(TAG, "onBindViewHolder() has been instantiated!");
        trailerViewHolder.mTrailerTv.setText(context.getString(R.string.trailer));
    }

    @Override
    public int getItemCount() {
        if (mTrailers != null)
            return mTrailers.size();
        return 0;
    }

    class TrailerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.trailer_name_tv)
        TextView mTrailerTv;

        TrailerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.trailer_name_tv)
        public void onClick() {
            int clickedItem = getAdapterPosition();
            String trailer = mTrailers.get(clickedItem);
            Log.i(TAG, "WATCH TRAILER::has been clicked");
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse((TRAILER_BASE_URL + trailer)));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
