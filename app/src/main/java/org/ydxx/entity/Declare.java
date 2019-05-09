package org.ydxx.entity;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.ydxx.R;
import org.ydxx.activitys.JxzyActivity;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.net.Business;

public class Declare extends Application {
    private Business business;
    private User user;

    public Business getBusiness() {
        return business;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        business = new Business();

        SharedPreferences preferences = getSharedPreferences("TAG", MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean("isFirst", true);
        if (isFirst) {
            List<Jxzy> items = new ArrayList<>();
            Jxzy jxzy = new Jxzy();
            jxzy.setKcmc("高数 No.1");
            jxzy.setExt1("http://www.w3school.com.cn/");
            jxzy.setLsxm("明明 No.1");

            Jxzy jxzy1 = new Jxzy();
            jxzy1.setKcmc("高数 No.2");
            jxzy1.setLsxm("明明 No.2");
            jxzy1.setExt1("https://www.imooc.com/video/16896");

            Jxzy jxzy2 = new Jxzy();
            jxzy2.setKcmc("高数 No.3");
            jxzy2.setLsxm("明明 No.3");
            jxzy2.setExt1("https://www.imooc.com/video/17196");

            items.add(jxzy);
            items.add(jxzy1);
            items.add(jxzy2);

            LocalDataSource.getInstance(this).getJxzyDao().insertJxzies(items);

            List<Mess> data = new ArrayList<>();
            Mess mess = new Mess();
            mess.setFusername("明明 No.1");
            mess.setFmessage("毕业设计好过吗？");
            mess.setTusername("聪聪 No.1");
            mess.setTmessage("不好过。");
            Mess mess1 = new Mess();
            mess.setFusername("明明 No.2");
            mess.setFmessage("真的不好过吗？");
            mess.setTusername("聪聪 No.2");
            mess.setTmessage("是真的！");
            data.add(mess);
            data.add(mess1);
            LocalDataSource.getInstance(this).getMessDao().insertMesses(data);

            List<User> users = new ArrayList<>();
            User user = new User();
            user.setUsername("111");
            user.setAge("111");
            user.setPassword("111");
            user.setEmail("111");
            user.setSex("1");
            user.setType("4");

            User user2 = new User();
            user2.setUsername("222");
            user2.setAge("222");
            user2.setPassword("222");
            user2.setEmail("222");
            user2.setSex("2");
            user2.setType("3");

            users.add(user);
            users.add(user2);
            LocalDataSource.getInstance(this).getUserDao().insertUsers(users);
        }
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("TAG", false);
        edit.apply();
        //initImageLoader(getApplicationContext());
    }

    //public static void initImageLoader(Context context) {
    //	DisplayImageOptions options = new DisplayImageOptions.Builder()
    //	.showImageOnLoading(R.drawable.loading)
    //	.showImageOnFail(R.drawable.error)
    //	.showImageForEmptyUri(R.drawable.empty)
    //	.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
    //	.cacheInMemory(true)
    //	.cacheOnDisk(true)
    //	.bitmapConfig(Bitmap.Config.RGB_565)
    //	.build();
    //
    //	ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
    //	config.defaultDisplayImageOptions(options);
    //	config.threadPriority(Thread.NORM_PRIORITY - 2);//最大线程数字
    //	config.denyCacheImageMultipleSizesInMemory();//拒绝缓存多个文件
    //	config.memoryCache(new WeakMemoryCache()).memoryCacheSize(10 * 1024 * 1024);
    //	config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
    //	config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
    //	config.tasksProcessingOrder(QueueProcessingType.LIFO);
    //
    //	ImageLoader.getInstance().init(config.build());
    //}
}
