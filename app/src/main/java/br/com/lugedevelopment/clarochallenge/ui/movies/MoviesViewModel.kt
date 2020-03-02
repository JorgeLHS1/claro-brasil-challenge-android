package br.com.lugedevelopment.clarochallenge.ui.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.lugedevelopment.clarochallenge.data.MoviesRepository
import br.com.lugedevelopment.clarochallenge.data.dao.MovieEntity
import br.com.lugedevelopment.clarochallenge.data.dao.MoviesRoomDatabase
import br.com.lugedevelopment.clarochallenge.data.models.Movie
import br.com.lugedevelopment.clarochallenge.data.network.NetworkUtils
import br.com.lugedevelopment.clarochallenge.data.network.Webservice
import br.com.lugedevelopment.clarochallenge.data.network.reponses.SearchMovieResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    val allMovies: LiveData<MutableList<MovieEntity>>

    var moviesResult: MutableLiveData<List<Movie>> = MutableLiveData()

    var errorResult: MutableLiveData<Boolean> = MutableLiveData()

    var showProgressDialog: MutableLiveData<Boolean> = MutableLiveData()

    private val repository: MoviesRepository

    init {
        val moviesDao = MoviesRoomDatabase.getDatabase(application, viewModelScope).moviesDAO()
        repository =
            MoviesRepository(moviesDao)
        allMovies = repository.allMovies
    }

    fun insert(movie: MovieEntity) = viewModelScope.launch {
        repository.insertMovie(movie)
    }

    fun searchMovieResult(movieName: String) {
        getData(movieName)
    }

    fun getData(movieName: String = "Toy Story") {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.themoviedb.org/3/")
        val endpoint = retrofitClient.create(Webservice::class.java)
        val callback = endpoint.searchMovies(movieName, language = "pt-BR")
        showProgressDialog.value = true

        callback.enqueue(object : Callback<SearchMovieResponse> {
            override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                errorResult.value = true
                showProgressDialog.value = false
            }

            override fun onResponse(
                call: Call<SearchMovieResponse>,
                response: Response<SearchMovieResponse>
            ) {
                if (response.isSuccessful) {
                    val movies: MutableList<Movie> = mutableListOf()
                    response.body()?.let { movieResult ->
                        for (result in movieResult.results) {

                            val movie = Movie(
                                voteCount = result.voteCount,
                                video = result.video,
                                posterPath = result.posterPath,
                                id = result.id,
                                adult = result.adult,
                                backdropPath = result.backdropPath,
                                originalLanguage = result.originalLanguage,
                                originalTitle = result.originalTitle,
                                genreIds = result.genreIds,
                                title = result.title,
                                voteAverage = result.voteAverage,
                                overview = result.overview,
                                releaseDate = result.releaseDate
                            )

                            movies.add(movie)
                        }
                    }
                    moviesResult.value = movies
                    showProgressDialog.value = false
                } else {
                    errorResult.value = true
                    showProgressDialog.value = false
                }
            }
        })
    }

}