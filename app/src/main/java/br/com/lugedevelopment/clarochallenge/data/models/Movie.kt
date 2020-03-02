package br.com.lugedevelopment.clarochallenge.data.models

data class Movie(
    val voteCount: Int,
    val video: Boolean,
    val posterPath: String?,
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val genreIds: List<Int>,
    val title: String,
    val voteAverage: Double,
    val overview: String,
    val releaseDate: String
)