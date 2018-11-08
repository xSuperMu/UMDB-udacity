package com.ultra.muhammad.umdb_1.arch_comp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ultra.muhammad.umdb_1.database.AppDatabase;
import com.ultra.muhammad.umdb_1.database.MovieEntry;

import java.util.List;

/*
 *   I'm going to use this class to cache my list of movieEntry objects (wrapped in a LiveData object).
 *
 * */

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();
    private final LiveData<List<MovieEntry>> movies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase database = AppDatabase.getsInstance(getApplication());
        Log.d(TAG, "MainViewModel: Actively retrieving the movies from the database");
        movies = database.movieDao().loadAllMovies();
    }

    public LiveData<List<MovieEntry>> getMovies() {
        return movies;
    }
}
