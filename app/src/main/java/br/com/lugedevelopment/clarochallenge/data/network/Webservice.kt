package br.com.lugedevelopment.clarochallenge.data.network

import br.com.lugedevelopment.clarochallenge.data.network.reponses.MovieResponse
import br.com.lugedevelopment.clarochallenge.data.network.reponses.SearchMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Webservice {

    @GET("/3/search/movie")
    fun searchMovies(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = "91524da116c07557abc794f8e9848f8d",
        @Query("language") language: String = "en-US"
    ): Call<SearchMovieResponse>

    @GET("/3/movie/{id}")
    fun getMovie(
        @Path("id") id: String,
        @Query("append_to_response") response: String = "videos",
        @Query("api_key") apiKey: String = "91524da116c07557abc794f8e9848f8d",
        @Query("language") language: String = "en-US"
    ): Call<MovieResponse>

}