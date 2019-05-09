package org.ydxx.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;

/**
 * @author： Name:  JAG
 * @date: Date:  2019/3/7
 * @description:
 */
@Dao
public interface JxzyDao {

    @Query("SELECT * FROM jxzy")
    List<Jxzy> getAllJxzy();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJxzies(List<Jxzy> jxzies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertJxzy(Jxzy jxzy);

    @Update
    void updateJxzy(Jxzy jxzy);

    @Delete
    void deleteJxzy(Jxzy jxzy);

    @Query("DELETE FROM jxzy")
    void deleteAllJxzy();
}
