package me.hienngo.bbcsport.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import me.hienngo.bbcsport.entity.NewsEntity;

/**
 * @author hienngo
 * @since 2/1/18
 */
@Dao
public interface NewsDao {
    @Query("select * from news")
    Maybe<List<NewsEntity>> getAll();

    @Query("delete from news")
    void deleteAll();

    @Insert
    void add(List<NewsEntity> newsEntities);
}
