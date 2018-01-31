package me.hienngo.bbcsport.domain.repo;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author hienngo
 * @since 1/31/18
 */

public interface NewsApiRepo {
    @GET("top-headlines")
    Observable getTopHeadline();
}
