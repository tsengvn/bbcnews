package me.hienngo.bbcsport.domain.repo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author hienngo
 */

public interface NewsApiRepo {
    @GET("top-headlines")
    Single<NewsResponse> getTopHeadline(@Query("apiKey") String apiKey, @Query("sources") String source);
}
