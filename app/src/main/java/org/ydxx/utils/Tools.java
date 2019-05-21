package org.ydxx.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ydxx.entity.Jxzy;
import org.ydxx.net.MainService;
import org.ydxx.net.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Tools {


    @SuppressLint("CheckResult")
    public static void addData(String type, String json) {


//        private String jxzyid;
//        private String jxzymc;
//        private String xsid;
//        private String xsmc;
//        private String ext1;
//        private String ext2;
//        private String ext3;

//        data.put("jxzyid", "高数 No.2");
//        data.put("jxzymc", "明明 No.2");
//        data.put("xsid", "明明 No.2");
//        data.put("xsmc", "高数 No.2");
//        data.put("ext1", "https://www.imooc.com/video/16896");
//        data.put("ext2", "高数 No.2");
//        data.put("ext3", "https://www.imooc.com/video/16896");
        RetrofitClient.getInstance().create(MainService.class)
                .addData(type, json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    Log.e("kin", "delData: " + response.toString());
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    if (jsonObject.getInt("status") == 1) {
                        Log.e("kin", "delData: " + response.toString());
                    } else {

                    }
                }, throwable -> {
                    Log.e("kin", "addData: " + throwable.getMessage());
                });
    }


    @SuppressLint("CheckResult")
    public static void getData(String type) {
        RetrofitClient.getInstance().create(MainService.class)
                .getData(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    Log.e("kin", "getData: " + response.toString());
                    //jxzy
                    if (response.getData().size() == 0) {
                        return;
                    }
                    Log.e("kin", "getData: " + response);
                    Map<String, Object> data = new HashMap<>();
                    data.put("id", response.getData().get(response.getData().size() - 1).getId());
                    data.put("kcmc", "高数 No.10");
                    data.put("lsxm", "明明 No.10");
                    data.put("ext1", "https://www.imooc.com/video/16896");
                    data.put("lsid", "高数 No.2");
                    data.put("ext2", "高数 No.2");
                    data.put("ext3", "https://www.imooc.com/video/16896");
                    String s = new Gson().toJson(data);
                    updateData("jxzy", s);
                }, throwable -> {
                    Log.e("kin", "getData: throwable " + throwable.getMessage());
                });
    }


    @SuppressLint("CheckResult")
    public static void updateData(String type, String json) {
        RetrofitClient.getInstance().create(MainService.class)
                .updateData(type, json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    Log.e("kin", "updateData: " + response.toString());
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    if (jsonObject.getInt("status") == 1) {
                        Log.e("kin", "updateData: " + response.toString());
                    } else {

                    }
                }, throwable -> {
                    Log.e("kin", "updateData: " + throwable.getMessage());
                });
    }

    @SuppressLint("CheckResult")
    public static void delData(String type, String json) {
        RetrofitClient.getInstance().create(MainService.class)
                .delData(type, json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                })
                .subscribe(response -> {
                    Log.e("kin", "delData: " + response.toString());
                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
                    if (jsonObject.getInt("status") == 1) {
                        Log.e("kin", "delData: " + response.toString());
                    } else {

                    }
                }, throwable -> {
                    Log.e("kin", "delData: " + throwable.getMessage());
                });
    }
}
