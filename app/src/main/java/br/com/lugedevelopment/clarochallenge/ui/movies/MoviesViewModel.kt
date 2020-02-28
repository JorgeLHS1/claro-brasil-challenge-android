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
import br.com.lugedevelopment.clarochallenge.data.network.reponses.MovieResultResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val allMovies: LiveData<MutableList<MovieEntity>>

    val moviesLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    private val repository: MoviesRepository

    init {
        val moviesDao = MoviesRoomDatabase.getDatabase(application, viewModelScope).moviesDAO()
        repository =
            MoviesRepository(moviesDao)
        allMovies = repository.allMovies
        getData()
    }

    fun insert(movie: MovieEntity) = viewModelScope.launch {
        repository.insertMovie(movie)
    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://api.themoviedb.org/3/")
        val endpoint = retrofitClient.create(Webservice::class.java)
        val callback = endpoint.searchMovies("Toy Story", language = "pt-BR")

        callback.enqueue(object : Callback<MovieResultResponse> {
            override fun onFailure(call: Call<MovieResultResponse>, t: Throwable) {
                //TODO: Add onFailure Function
            }
            override fun onResponse(call: Call<MovieResultResponse>, response: Response<MovieResultResponse>) {
                if(response.isSuccessful){
                    val movies: MutableList<Movie> = mutableListOf()
                    val picasso = Picasso.get()
                    response.body()?.let {movieResult ->
                        for (result in movieResult.results){

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
                                //posterImage = picasso.load("https://image.tmdb.org/t/p/w500" + result.posterPath).get()
                                //posterImage = repository.getBitmap("https://image.tmdb.org/t/p/w500" + result.posterPath)
                            )

                            movies.add(movie)
                        }
                    }
                    moviesLiveData.value = movies
                }
            }
        })
    }

}