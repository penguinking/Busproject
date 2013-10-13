package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bizinfocus.happycityapp.R;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class SelectLineData {

	public static String[] LineList;
	private static final int BUFFER_SIZE = 400000;
	public static final String DB_NAME = "maindatabase.db"; // 保存的数据库文件名
	public static final String PACKAGE_NAME = "com.bizinfocus.happycityapp";
	public static final String DB_PATH = "/data"
			+ Environment.getDataDirectory().getAbsolutePath() + "/"
			+ PACKAGE_NAME; // 在手机里存放数据库的位置

	public static List<String[]> SelectLineStationData(Context context, String line) {
		String sql = "";
		sql = "SELECT DISTINCT xid,zhan from cnbus where xid like '%" + line + "%'";
		if (!sql.equals("")) {
			String dbfile = DB_PATH + "/" + DB_NAME;
			if (!(new File(dbfile).exists())) {// 判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
				InputStream is = context.getResources().openRawResource(
						R.raw.maindatabase); // 欲导入的数据库
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

					System.out.println(e.toString() + "发生异常！");
				}

			}

			SQLiteDatabase database;
			// DBManager dbHelper;
			database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
			Cursor cur = database.rawQuery(sql, null);
			List<String[]> list = new ArrayList<String[]>();
			for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
				String s[] = new String[2];
				s[0] = cur.getString(0);
				s[1] = cur.getString(1);
				list.add(s);
			}
			cur.close();
			database.close();
			return list;
			

		} else {
			return null;
		}

	}
}
