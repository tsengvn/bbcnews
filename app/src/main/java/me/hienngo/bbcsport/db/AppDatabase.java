package me.hienngo.bbcsport.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.hienngo.bbcsport.entity.NewsEntity;

/**
 * @author hienngo
 * @since 2/1/18
 */
@Database(entities = {NewsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract NewsDao newsDao();
}
