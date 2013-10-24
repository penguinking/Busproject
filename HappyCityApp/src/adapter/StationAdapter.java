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

	private List<?> list;// վ�㼯��
	private LayoutInflater inflater;// ���������
	private ViewHolder holder;// ����ViewHolder��
	// ���캯��
	public StationAdapter(Context context, List<?> list) {
		this.list = list;
		//this.map= map;
		// ʵ����LayoutInflater
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
		// ���convertViewΪnull
		if (position % 2 == 0) {
			convertView = inflater
					.inflate(R.layout.activity_bus_listitem, null);
			convertView.setBackgroundResource(R.color.custom_bule);
			holder = new ViewHolder();
			// ��ø����ؼ���ʵ��
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
			// ��ø����ؼ���ʵ��
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
		// ��������
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
						holder.tvmainstation.setText("��ҳվ��");
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
			holder.txtLineInfo.setText("��Ǹ��δ�ҵ�����վ����Ϣ");
			holder.txtStationDstInfo.setText("");
		return convertView;
	}

	public static class ViewHolder {
		private TextView txtStationStartInfo;// վ����ʼ��Ϣ
		private TextView txtStationDstInfo;// վ���ֹ��Ϣ
		private TextView txtLineInfo;// ��·��Ϣ
		private TextView txtStation;// վ����Ϣ
		public ImageView ivtick;// �Ƿ���ҳ��ʾ
		public TextView tvmainstation; //��ҳ��ʾ
	}

}
