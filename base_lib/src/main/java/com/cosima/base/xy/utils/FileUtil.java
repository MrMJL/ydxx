package com.cosima.base.xy.utils;

import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by biyunlong on 2016/7/29.
 */
public class FileUtil {
    //创建文件夹及文件
    public static File CreateFile(String path, String name) {
        File file = createDirect(path);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
            }
        }
        File dir = new File(file.getAbsolutePath() + File.separator + name);
        if (!dir.exists()) {
            try {
                dir.createNewFile();
            } catch (Exception e) {
            }
        }
        return dir;
    }

    public static File createImageFilePath() {
        String file_path = Environment.getExternalStorageDirectory().toString() + "/money_bear";
        File file = createDirect(file_path);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
            }
        }

        long time = System.currentTimeMillis();
        String t = new SimpleDateFormat("yyyy_MM_dd").format(new Date(time));
        String d = new SimpleDateFormat("HH_mm").format(new Date(time));

        // 压缩后图片保存的文件名
        String fileName = "img" + "_" + t + "_" + d + ".jpg";

        File dir = new File(file.getAbsolutePath() + File.separator + fileName);
        if (!dir.exists()) {
            try {
                dir.createNewFile();
            } catch (Exception e) {
            }
        }

        return dir;
    }

    //创建文件夹
    public static File createDirect(String path) {
        String filePath =
            Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
            }
        }
        return file;
    }

    public static boolean deleteFile(String path) {
        boolean flag = false;
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path;
        File file = new File(path);
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    //监测，删除，新建
    public static File cac(String path, String name) {
        String str = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator
            + path
            + File.separator
            + name;
        File file = new File(str);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        return CreateFile(path, name);
    }

    //获取文件夹下的文件列表
    public static List getFileName(String path) {
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path;
        List<String> list = new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            return null;
        }
        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
            } else {
                list.add(fs.getName());
            }
        }
        return list;
    }

    public static boolean isFileExist(String path) {
        String filePath =
            Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + path;
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }
}

