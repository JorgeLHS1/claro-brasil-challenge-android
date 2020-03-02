package br.com.lugedevelopment.clarochallenge.data.models

data class MovieDetails (
    val video: Boolean,
    val posterPath: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val overview: String,
    val releaseDate: String
)