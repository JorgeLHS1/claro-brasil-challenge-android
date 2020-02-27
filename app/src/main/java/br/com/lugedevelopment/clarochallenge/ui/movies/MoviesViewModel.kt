package br.com.lugedevelopment.clarochallenge.ui.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.lugedevelopment.clarochallenge.dao.MovieEntity
import br.com.lugedevelopment.clarochallenge.dao.MoviesRepository
import br.com.lugedevelopment.clarochallenge.dao.MoviesRoomDatabase
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val allMovies: LiveData<MutableList<MovieEntity>>

    private val repository: MoviesRepository

    init {
        val moviesDao = MoviesRoomDatabase.getDatabase(application, viewModelScope).moviesDAO()
        repository = MoviesRepository(moviesDao)
        allMovies = repository.allMovies
    }

    fun insert(movie: MovieEntity) = viewModelScope.launch {
        repository.insertMovie(movie)
    }


}