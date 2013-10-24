package adapter;

import java.util.List;

import com.bizinfocus.happycityapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StationAdapter extends BaseAdapter {

	private List<?> list;// 站点集合
	private LayoutInflater inflater;// 布局填充器
	private ViewHolder holder;// 声明ViewHolder类
	// 构造函数
	public StationAdapter(Context context, List<?> list) {
		this.list = list;
		//this.map= map;
		// 实例化LayoutInflater
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 如果convertView为null
		if (position % 2 == 0) {
			convertView = inflater
					.inflate(R.layout.activity_bus_listitem, null);
			convertView.setBackgroundResource(R.color.custom_bule);
			holder = new ViewHolder();
			// 获得各个控件的实例
			holder.txtStationStartInfo = (TextView) convertView
					.findViewById(R.id.tv_station_start);
			holder.txtStationDstInfo = (TextView) convertView
					.findViewById(R.id.tv_station_dst);
			holder.txtLineInfo = (TextView) convertView
					.findViewById(R.id.tv_line);
			holder.txtStation = (TextView) convertView
					.findViewById(R.id.tv_station);
			holder.ivtick = (ImageView) convertView.findViewById(R.id.iv_tick);
			holder.tvmainstation=(TextView)convertView.findViewById(R.id.tv_mainstation);
			convertView.setTag(holder);
		} else {
			convertView = inflater
					.inflate(R.layout.activity_bus_listitem, null);
			convertView.setBackgroundResource(R.color.custom_green);
			holder = new ViewHolder();
			// 获得各个控件的实例
			holder.txtStationStartInfo = (TextView) convertView
					.findViewById(R.id.tv_station_start);
			holder.txtStationDstInfo = (TextView) convertView
					.findViewById(R.id.tv_station_dst);
			holder.txtLineInfo = (TextView) convertView
					.findViewById(R.id.tv_line);
			holder.txtStation = (TextView) convertView
					.findViewById(R.id.tv_station);
			holder.ivtick = (ImageView) convertView.findViewById(R.id.iv_tick);
			holder.tvmainstation=(TextView)convertView.findViewById(R.id.tv_mainstation);
			convertView.setTag(holder);
		}

		String[] str = (String[]) list.get(position);
		// 设置内容
		if (str.length > 0) {
			try {
				holder.txtLineInfo.setText(str[0]);
				holder.txtStationStartInfo.setText(str[1]);
				holder.txtStationDstInfo.setText(str[2]);
				holder.txtStation.setText(str[3]);
				if (str[4] != null) {
					String bool = str[4];
					int i = Integer.parseInt(bool);
					if (i == 1) {
						holder.ivtick.setVisibility(0);
						holder.tvmainstation.setVisibility(0);
						holder.tvmainstation.setText("首页站点");
					} else {
						holder.ivtick.setVisibility(4);
						holder.tvmainstation.setVisibility(4);
					}
				}else{
					holder.ivtick.setVisibility(4);
					holder.tvmainstation.setVisibility(4);
				}

			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("ArrayIndexOutOfBoundsException");
			}
			//holder.cbmain.setChecked(map.get(position) == null ? false : true);
			return convertView;
		} else
			holder.txtLineInfo.setText("抱歉，未找到常用站点信息");
			holder.txtStationDstInfo.setText("");
		return convertView;
	}

	public static class ViewHolder {
		private TextView txtStationStartInfo;// 站点起始信息
		private TextView txtStationDstInfo;// 站点截止信息
		private TextView txtLineInfo;// 线路信息
		private TextView txtStation;// 站点信息
		public ImageView ivtick;// 是否首页显示
		public TextView tvmainstation; //首页显示
	}

}
