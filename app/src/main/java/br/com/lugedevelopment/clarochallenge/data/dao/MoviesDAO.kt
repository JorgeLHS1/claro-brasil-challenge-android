package br.com.lugedevelopment.clarochallenge.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoviesDAO {

    @Query("SELECT * FROM movies_table WHERE movie_favorite = 1")
    fun getMoviesByFavorite(): LiveData<MutableList<MovieEntity>>

    @Query("SELECT * FROM movies_table WHERE movie_id = :idMovie")
    fun getMovieById(idMovie: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movies_table")
    fun getMovies(): LiveData<MutableList<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun removeMovie(movie: MovieEntity)

    @Query("DELETE FROM movies_table")
    suspend fun removeAll()
}