package me.hienngo.bbcsport.domain.repo

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Hien Ngo
 * @since 2019-08-29
 */
interface MovideApiRepo {
    @GET("movie/now_playing")
    fun getNowPlaying(@Query("apiKey") apiKey: String): Single<NowPlayingResponse>
}