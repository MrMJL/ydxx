package org.ydxx.entity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.cosima.base.xy.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.ydxx.R;
import org.ydxx.activitys.JxzyActivity;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.net.Business;
import org.ydxx.net.MainService;
import org.ydxx.net.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        Utils.init(this);
        SharedPreferences preferences = getSharedPreferences("TAG", MODE_PRIVATE);
        boolean isFirst = preferences.getBoolean("isFirst", true);
        if (isFirst) {
            getJxzy();
            getMess();
            getOnlineUser();
        }
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("TAG", false);
        edit.apply();
        //initImageLoader(getApplicationContext());
    }

    @SuppressLint("CheckResult")
    public void getJxzy() {
        RetrofitClient.getInstance().create(MainService.class)
                .getJxzy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    for (int i = 0; i < response.size(); i++) {
                        Log.e("kin", "delData: " + response.get(i).toString());
                    }
                    LocalDataSource.getInstance(this).getJxzyDao().insertJxzies(response);
                }, throwable -> {
                    Log.e("kin", "delData: " + throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    public void getMess() {
        RetrofitClient.getInstance().create(MainService.class)
                .getMess()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    for (int i = 0; i < response.size(); i++) {
                        Log.e("kin", "delData: " + response.get(i).toString());
                    }
                    LocalDataSource.getInstance(this).getMessDao().insertMesses(response);

                }, throwable -> {
                    Log.e("kin", "delData: " + throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    public void getOnlineUser() {
        RetrofitClient.getInstance().create(MainService.class)
                .getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    for (int i = 0; i < response.size(); i++) {
                        Log.e("kin", "delData: " + response.get(i).toString());
                    }
                    LocalDataSource.getInstance(this).getUserDao().insertUsers(response);

                }, throwable -> {
                    Log.e("kin", "delData: " + throwable.getMessage());
                });
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
