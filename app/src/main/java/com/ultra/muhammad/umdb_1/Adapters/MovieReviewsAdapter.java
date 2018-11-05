package com.ultra.muhammad.umdb_1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.ultra.muhammad.umdb_1.R;

import java.util.List;

import at.blogc.android.views.ExpandableTextView;

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.ReviewViewHolder> {
    private static final String TAG = MovieReviewsAdapter.class.getSimpleName();
    private List<String> mReviews;

    public MovieReviewsAdapter(Context context, List<String> reviewsList) {
        mReviews = reviewsList;
        SparseBooleanArray mCollapsedStatus = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder() has been instantiated!");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reviews_list_item, parent, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder() has been instantiated!");
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {
        ExpandableTextView expandableTextView;

        ReviewViewHolder(View itemView) {
            super(itemView);
            expandableTextView = itemView.findViewById(R.id.expand_text_view);
        }

        void bind(int position) {
            expandableTextView.setText(mReviews.get(position));
            expandableTextView.setAnimationDuration(750L);
            expandableTextView.setInterpolator(new OvershootInterpolator());
            expandableTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandableTextView.toggle();
                }
            });
        }
    }
}
