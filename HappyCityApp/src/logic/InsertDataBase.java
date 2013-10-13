package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import com.bizinfocus.happycityapp.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class InsertDataBase {
	private static final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "maindatabase.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.bizinfocus.happycityapp";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;  //在手机里存放数据库的位置
    public static void InsertData(Context context,String sta_start,String sta_dst,String line,String curr_station,String main_bool)
	{
		
		
		String sql="";
		
	    sql="INSERT INTO COMMONSTATION(LINE,STA_START,STA_DST,STATION,MAIN_BOOL)VALUES('"+line+"','"+sta_start+"','"+sta_dst+"','"+curr_station+"','"+main_bool+"')";					
		System.out.println(sql);
		
		if(!sql.equals(""))
		{	
			String dbfile=DB_PATH + "/" + DB_NAME;
			if (!(new File(dbfile).exists())) {//判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = context.getResources().openRawResource(R.raw.maindatabase); //欲导入的数据库
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
					
					System.out.println(e.toString()+"发生异常！");
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

