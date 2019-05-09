package org.ydxx.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.ydxx.R;
import org.ydxx.entity.Jxzy;

public class JxzyItemAdapter extends BaseAdapter {
	private LayoutInflater listContainer;
	private List<Jxzy> items = new ArrayList<Jxzy>();

	public JxzyItemAdapter(Context context) {
		listContainer = LayoutInflater.from(context);
	}

	private final class item {
		public TextView tv_item_jxzy_zymc;
		public TextView tv_item_jxzy_lsmc;
		

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Jxzy getItem(int position) {
		if (!items.isEmpty()) {
			return items.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		item targetItem = null;
		if (convertView == null) {
			convertView = listContainer.inflate(R.layout.jxzy_item, null);
			targetItem = new item();
			targetItem.tv_item_jxzy_zymc = (TextView) convertView.findViewById(R.id.tv_item_jxzy_zymc);
			targetItem.tv_item_jxzy_lsmc = (TextView) convertView.findViewById(R.id.tv_item_jxzy_lsmc);
			

			convertView.setTag(targetItem);
		} else {
			targetItem = (item) convertView.getTag();
		}
		if (!items.isEmpty()) {
			Jxzy item = this.items.get(position);
			targetItem.tv_item_jxzy_zymc.setText(item.getKcmc());
			targetItem.tv_item_jxzy_lsmc.setText(item.getLsxm());
		}
		return convertView;
	}

	public List<Jxzy> getItems() {
		return items;
	}

	public void setItems(List<Jxzy> items) {
		this.items = items;
	}

}
