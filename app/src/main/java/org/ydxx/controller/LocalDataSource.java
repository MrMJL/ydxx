package org.ydxx.controller;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import org.ydxx.dao.JxzyDao;
import org.ydxx.dao.MessDao;
import org.ydxx.dao.UserDao;
import org.ydxx.dao.XsdyDao;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;
import org.ydxx.entity.User;
import org.ydxx.entity.Xsdy;

/**
 * @author： Name:  JAG
 * @date: Date:  2019/3/7
 * @description:
 */
@Database(entities = {User.class, Jxzy.class, Xsdy.class, Mess.class}, version = 1, exportSchema = false)
public abstract class LocalDataSource extends RoomDatabase {

    private static final String DB_NAME = "material.db";
    private static volatile LocalDataSource INSTANCE;


    public static synchronized LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = create(context);
        }
        return INSTANCE;
    }

    private static LocalDataSource create(final Context context) {
        return Room.databaseBuilder(
            context,
            LocalDataSource.class,
            DB_NAME).allowMainThreadQueries().build();
    }

    public abstract UserDao getUserDao();

    public abstract JxzyDao getJxzyDao();

    public abstract XsdyDao getXsdyDao();

    public abstract MessDao getMessDao();
}
