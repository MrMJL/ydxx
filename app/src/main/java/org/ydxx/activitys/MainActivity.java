package org.ydxx.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.ydxx.R;
import org.ydxx.controller.LocalDataSource;
import org.ydxx.entity.Declare;
import org.ydxx.entity.Jxzy;
import org.ydxx.entity.Mess;

/**
 * 主页面
 */
public class MainActivity extends BaseActivity {
	private ArrayList<HashMap<String, Object>> meumList = new ArrayList<HashMap<String, Object>>();
	private Declare declare;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		declare = (Declare) this.getApplicationContext();
		setContentView(R.layout.activity_main);

		GridView gridview = (GridView) findViewById(R.id.MainActivityGrid);

		HashMap<String, Object> map = new HashMap<String, Object>();

		if(declare.getUser().getType().equals("3")){
			
		
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
				new String[] { "ItemImage", "ItemText" }, // 对应map的Key
				new int[] { R.id.ItemImage, R.id.ItemText }); // 对应R的Id

		// 添加Item到网格中
		gridview.setAdapter(saItem);
		// 添加点击事件
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
				
				if (meumList.get(index).get("ItemText").toString().equals("课程列表")) {
					Intent intent = new Intent(MainActivity.this, JxzyActivity.class);
					startActivity(intent);
					MainActivity.this.finish();
				}
				
				if (meumList.get(index).get("ItemText").toString().equals("我的课程")) {
					if (declare.getUser().getType().equals("3")) {
						Intent intent = new Intent(MainActivity.this, XsdyActivity.class);
						startActivity(intent);
						MainActivity.this.finish();
					}else {
						Intent intent = new Intent(MainActivity.this, JxzyActivity.class);
						startActivity(intent);
						MainActivity.this.finish();
					}
				}

				if (meumList.get(index).get("ItemText").toString().equals("在线交流")) {
					Intent intent = new Intent(MainActivity.this, MessActivity.class);
					startActivity(intent);
					MainActivity.this.finish();
				}

				if (meumList.get(index).get("ItemText").toString().equals("我的信息")) {
					Intent intent = new Intent(MainActivity.this, UpdateUserActivity.class);
					startActivity(intent);
					MainActivity.this.finish();
				}

				if (meumList.get(index).get("ItemText").toString().equals("退出")) {
					MainActivity.this.finish();
				}

			}
		});
	}
}
