package my.app;

import java.util.Calendar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddMonthActivity extends Activity {
	
EditText amount=null;
	TextView date=null;
	Button Add=null;
	String nextdate=null;
	Calendar c;
	int cyear, cday, cmonth,cmin;
	
	@Override
	public void onCreate(Bundle bubd) {

		super.onCreate(bubd);
		setContentView(R.layout.addmonth);
		init();
		setDate();
		Add.setOnClickListener(submit);
	}

	private void setDate() {
		// TODO Auto-generated method stub
		try{
		Helper h=new Helper();
		nextdate=h.getLatestMonth();
		date.setText("For The Month "+ nextdate);
		}catch(Exception e){
			c = Calendar.getInstance();
			cyear = c.get(Calendar.YEAR);
			cmonth = c.get(Calendar.MONTH);
			cday = c.get(Calendar.DAY_OF_MONTH);
			nextdate=(cday + " - " + (cmonth + 1) + " - " + cyear);
			date.setText("For The Month "+ nextdate);}
	}

	private void init() {
		// TODO Auto-generated method stub
		amount=(EditText)findViewById(R.id.AddMonthText);
		Add=(Button)findViewById(R.id.AddMonthButton);
		date=(TextView)findViewById(R.id.AddMonthDate);
	}
	
View.OnClickListener submit= new View.OnClickListener() {
	int amt=0;
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			try{
				 amt=Integer.valueOf(amount.getText().toString());
			DB db = new DB();
			db.open();
			int d=db.insertAmount(amt,nextdate);
			if(d==1){
				Toast.makeText(AddMonthActivity.this,  " Added Successfully", Toast.LENGTH_LONG).show();
			}else{Toast.makeText(AddMonthActivity.this,  " Not Added Successfully", Toast.LENGTH_LONG).show();}
			db.close();
			
			changeUserBalance();
			}catch(Exception e){}
		}

		
	
	public void changeUserBalance() {
		// TODO Auto-generated method stub
		try{
		DB mydb=new DB();mydb.open();
		Cursor cid=mydb.fetchAllUserData();
		while(cid.moveToNext())
		{
			int cusId=cid.getInt(0);
			
			Helper h=new Helper();
			int ca=h.getcustomerAmount(cusId);
			String sign=h.getCustomerBalanceSign(cusId);
			if(sign.equalsIgnoreCase("+")){
				
				h.addBalanceAmount(ca,cusId);
				
			}else if(sign.equalsIgnoreCase("-")){
				
				int monthamt=ca;
				int cusbal=h.getBalance(cusId);
				if(monthamt>=cusbal){
					h.subBalanceAmount1(ca,cusId);
					h.changeBalanceSignPlus(cusId);
				}
				else
				{
					h.subBalanceAmount(ca,cusId);
					
				}
			}
		}
		mydb.close();
		cid.close();
		}
		catch(Exception e)
		{
			
		}
		
	}};
}
