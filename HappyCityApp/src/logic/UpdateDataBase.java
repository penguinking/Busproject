package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.bizinfocus.happycityapp.R;

public class UpdateDataBase {
	private static final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "maindatabase.db"; //��������ݿ��ļ���
    public static final String PACKAGE_NAME = "com.bizinfocus.happycityapp";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;  //���ֻ��������ݿ��λ��
    public static void UpdateData(Context context,String line,String curr_station)
	{
		
		
		String sql="";
	    sql="UPDATE COMMONSTATION SET main_bool = 0 WHERE station <> '"+curr_station+"' and line <> '"+line+"'";
		System.out.println(sql);
		
		if(!sql.equals(""))
		{	
			String dbfile=DB_PATH + "/" + DB_NAME;
			if (!(new File(dbfile).exists())) {//�ж����ݿ��ļ��Ƿ���ڣ�����������ִ�е��룬����ֱ�Ӵ����ݿ�
                InputStream is = context.getResources().openRawResource(R.raw.maindatabase); //����������ݿ�
                FileOutputStream fos;
				try {
					fos = new FileOutputStream(dbfile);
					byte[] buffer = new byte[BUFFER_SIZE];
	                int count = 0;
	                while ((count = is.read(buffer)) > 0) {
	                    fos.write(buffer, 0, count);
	                }
	                fos.close();
	                is.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					System.out.println(e.toString()+"�����쳣��");
				}
	               
	        }
			
			SQLiteDatabase database;
			//DBManager dbHelper;
			database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
			database.execSQL(sql);
			database.close();
			}
			
		}
}
