package adapter;




import java.util.List;

import com.bizinfocus.happycityapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddLineAdapter extends BaseAdapter{
	private Context context;//������
	private List<String[]> LineList;//վ�㼯��
	private LayoutInflater inflater;//���������
	private ViewHolder holder;//����ViewHolder��
	
	//���캯��
		public AddLineAdapter(Context context,List<String[]> LineList) {
			this.context = context;
			this.LineList = LineList;
			//ʵ����LayoutInflater
			inflater = LayoutInflater.from(context);
		}	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return LineList.size();
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
	public View getView(int position, View convertView,ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			convertView = inflater.inflate(R.layout.dialog_bus_listview_item, null);
			holder = new ViewHolder();
			//��ø����ؼ���ʵ��
			holder.txtLineInfo= (TextView) convertView.findViewById(R.id.tv_line);
			holder.txtStationInfo = (TextView) convertView.findViewById(R.id.tv_station);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();					
		}
		String str[]= (String[])LineList.get(position);
		holder.txtLineInfo.setText(str[0]+"·");
		holder.txtStationInfo.setText(str[1]);
		return convertView;		
	}
	
	private final class ViewHolder{
		private TextView txtLineInfo;//��·��Ϣ 
		private TextView txtStationInfo;//վ����Ϣ
	}

}
