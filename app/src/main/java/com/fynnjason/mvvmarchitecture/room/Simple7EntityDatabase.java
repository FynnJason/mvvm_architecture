package com.fynnjason.mvvmarchitecture.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Simple7Entity.class}, version = 1, exportSchema = false)
public abstract class Simple7EntityDatabase extends RoomDatabase {
    public abstract Simple7EntityDao getSimple7EntityDao();
}
