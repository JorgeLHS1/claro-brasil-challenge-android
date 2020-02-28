package br.com.lugedevelopment.clarochallenge.data

import androidx.lifecycle.LiveData
import br.com.lugedevelopment.clarochallenge.data.dao.MovieEntity
import br.com.lugedevelopment.clarochallenge.data.dao.MoviesDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MoviesRepository(private val moviesDAO: MoviesDAO) {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    val allMovies: LiveData<MutableList<MovieEntity>> = moviesDAO.getMovies()

    val favoriteMovies: LiveData<MutableList<MovieEntity>> = moviesDAO.getMoviesByFavorite()

    suspend fun insertMovie(movie: MovieEntity) = moviesDAO.insertMovie(movie)

    suspend fun removeMovie(movie: MovieEntity) = moviesDAO.removeMovie(movie)

    fun getMovieById(id: Int): LiveData<MovieEntity> = moviesDAO.getMovieById(id)
}