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
import org.ydxx.entity.Xsdy;

public class XsdyItemAdapter extends BaseAdapter {
	private LayoutInflater listContainer;
	private List<Xsdy> items = new ArrayList<Xsdy>();

	public XsdyItemAdapter(Context context) {
		listContainer = LayoutInflater.from(context);
	}

	private final class item {
		public TextView tv_item_xsdy_zymc;
		public TextView tv_item_xsdy_lsmc;
		

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Xsdy getItem(int position) {
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
			convertView = listContainer.inflate(R.layout.xsdy_item, null);
			targetItem = new item();
			targetItem.tv_item_xsdy_zymc = (TextView) convertView.findViewById(R.id.tv_item_xsdy_zymc);
			targetItem.tv_item_xsdy_lsmc = (TextView) convertView.findViewById(R.id.tv_item_xsdy_lsmc);
			

			convertView.setTag(targetItem);
		} else {
			targetItem = (item) convertView.getTag();
		}
		if (!items.isEmpty()) {
			Xsdy item = this.items.get(position);
			targetItem.tv_item_xsdy_zymc.setText(item.getJxzymc());
			targetItem.tv_item_xsdy_lsmc.setText(item.getExt2());
		}
		return convertView;
	}

	public List<Xsdy> getItems() {
		return items;
	}

	public void setItems(List<Xsdy> items) {
		this.items = items;
	}

}
