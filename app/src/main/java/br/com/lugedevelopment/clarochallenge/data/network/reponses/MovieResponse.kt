package br.com.lugedevelopment.clarochallenge.data.network.reponses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(
    @Json(name = "adult")
    var adult: Boolean,
    @Json(name = "backdrop_path")
    var backdropPath: String? = null,
    @Json(name = "belongs_to_collection")
    var belongsToCollection: BelongsToCollectionResult? = null,
    @Json(name = "budget")
    var budget: Int,
    @Json(name = "genres")
    var genres: List<GenreResult>,
    @Json(name = "homepage")
    var homepage: String? = null,
    @Json(name = "id")
    var id: Int,
    @Json(name = "imdb_id")
    var imdbId: String? = null,
    @Json(name = "original_language")
    var originalLanguage: String,
    @Json(name = "original_title")
    var originalTitle: String,
    @Json(name = "overview")
    var overview: String? = null,
    @Json(name = "popularity")
    var popularity: Double,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "production_companies")
    var productionCompanies: List<ProductionCompanyResult>? = null,
    @Json(name = "production_countries")
    var productionCountries: List<ProductionCountryResult>? = null,
    @Json(name = "release_date")
    var releaseDate: String,
    @Json(name = "revenue")
    var revenue: Int,
    @Json(name = "runtime")
    var runtime: Int? = null,
    @Json(name = "spoken_languages")
    var spokenLanguages: List<SpokenLanguageResult>? = null,
    @Json(name = "status")
    var status: String,
    @Json(name = "tagline")
    var tagline: String? = null,
    @Json(name = "title")
    var title: String,
    @Json(name = "video")
    var video: Boolean,
    @Json(name = "vote_average")
    var voteAverage: Double,
    @Json(name = "vote_count")
    var voteCount: Int,
    @Json(name = "videos")
    var videos: VideosResponse? = null
)

@JsonClass(generateAdapter = true)
data class GenreResult(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null
)

@JsonClass(generateAdapter = true)
data class BelongsToCollectionResult(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "poster_path")
    var posterPath: String? = null,
    @Json(name = "backdrop_path")
    var backdropPath: String? = null
)

@JsonClass(generateAdapter = true)
data class ProductionCompanyResult(
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "logo_path")
    var logoPath: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "origin_country")
    var originCountry: String? = null
)

@JsonClass(generateAdapter = true)
data class ProductionCountryResult(
    @Json(name = "iso_3166_1")
    var iso31661: String? = null,
    @Json(name = "name")
    var name: String? = null
)

@JsonClass(generateAdapter = true)
data class SpokenLanguageResult(
    @Json(name = "iso_639_1")
    var iso6391: String? = null,
    @Json(name = "name")
    var name: String? = null
)

@JsonClass(generateAdapter = true)
data class VideosResponse(
    @Json(name = "results")
    var results: List<VideoResultResponse>? = null
)

@JsonClass(generateAdapter = true)
data class VideoResultResponse(
    @Json(name = "id")
    var id: String? = null,
    @Json(name = "iso_639_1")
    var iso6391: String? = null,
    @Json(name = "iso_3166_1")
    var iso31661: String? = null,
    @Json(name = "key")
    var key: String? = null,
    @Json(name = "name")
    var name: String? = null,
    @Json(name = "site")
    var site: String? = null,
    @Json(name = "size")
    var size: Int? = null,
    @Json(name = "type")
    var type: String? = null
)
