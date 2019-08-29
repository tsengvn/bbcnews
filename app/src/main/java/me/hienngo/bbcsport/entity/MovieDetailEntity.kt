package me.hienngo.bbcsport.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_detail")
class MovieDetailEntity(
        @PrimaryKey
        @SerializedName("id") val id: Int,
        @SerializedName("adult") val adult: Boolean,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("belongs_to_collection") val belongsToCollection: String,
        @SerializedName("budget") val budget: Int,
        @SerializedName("homepage") val homepage: String,
        @SerializedName("imdb_id") val imdbId: String,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("poster_path") val poster_path: String,
        @SerializedName("release_date") val release_date: String,
        @SerializedName("revenue") val revenue: Int,
        @SerializedName("runtime") val runtime: Int,
        @SerializedName("status") val status: String,
        @SerializedName("tagline") val tagline: String,
        @SerializedName("title") val title: String,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("vote_count") val voteCount: Int
)