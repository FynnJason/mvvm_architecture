package com.fynnjason.mvvmarchitecture.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Simple7EntityDao {
    /**
     * 插入一条或多条数据
     */
    @Insert
    void insertEntities(Simple7Entity... entities);

    /**
     * 更新一条或多条数据
     */
    @Update
    void updateEntities(Simple7Entity... entities);

    /**
     * 删除一条或多条数据
     */
    @Delete
    void deleteEntities(Simple7Entity... entities);

    /**
     * 删除所有数据
     */
    @Query("DELETE FROM simple7entity")
    void deleteAllEntities();

    /**
     * 查询所有数据
     */
    @Query("SELECT * FROM simple7entity")
    List<Simple7Entity> getAllEntities();

    /**
     * 根据条件查询某条数据
     */
    @Query("SELECT * FROM simple7entity WHERE chinese = :chinese")
    Simple7Entity getEntity(String chinese);
}
