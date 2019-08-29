package me.hienngo.bbcsport.domain.repo

import com.google.gson.annotations.SerializedName
import me.hienngo.bbcsport.entity.Movie

/**
 * @author hienngo
 */
class NowPlayingResponse(
        @SerializedName("results") val results: List<Movie>,
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") val total_results: Int,
        @SerializedName("dates") val dates: Dates,
        @SerializedName("total_pages") val total_pages: Int
)