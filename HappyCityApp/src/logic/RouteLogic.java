package logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import util.UrlUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * ��·��ѯ
 * @author Administrator
 *
 */
public class RouteLogic implements Runnable {
	
	Handler handler;
	private String route;//��·����
	
	public RouteLogic(Handler handler,String route) {
		super();
		this.handler = handler;
		this.route = route;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {			
			// ���ݵ�ַ���÷����
			// ���ݵ�ַ���÷����
			String url="http://cybus.bizinfocus.com:8080/searchXl";		
			
			UrlUtil uu=new UrlUtil();
	        List<NameValuePair> params = new ArrayList<NameValuePair>();	       
	        params.add(new BasicNameValuePair("xlbh", route));			
	        String r=uu.doPost(url, params);
	        String r1=r.replace("|", ",");
	        String []rs=  r1.split(",");
			
			Message msg = prepareMessage(rs);
			// message�������ӵ����̵߳�MQ��          
			handler.sendMessage(msg);				        
				
			
		} catch (Exception e) { 
			//Log.d(TAG, "interrupted!");        
		}   
	}
	
	
	private Message prepareMessage(String[] list) {
		Message result = handler.obtainMessage();
		Bundle data = new Bundle(); 		 
		data.putStringArray("list", list);
		
		result.setData(data);
		return result;
		
	}

}