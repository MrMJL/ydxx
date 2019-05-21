package org.ydxx.activitys;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Ignore;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ydxx.R;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;
import org.ydxx.net.MainService;
import org.ydxx.net.RetrofitClient;
import org.ydxx.utils.Tools;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {
    private ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
    private Declare declare;

    private Jxzy jxzy = new Jxzy();
    private Gson gson;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        declare = (Declare) this.getApplicationContext();
        setContentView(R.layout.activity_main);
        GridView gridview = (GridView) findViewById(R.id.MainActivityGrid);

        HashMap<String, Object> map = new HashMap<String, Object>();
        gson = new Gson();
        if (declare.getUser().getType().equals("3")) {


            map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.full_page);
            map.put("ItemText", "课程列表");
            meumList.add(map);
        }

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.edit_page);
        map.put("ItemText", "我的课程");
        meumList.add(map);

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.add_to_shopping_cart);
        map.put("ItemText", "在线交流");
        meumList.add(map);

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.edit_profile);
        map.put("ItemText", "我的信息");
        meumList.add(map);

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.block);
        map.put("ItemText", "退出");
        meumList.add(map);

        SimpleAdapter saItem = new SimpleAdapter(this, meumList, // 数据源
                R.layout.main_item, // xml实现
                new String[]{"ItemImage", "ItemText"}, // 对应map的Key
                new int[]{R.id.ItemImage, R.id.ItemText}); // 对应R的Id

        // 添加Item到网格中
        gridview.setAdapter(saItem);
        // 添加点击事件
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {

                if (meumList.get(index).get("ItemText").toString().equals("课程列表")) {
                    Gson gson = new Gson();
                    Map<String, String> data = new HashMap<>();
                    //jxzy
                    data.put("kcmc", "高数 No.2");
                    data.put("lsxm", "明明 No.2");
                    data.put("ext1", "https://www.imooc.com/video/16896");
                    data.put("lsid", "高数 No.2");
                    data.put("ext2", "高数 No.2");
                    data.put("ext3", "https://www.imooc.com/video/16896");

                    //xsdy
//                    data.put("jxzyid", "高数 No.2");
//                    data.put("jxzymc", "明明 No.2");
//                    data.put("xsid", "明明 No.2");
//                    data.put("xsmc", "高数 No.2");
//                    data.put("ext1", "https://www.imooc.com/video/16896");
//                    data.put("ext2", "高数 No.2");
//                    data.put("ext3", "https://www.imooc.com/video/16896");

                    //mess
//                    data.put("fuserid", "高数 No.2");
//                    data.put("fusername", "明明 No.2");
//                    data.put("fmessage", "明明 No.2");
//                    data.put("tuserid", "高数 No.2");
//                    data.put("tusername", "高数 No.2");
//                    data.put("tmessage", "高数 No.2");
//                    data.put("ext1", "https://www.imooc.com/video/16896");
//                    data.put("ext2", "高数 No.2");
//                    data.put("ext3", "https://www.imooc.com/video/16896");

                    //user
//                    data.put("username", "高数 No.2");
//                    data.put("sex", "明明 No.2");
//                    data.put("age", "https://www.imooc.com/video/16896");
//                    data.put("password", "高数 No.2");
//                    data.put("email", "高数 No.2");
//                    data.put("isenable", "https://www.imooc.com/video/16896");
//                    data.put("type", "https://www.imooc.com/video/16896");
//                    data.put("classid", "https://www.imooc.com/video/16896");
//                    data.put("classname", "https://www.imooc.com/video/16896");

                    String json = gson.toJson(data);

                    Tools.addData("jxzy", json);
                    return;
//					Intent intent = new Intent(MainActivity.this, JxzyActivity.class);
//					startActivity(intent);
//					MainActivity.this.finish();
                }

                if (meumList.get(index).get("ItemText").toString().equals("我的课程")) {
                    Tools.getData("jxzy");
                    return;
//					if (declare.getUser().getType().equals("3")) {
//						Intent intent = new Intent(MainActivity.this, XsdyActivity.class);
//						startActivity(intent);
//						MainActivity.this.finish();
//					}else {
//						Intent intent = new Intent(MainActivity.this, JxzyActivity.class);
//						startActivity(intent);
//						MainActivity.this.finish();
//					}
                }

                if (meumList.get(index).get("ItemText").toString().equals("在线交流")) {
                    Map<String, Object> data = new HashMap<>();
                    //jxzy
//                    data.put("id", "1");
//                    data.put("kcmc", "高数 No.10");
//                    data.put("lsxm", "明明 No.10");
//                    data.put("ext1", "https://www.imooc.com/video/16896");
//                    data.put("lsid", "高数 No.2");
//                    data.put("ext2", "高数 No.2");
//                    data.put("ext3", "https://www.imooc.com/video/16896");

                    //xsdy
                    data.put("id", "1");
                    data.put("jxzyid", "高数 No.20");
                    data.put("jxzymc", "明明 No.20");
                    data.put("xsid", "明明 No.20");
                    data.put("xsmc", "高数 No.20");
                    data.put("ext1", "https://www.imooc.com/video/16896");
                    data.put("ext2", "高数 No.20");
                    data.put("ext3", "https://www.imooc.com/video/16896");

                    //mess
//                    data.put("fuserid", "高数 No.20");
//                    data.put("fusername", "明明 No.20");
//                    data.put("fmessage", "明明 No.20");
//                    data.put("tuserid", "高数 No.20");
//                    data.put("tusername", "高数 No.20");
//                    data.put("tmessage", "高数 No.20");
//                    data.put("ext1", "https://www.imooc.com/video/16896");
//                    data.put("ext2", "高数 No.20");
//                    data.put("ext3", "https://www.imooc.com/video/16896");

                    //user
//                    data.put("username", "高数 No.2");
//                    data.put("sex", "明明 No.2");
//                    data.put("age", "https://www.imooc.com/video/16896");
//                    data.put("password", "高数 No.2");
//                    data.put("email", "高数 No.2");
//                    data.put("isenable", "https://www.imooc.com/video/16896");
//                    data.put("type", "https://www.imooc.com/video/16896");
//                    data.put("classid", "https://www.imooc.com/video/16896");
//                    data.put("classname", "https://www.imooc.com/video/16896");

                    String json = gson.toJson(data);
                    Tools.updateData("xsdy", json);
                    return;
//					Intent intent = new Intent(MainActivity.this, MessActivity.class);
//					startActivity(intent);
//					MainActivity.this.finish();
                }

                if (meumList.get(index).get("ItemText").toString().equals("我的信息")) {
                    Map<String, String> data = new HashMap<>();
                    data.put("id", "1");
                    String json = gson.toJson(data);
                    Tools.delData("xsdy", json);
                    return;
//					Intent intent = new Intent(MainActivity.this, UpdateUserActivity.class);
//					startActivity(intent);
//					MainActivity.this.finish();
                }

                if (meumList.get(index).get("ItemText").toString().equals("退出")) {
                    MainActivity.this.finish();
                }

            }
        });
    }
}
