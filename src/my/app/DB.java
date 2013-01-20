package my.app;

import java.io.File;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;



public class DB {
	
	File dbDir = null;
	File dbFile = null;
	SQLiteDatabase db = null;
	String sql = "create table tab_main (_id INTEGER PRIMARY KEY autoincrement,name VARCHAR ," +
			" phone INTEGER," +
			" address VARCHAR," +
			" doorno VARCHAR," +
			" Location VARCHAR," +
			" balancesign VARCHAR," +
			" balance INTEGER," +
			" stdsign VARCHAR," +
			" stdamount INTEGER," +
			" disconnect VARCHAR," +
			" connectiondate VARCHAR," +
			" freeconnection VARCHAR," +
			" offeramount INTEGER )";
	 
	String sql1 = " CREATE table location(_id INTEGER PRIMARY KEY autoincrement,area VARCHAR ) ";
	
	String sql2="CREATE  TABLE bill (_id INTEGER , date VARCHAR, amount INTEGER)";
	                   
	String sql3="CREATE  TABLE stdamount(_id INTEGER PRIMARY KEY  AUTOINCREMENT ,date VARCHAR, amount INTEGER)";
	
	public SQLiteDatabase open() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			dbDir = Environment.getExternalStorageDirectory();
			dbFile = new File(dbDir, "naresh.db");
		} else {
			return null;
		}
		if (dbFile.exists()) {
			
			return openDB();
		} else {
			return createDB();
		}
	}

	private SQLiteDatabase openDB() {
		 db = SQLiteDatabase.openDatabase(dbFile.toString(),
				null, SQLiteDatabase.OPEN_READWRITE);
		return db;
	}

	private SQLiteDatabase createDB() {
		db = SQLiteDatabase.openOrCreateDatabase(dbFile,
				null);
		db.close();
	    db = SQLiteDatabase.openDatabase(dbFile.toString(),
				null, SQLiteDatabase.OPEN_READWRITE);
	
		db.execSQL(sql);
		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);

		return db;

	}

	public Cursor fetchAllData() {
		// TODO Auto-generated method stub
		
		Cursor c=db.query("tab_main", new String[] {"_id","name"}, null, null, null, null, null);
	
		return c;
		}

	public void close() {
		// TODO Auto-generated method stub
		db.close();
	}

	public Cursor fetchAllArea() {
		// TODO Auto-generated method stub
		Cursor c=db.query("location", new String[] {"_id","area"}, null, null, null, null, null);
		
		return c;
	}

	public Cursor fetchSearchData(String stext, String slist) {
		// TODO Auto-generated method stub
		
		Cursor c=db.rawQuery("select _id,name from tab_main where "+slist +" like '%"+stext+"%'", null);
		return c;
	}
	public Cursor fetchIdBalance(int id) {
		Cursor c=db.rawQuery("select _id,balance from tab_main where _id = "+id, null);
		return c;
	}

	public int insertLocation(String location) {
		// TODO Auto-generated method stub
		try{
		db.execSQL("INSERT INTO location (\"area\") VALUES (\""+location+"\")");
		return 1;
		}catch(Exception e){return 0;
		}
		
	}

	public int deleteLocation(String location) {
		// TODO Auto-generated method stub
		try{
			db.execSQL("DELETE from location where area= \""+location+"\"");
			return 1;
			}catch(Exception e){return 0;
			}
	}

	public Cursor fetchIdStd(int id) {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("select  _id,stdsign,stdamount from tab_main where _id = "+id, null);
		return c;
	}

	public int getLatestMonthStdAmount() {
		// TODO Auto-generated method stub
		
		Cursor c=db.rawQuery("select _id,amount from stdamount order by _id", null);
		c.moveToLast();
		return c.getInt(1);
	}

	public Cursor fetchReportAllData(int balance) {
		// TODO Auto-generated method stub
		
		Cursor c=db.rawQuery("SELECT _id,name from tab_main where balance >= "+balance, null);
		return c;
	}

	public Cursor fetchReportData(int balance, String loc) {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("SELECT _id,name from tab_main where balance >= "+balance +" AND location= \""+loc+"\"", null);
		return c;
	}

	public Cursor fetchAllUserData() {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("SELECT * from tab_main order by _id", null);
		return c;
	}

	public Cursor fetchAllUserDataWithLoc(String loc) {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("SELECT * from tab_main where location =\""+loc+"\"", null);
		return c;
	}

	public Cursor fetchBillData(int id) {
		// TODO Auto-generated method stub
		
		Cursor c=db.rawQuery("SELECT _id,date,amount from bill where _id =\""+id+"\"", null);
		return c;
	}

	public Cursor fetchNameFromId(int id) {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("SELECT _id,name from tab_main where _id =\""+id+"\"", null);
		return c;
	}

	public String fetchLatestMonth() {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("select _id,date from stdamount order by _id", null);	
		c.moveToLast();
		String s=c.getString(1);db.close();
		return s;
	}

	public int insertAmount(Integer amt,String date) {
		// TODO Auto-generated method stub
		try{
			db.execSQL("INSERT INTO stdamount (\"date\",\"amount\") VALUES (\""+date+"\","+amt+")");
			return 1;
			}catch(Exception e){return 0;
			}
		
	}

	public int insertBill(int id, String tempdate, int amounts) {
		// TODO Auto-generated method stub
		try{
			db.execSQL("INSERT INTO bill (\"_id\",\"date\",\"amount\") VALUES ("+id+",\""+tempdate+"\","+amounts+")");
			return 1;
			}catch(Exception e){return 0;
			}
	}

	public String fetchCustomerBalanceSign(int id) {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("SELECT _id,balancesign from tab_main where _id =\""+id+"\"", null);
		c.moveToNext();
		String sign=c.getString(1);
		return sign;
	}

	public void UpdateAddCustomerBalance(int ca, int cusId) {
		// TODO Auto-generated method stub
	
		db.execSQL("UPDATE  tab_main SET \"balance\" = balance + " +ca+ " WHERE  \"_id\" = "+ cusId);	
		//db.execSQL("UPDATE tab_main SET balance= 100 where  _id=3");
		Log.w("db","ok");
	}

	public void UpdateSubCustomerBalance(int ca, int cusId) {
		// TODO Auto-generated method stub
		db.execSQL("UPDATE  tab_main SET \"balance\" = balance - " +ca+ " WHERE  \"_id\" = "+ cusId);
		
	}

	public int fetchLatestMonthAmount() {
		// TODO Auto-generated method stub
		Cursor c=db.rawQuery("select _id,amount from stdamount order by _id", null);	
		c.moveToLast();
		return c.getInt(1);
		
	}

	public void UpdateSubCustomerBalance1(int ca, int cusId) {
		// TODO Auto-generated method stub
		db.execSQL("UPDATE  tab_main SET \"balance\" =  " +ca+ " - balance WHERE  \"_id\" = "+ cusId);
	}

	public void UpdateBalanceSignPlus(int cusId) {
		// TODO Auto-generated method stub
		db.execSQL("UPDATE  tab_main SET \"balancesign\" =\"+\" WHERE  \"_id\" = "+ cusId);
	}
	public void UpdateBalanceSignMins(int cusId) {
		// TODO Auto-generated method stub
		db.execSQL("UPDATE  tab_main SET \"balancesign\" =\"-\" WHERE  \"_id\" = "+ cusId);
	}
}
