package br.com.lugedevelopment.clarochallenge.data.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MovieEntity (@PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
                        @ColumnInfo(name = "movie_name") val movieName: String,
                        @ColumnInfo(name = "movie_category")val movieCategory: String,
                        @ColumnInfo(name = "movie_favorite") val isMovieFavorite: Boolean = false)