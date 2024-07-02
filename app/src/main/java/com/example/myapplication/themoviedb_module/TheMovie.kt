// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.myapplication.themoviedb_module

import com.google.gson.annotations.SerializedName

data class TheMovie (
    val page: Long,
    val totalPages: Long,
    val results: List<Result>,
    val totalResults: Long
)

data class Result (
    @SerializedName("overview")
    val overview: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("genre_ids")
    val genreIDS: List<Long>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("id")
    val id: Long,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("vote_count")
    val voteCount: Long
) {
    val fullPosterPath : String
        get() = "https://image.tmdb.org/t/p/w500/$backdropPath"
}
