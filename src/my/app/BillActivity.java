package my.app;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BillActivity extends Activity {
	
	Button go=null;
	Button billdate=null;
	Button pay=null;
	EditText customerid=null;
	EditText amount=null;
	TextView tv=null;
	String amt=null;
	int id;
	int balance,customerAmount;
	
	Calendar c;
	int cyear, cday, cmonth;
	static final int DATE_DIALOG_ID = 0;
	Cursor cur;
	@Override
	public void onCreate(Bundle bubd) {
		super.onCreate(bubd);
		setContentView(R.layout.bill);
		intVar();
		setdate();
		go.setOnClickListener(goClick);
		pay.setOnClickListener(payClick);
	}

	private void intVar() {
		// TODO Auto-generated method stub
		go =(Button)findViewById(R.id.PayGo);
		billdate=(Button)findViewById(R.id.PayDate);
		pay=(Button)findViewById(R.id.Pay);
		customerid=(EditText)findViewById(R.id.PayId);
		amount=(EditText)findViewById(R.id.PayAmount);
		tv=(TextView)findViewById(R.id.PayTextView);
	}
	
	View.OnClickListener goClick =new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			try{
				Helper help=new Helper();
			
			id=Integer.parseInt(customerid.getText().toString());
			
			balance=help.getBalance(id);
			
			customerAmount=help.getcustomerAmount(id);
			if(help.getCustomerBalanceSign(id).equalsIgnoreCase("+")){
				tv.setText("Balance "+ balance);
			}else{
				tv.setText("Return "+ balance);
			}
			
			amount.setText(customerAmount+"");
			}catch(Exception e){
				Log.w("catched", e.getMessage());
			}
		}
	};
	void alert()
	{
		 AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
	        dlgAlert.setMessage("PayMent Cannot be Accepted");
	        dlgAlert.setTitle("Sorry");
	        dlgAlert.setPositiveButton("OK", null);
	        dlgAlert.setCancelable(true);
	        dlgAlert.create().show();
	}
	View.OnClickListener payClick =new View.OnClickListener() {
		int amounts=0;
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Helper h=new Helper();
			String sign=h.getCustomerBalanceSign(id);
			if(sign.equalsIgnoreCase("-")){
				alert();
			}
			else{
			String tempdate=billdate.getText().toString();
			 amounts=Integer.valueOf(amount.getText().toString());
			DB mydb=new DB();
			mydb.open();
			mydb.insertBill(id,tempdate,amounts);
			mydb.close();
			ChangeUserData();			}
		}

	

		private void ChangeUserData() {
			// TODO Auto-generated method stub
			try
			{
				Helper h=new Helper();
				String sign=h.getCustomerBalanceSign(id);
				//int ca=h.getcustomerAmount(id);
				Toast.makeText(BillActivity.this, "Payed Successfully", Toast.LENGTH_LONG);
				if(sign.equalsIgnoreCase("-"))
				{
					h.addBalanceAmount(amounts,id);
				}else if(sign.equalsIgnoreCase("+"))
				{
					int monthamt=amounts;
					int cusbal=h.getBalance(id);
					if(monthamt<=cusbal){
						h.subBalanceAmount(amounts,id);
					}else
					{
						h.subBalanceAmount1(amounts,id);
						h.changeBalanceSignMins(id);
					}
				}
				
			}catch(Exception e)
			{
				Log.w("Change data","error");
			}
		}
	};
	
	private void setdate() {
		// TODO Auto-generated method stub
		
		c = Calendar.getInstance();
		cyear = c.get(Calendar.YEAR);
		cmonth = c.get(Calendar.MONTH);
		cday = c.get(Calendar.DAY_OF_MONTH);
		billdate.setText(cday + " - " + (cmonth + 1) + " - " + cyear);
		billdate.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, cyear, cmonth,
					cday);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		// onDateSet method
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			String date_selected = String.valueOf(dayOfMonth) + " - "
					+ String.valueOf(monthOfYear+1) + " - " + String.valueOf(year);
			billdate.setText(date_selected);
		}
	};
}
