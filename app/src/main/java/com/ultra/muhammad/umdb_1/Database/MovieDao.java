package com.ultra.muhammad.umdb_1.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<MovieEntry> loadAllMovies();

    @Insert
    void insertMovie(MovieEntry movieEntry);

    @Delete
    void deleteMovie(MovieEntry movieEntry);
}
