package me.hienngo.bbcsport.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import me.hienngo.bbcsport.entity.NewsEntity;

/**
 * @author hienngo
 * @since 2/1/18
 */
@Database(entities = {NewsEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract NewsDao newsDao();
}
