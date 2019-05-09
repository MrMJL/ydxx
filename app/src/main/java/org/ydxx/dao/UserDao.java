package org.ydxx.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import org.ydxx.entity.User;

/**
 * @author： Name:  JAG
 * @date: Date:  2019/3/7
 * @description:
 */
@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE username = :username")
    User getUserByName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsers(List<User> users);

    @Update()
    void updateUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUser();
}
