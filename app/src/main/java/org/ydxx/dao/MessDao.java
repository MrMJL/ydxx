package org.ydxx.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;
import org.ydxx.entity.User;

/**
 * @author： Name:  JAG
 * @date: Date:  2019/3/7
 * @description:
 */
@Dao
public interface MessDao {

    @Query("SELECT * FROM mess")
    List<Mess> getAllMess();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMesses(List<Mess> messes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMess(Mess mess);


    @Update
    void updateUser(Mess mess);

    @Query("DELETE FROM mess")
    void deleteAllMess();
}
