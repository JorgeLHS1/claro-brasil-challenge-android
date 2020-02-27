package br.com.lugedevelopment.clarochallenge.dao

import androidx.lifecycle.LiveData

class MoviesRepository(private val moviesDAO: MoviesDAO) {


    val allMovies: LiveData<MutableList<MovieEntity>> = moviesDAO.getMovies()

    val favoriteMovies: LiveData<MutableList<MovieEntity>> = moviesDAO.getMoviesByFavorite()

    suspend fun insertMovie(movie: MovieEntity) = moviesDAO.insertMovie(movie)

    suspend fun removeMovie(movie: MovieEntity) = moviesDAO.removeMovie(movie)

    fun getMovieById(id: Int): LiveData<MovieEntity> = moviesDAO.getMovieById(id)
}