package com.bizinfocus.happycityapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Menu3Fragment extends Fragment{
	static Animation myAnimation;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.fragment_menu_3, container, false);
	final LinearLayout layout=(LinearLayout) rootView.findViewById(R.id.layout_main_bus);
	layout.setAnimation(myAnimation);
	layout.setOnClickListener(new OnClickListener(){
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			layout.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.item_anim));
			Intent intent = new Intent();
			intent.setClass(getActivity(), BusActivity.class);
			//getActivity().startActivity(intent);
		}
		
	});
	return rootView;
	}
	
	public static Animation loadAnimation (Context context, int id){
	//��һ������ContextΪ�����������    
	//�ڶ�������idΪ����XML�ļ�������
	//���ӣ�
	myAnimation = AnimationUtils.loadAnimation(context,R.anim.item_anim);
	//ʹ��AnimationUtils��ľ�̬����loadAnimation()������XML�еĶ���XML�ļ�
	return myAnimation;
}

}