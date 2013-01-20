package my.app;

import android.database.Cursor;
import android.util.Log;

public class Helper {

	
	Helper()
	{
		
		
	}

	public int getBalance(int id) {
		Cursor cur=null;
		DB db=new DB();
		
		db.open();
		cur=db.fetchIdBalance(id);
		
		cur.moveToNext();
		int temp=cur.getInt(1);
		db.close();
		return temp;
	}

	public int getcustomerAmount(int id) {
		// to get customer standard amount by adding or subtraction from standard amount of the month
		DB db=new DB();int temp;
		db.open();
		Cursor cur;
		cur=db.fetchIdStd(id);
		cur.moveToNext();
		int amt=db.getLatestMonthStdAmount();
		
	if(cur.getString(1).toString().equalsIgnoreCase("+")){
		temp= (amt+Integer.valueOf(cur.getString(2).toString()));
		
	}else{
		temp= (amt-Integer.valueOf(cur.getString(2).toString()));
	}
	db.close();
		return temp;
	}
	public String getCustomerBalanceSign(int id)
	{
		DB db=new DB();
		db.open();
		String sign=db.fetchCustomerBalanceSign(id);
		db.close();
		return sign;
		
		
	}
	public String getLatestMonth() {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		String pdate=db.fetchLatestMonth();
		String ndate=ChangeNextMonth(pdate);
		db.close();
		return ndate;
	}
	public int getLatestMonthAmount() {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		int amt=db.fetchLatestMonthAmount();
		db.close();
		
		return amt;
	}
	private String ChangeNextMonth(String pdate) {
		// TODO Auto-generated method stub
		String d=pdate;
		int s=d.indexOf("-");
		String mon=null;
		int a=d.charAt(s+2);char b=d.charAt(s+3);
		a=a-48;
		if(a==1&& !(b==' '))
		{
		if(b=='2'){mon="1 "; }
		if(b=='1'){mon="12 "; }
		if(b=='0'){ mon="11 ";}
		}else if(a==1&& (b==' ')){
			mon="2 ";
		}else if(a!=1){
			
			mon=++a+" ";
		}
		int temp = 0;
		String day = null;
		while(temp<s)	
		{if(day==null){day=d.charAt(temp)+"";}else{day+=d.charAt(temp);}
		temp++;
		}
		int e=d.indexOf("-",s+1);
		temp = e+1;
		String year= null;
		while(temp<d.length())	
		{if(year==null){year=d.charAt(temp)+"";}else{year+=d.charAt(temp);}
		temp++;}
		
		String nextMonth =day+"- "+mon+"-"+year;
		return nextMonth;
	}

	public void addBalanceAmount(int ca, int cusId) {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		db.UpdateAddCustomerBalance(ca,cusId);
		db.close();
	}

	public void subBalanceAmount(int ca, int cusId) {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		
		db.UpdateSubCustomerBalance(ca,cusId);
		db.close();
	}

	public void subBalanceAmount1(int ca, int cusId) {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		
		db.UpdateSubCustomerBalance1(ca,cusId);
		db.close();
	}

	public void changeBalanceSignPlus(int cusId) {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		
		db.UpdateBalanceSignPlus(cusId);
		db.close();
	}
	public void changeBalanceSignMins(int cusId) {
		// TODO Auto-generated method stub
		DB db=new DB();
		db.open();
		
		db.UpdateBalanceSignMins(cusId);
		db.close();
	}

	public Cursor getAllUser() {
		// TODO Auto-generated method stub
		DB mydb=new DB();mydb.open();
		Cursor c=mydb.fetchAllUserData();
		mydb.close();
		Log.w("helper", "end1");
		return c;
	}
	
	
}
