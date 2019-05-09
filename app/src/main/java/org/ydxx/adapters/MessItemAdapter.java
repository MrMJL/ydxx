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
import org.ydxx.entity.Mess;

public class MessItemAdapter extends BaseAdapter {
	private LayoutInflater listContainer;
	private List<Mess> items = new ArrayList<Mess>();

	public MessItemAdapter(Context context) {
		listContainer = LayoutInflater.from(context);
	}

	private final class item {
		public TextView tv_mess_fuser;
		public TextView tv_mess_fmess;
		public TextView tv_mess_tuser;
		public TextView tv_mess_tmess;

	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Mess getItem(int position) {
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
			convertView = listContainer.inflate(R.layout.mess_item, null);
			targetItem = new item();
			targetItem.tv_mess_fuser = (TextView) convertView.findViewById(R.id.tv_mess_fuser);
			targetItem.tv_mess_fmess = (TextView) convertView.findViewById(R.id.tv_mess_fmess);
			targetItem.tv_mess_tuser = (TextView) convertView.findViewById(R.id.tv_mess_tuser);
			targetItem.tv_mess_tmess = (TextView) convertView.findViewById(R.id.tv_mess_tmess);

			convertView.setTag(targetItem);
		} else {
			targetItem = (item) convertView.getTag();
		}
		if (!items.isEmpty()) {
			Mess item = this.items.get(position);
			targetItem.tv_mess_fuser.setText(item.getFusername()+"说:");
			targetItem.tv_mess_fmess.setText(item.getFmessage());
			
			if(item.getTusername()!=null && !item.getTusername().equals("null")){
				targetItem.tv_mess_tuser.setText(item.getTusername()+"说:");
				targetItem.tv_mess_tmess.setText(item.getTmessage());
			}else{
				targetItem.tv_mess_tuser.setText("");
				targetItem.tv_mess_tmess.setText("");
			}
			
			

		}
		return convertView;
	}

	public List<Mess> getItems() {
		return items;
	}

	public void setItems(List<Mess> items) {
		this.items = items;
	}

}
