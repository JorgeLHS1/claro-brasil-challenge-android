package br.com.lugedevelopment.clarochallenge.data.network.reponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchMovieResponse(
    @Json(name = "page")
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "results")
    val results: List<SearchMovieResultResponse>
)

@JsonClass(generateAdapter = true)
data class SearchMovieResultResponse(
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "video")
    val video: Boolean,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "title")
    val title: String,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "release_date")
    val releaseDate: String
)

