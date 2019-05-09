package org.ydxx.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import org.ydxx.entity.User;
import org.ydxx.entity.Xsdy;

/**
 * @author： Name:  JAG
 * @date: Date:  2019/3/7
 * @description:
 */
@Dao
public interface XsdyDao {

    @Query("SELECT * FROM xsdy")
    List<Xsdy> getAllXsdy();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertXsdy(Xsdy xsdy);

    @Query("DELETE FROM xsdy WHERE jxzymc = :jxzymc")
    void deleteXsdyBy(String jxzymc);

    @Query("DELETE FROM xsdy")
    void deleteAllXsdy();
}
