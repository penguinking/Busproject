package com.bizinfocus.happycityapp;



import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Menu1Fragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.fragment_menu_1, container, false);
	LinearLayout layout=(LinearLayout) rootView.findViewById(R.id.layout_main_bus);
	layout.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();
			intent.setClass(getActivity(), BusActivity.class);
			getActivity().startActivity(intent);
		}
		
	});
	return rootView;
	}
}
