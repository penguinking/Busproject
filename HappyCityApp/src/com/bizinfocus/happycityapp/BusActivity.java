package com.bizinfocus.happycityapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockDialogFragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.view.Menu;
import logic.InsertDataBase;
import logic.DeleteData;
import logic.SelectLineData;
import adapter.AddLineAdapter;

public class BusActivity extends SherlockActivity {

	private adapter.CommonStationAdapter adapter;
	private ListView ls_station_info;
	// public HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	private Context context;
	private List<?> list;
	private EditText et_line;
	private EditText et_station;
	private ListView ls_info;
	private AddLineAdapter Addadapter;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("添加常用站点").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(
			com.actionbarsherlock.view.MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		switch (id) {
		case android.R.id.home:
			BusActivity.this.finish();
			return true;
		case 0:
			AddStationDialog();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		getSupportActionBar().setTitle("公交查询");
		ls_station_info = (ListView) findViewById(R.id.lv_station_info);
		initListView();

		OnItemClickListener listItemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				/**
				 * checkbox单选实现 map.clear(); map.put(position,100);
				 * adapter.notifyDataSetChanged();
				 **/
				Toast.makeText(getApplicationContext(), "添加常用站点信息",
						Toast.LENGTH_SHORT).show();
			}
		};
		ls_station_info.setOnItemClickListener(listItemClickListener);

		/**
		 * 添加listview滑动接听
		 
		ls_station_info.setOnTouchListener(new OnTouchListener() {
			float x, y, upx, upy;

			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					x = event.getX();
					y = event.getY();
				}
				if (event.getAction() == MotionEvent.ACTION_UP) {
					upx = event.getX();
					upy = event.getY();
					int position1 = ((ListView) view).pointToPosition((int) x,
							(int) y);
					int position2 = ((ListView) view).pointToPosition(
							(int) upx, (int) upy);

					if (position1 == position2 && (upx - x) > 150) {
						Deldialog((ListView) view, position1);

					}
				}
				return false;
			}

		});
		**/
		ls_station_info.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				// TODO Auto-generated method stub
				Deldialog(view, position);
				return true;
			}
			
		});
	}

	private void initListView() {
		new ArrayList<HashMap<String, Object>>();
		list = logic.CommonLineLogic.getList(BusActivity.this);
		if (list.size() > 0) {

			adapter = new adapter.CommonStationAdapter(getApplicationContext(),
					list);
			ls_station_info.setAdapter(adapter);
		} else
			Toast.makeText(getApplicationContext(), "请添加常用站点信息",
					Toast.LENGTH_SHORT).show();
	}

	/**
	 * 删除item，并播放动画
	 * 
	 * @param rowView
	 *            播放动画的view
	 * @param positon
	 *            要删除的item位置
	 */
	protected void removeListItem(View rowView, final int positon) {

		final Animation animation = (Animation) AnimationUtils.loadAnimation(
				rowView.getContext(), R.anim.item_anim);
		animation.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
			}

			public void onAnimationRepeat(Animation animation) {
			}

			public void onAnimationEnd(Animation animation) {
				list.remove(positon);
				adapter.notifyDataSetChanged();
				animation.cancel();
			}
		});

		rowView.startAnimation(animation);

	}

	protected void Deldialog(final View view, final int position) {
		AlertDialog.Builder builder = new Builder(BusActivity.this);
		builder.setMessage("确定删除该常用站点？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				
				TextView tv_line = (TextView) view.findViewById(R.id.tv_line);
				TextView tv_station = (TextView) view
						.findViewById(R.id.tv_station);
				String line = (String) tv_line.getText();
				String curr_station = (String) tv_station.getText();
				DeleteData.DeleteData(context, line, curr_station);
				removeListItem(view, position);
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});

		builder.create().show();
	}

	protected void AddStationDialog() {
		LayoutInflater flater = LayoutInflater.from(this);
		View view = flater.inflate(R.layout.station_dialog, null);
		AlertDialog.Builder builder = new Builder(BusActivity.this);
		et_line = (EditText)view.findViewById(R.id.et_line);
		et_station = (EditText)view.findViewById(R.id.et_station);
		ls_info=(ListView)view.findViewById(R.id.lv_info);
		et_line.addTextChangedListener(LineWatcher);
		et_station.addTextChangedListener(StationWatcher);
		builder.setView(view);
		builder.setTitle("添加常用站点");
		builder.setPositiveButton("确认", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				InsertDataBase.InsertData(context, "火车站", "汽车站", et_line.getText().toString(), et_station.getText().toString(), "0");
				initListView();
				ls_station_info.invalidate();
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		ls_info.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				TextView tv_line = (TextView)view.findViewById(R.id.tv_line);
				TextView tv_station = (TextView)view.findViewById(R.id.tv_station);
				et_line.setText(tv_line.getText());
				et_station.setText(tv_station.getText());
			}
			
		});

		builder.create().show();
	}
	
	private TextWatcher LineWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			String line = et_line.getText().toString();
			List<String[]> list = SelectLineData.SelectLineStationData(BusActivity.this, line);
			Addadapter = new AddLineAdapter(BusActivity.this,list);
			ls_info.setAdapter(Addadapter);
			ls_info.invalidate();
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}};
		
		private TextWatcher StationWatcher = new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}};
}
