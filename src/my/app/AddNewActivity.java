package my.app;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class AddNewActivity extends Activity { 

	EditText name;
	EditText phone;
	EditText address;
	EditText doorno;
	Spinner location;
	EditText balance;
	ImageButton stdsign;
	EditText stdamount;
	EditText noofconnection;
	Button date;
	Spinner disconnect = null;
	Spinner freeconnection;
	EditText offeramount;
	Button save;
	ImageButton balancesign;
	String sname, saddress, sdoorno, slocation, sbalance, sstdsign="+", sstdamount,
			sdisconnect, sfreeconnection,sdate;
	protected String sbalancesign="+";
	int iphone=0, ibalance, istdamount, inoofconnection,iofferamount;
	Toast t;
	Calendar c;
	int cyear, cday, cmonth;
	static final int DATE_DIALOG_ID = 0;
	byte sign = 0,signb=0;

	@Override
	public void onCreate(Bundle bubd) {

		super.onCreate(bubd);
		setContentView(R.layout.add);
		
		setform();
		save=(Button)findViewById(R.id.add_save);
		save.setOnClickListener(mouseclicked);
	
	}
	
	
	
View.OnClickListener mouseclicked = new View.OnClickListener() {
	

	public void onClick(View arg0) {		
		  
	       initlise();
	       
	       save();
	}

	

	private void initlise() {
		// TODO Auto-generated method stub
		name = (EditText) findViewById(R.id.add_name);
		phone = (EditText) findViewById(R.id.add_phone);
		address = (EditText) findViewById(R.id.add_address);
		doorno = (EditText) findViewById(R.id.add_doorno);
	
		balance = (EditText) findViewById(R.id.add_balance);
		stdsign = (ImageButton) findViewById(R.id.add_stdsign);
		stdamount = (EditText) findViewById(R.id.add_stdamount);
		offeramount = (EditText) findViewById(R.id.add_offeramount);
		
	}

	
	private void save() {
		try{
		   ContentValues insertdata = new ContentValues();
		   insertdata.put("name", name.getText().toString());
	       insertdata.put("phone",  getIntPhone());
	       insertdata.put("address",  address.getText().toString());
	       insertdata.put("doorno", doorno.getText().toString());
	       insertdata.put("balancesign", sbalancesign);
	       insertdata.put("balance", getIntBalance());
	       insertdata.put("Location", location.getSelectedItem().toString());
	       insertdata.put("stdsign", sstdsign);
	       insertdata.put("stdamount", getIntStdamt());
	       
	       insertdata.put("connectiondate", date.getText().toString());
	       insertdata.put("disconnect", disconnect.getSelectedItem().toString());
	       insertdata.put("freeconnection", freeconnection.getSelectedItem().toString());
	       insertdata.put("offeramount",getIntOfferAmt());
	   
	       DB mydb=new DB();
	       SQLiteDatabase db=mydb.open();
			db.insert("tab_main", null, insertdata);
			db.close();
			mydb.close();
			Toast.makeText(AddNewActivity.this, "Added Successfully", Toast.LENGTH_LONG).show();
			try{startActivity(new Intent(AddNewActivity.this, SplashScreenActivity.class)); }catch(Exception e){Toast.makeText(AddNewActivity.this," Error", Toast.LENGTH_LONG).show();}
		}
	catch(Exception e)
	{
		Toast.makeText(AddNewActivity.this, "Error In Adding", Toast.LENGTH_LONG).show();
	}
		}

	private int getIntBalance() {
		// TODO Auto-generated method stub
		try{
			return Integer.valueOf(balance.getText().toString());
			
		}catch(Exception e){
			return 0;
		}
		
		
	}

	private int getIntPhone() {
		try{
			return Integer.valueOf(phone.getText().toString());
			
		}catch(Exception e){
			return 0;
		}
		
	}
	private int getIntStdamt() {
		try{
			return Integer.valueOf(stdamount.getText().toString());
			
		}catch(Exception e){
			return 0;
		}
		
	}

	private int getIntOfferAmt() {
		try{
			return Integer.valueOf(offeramount.getText().toString());
			
		}catch(Exception e){
			return 0;
		}
		
	}
};

	
	private void setform() {
		addSpinnerData(); // add data for all listbox
		addSpinnerFromDb();
		setdate(); // set connection date
		setsign(); // set sign to standard amount
		setsignBalance();
	}

	private void addSpinnerFromDb() {
		try{DB mydb=new DB();
		mydb.open();
		Cursor cursor;
    	cursor = mydb.fetchAllArea();
    	startManagingCursor(cursor);
		String array_spinner[] = new String[cursor.getCount()];
		int i=0;
		while(cursor.moveToNext()){
			
			array_spinner[i]=cursor.getString(1);
			i++;
		}
		
		location = (Spinner) findViewById(R.id.add_location);
		
		ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_spinner_item, array_spinner);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.setNotifyOnChange(true);
		location.setAdapter(adapter);
		mydb.close();
	}
	catch(Exception e)
	{
	}
	}

	
	private void setsign() {
		stdsign = (ImageButton) findViewById(R.id.add_stdsign);

		stdsign.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (sign == 0) {
					stdsign.setImageDrawable(getResources().getDrawable(
							android.R.drawable.presence_busy));
					sign = 1; sstdsign="-";
				} else {
					stdsign.setImageDrawable(getResources().getDrawable(
							android.R.drawable.ic_input_add));
					sign = 0; sstdsign="+";
				}
			}

		});
	}

	private void setsignBalance() {
		
		balancesign = (ImageButton) findViewById(R.id.add_balancesign);
		balancesign.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {

				if (signb == 0) {
					balancesign.setImageDrawable(getResources().getDrawable(
							android.R.drawable.presence_busy));
					signb = 1; sbalancesign="-";
				} else {
					balancesign.setImageDrawable(getResources().getDrawable(
							android.R.drawable.ic_input_add));
					signb = 0; sbalancesign="+";
				}
			}

		});
	}

	private void setdate() {
		// TODO Auto-generated method stub
		date = (Button) findViewById(R.id.add_date);
		c = Calendar.getInstance();
		cyear = c.get(Calendar.YEAR);
		cmonth = c.get(Calendar.MONTH);
		cday = c.get(Calendar.DAY_OF_MONTH);
		date.setText(cday + " - " + (cmonth + 1) + " - " + cyear);
		date.setOnClickListener(new OnClickListener() {

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
			date.setText(date_selected);
		}
	};


	private void addSpinnerData() {
		// TODO Auto-generated method stub
		String array_spinner[] = new String[2];
		array_spinner[0] = "Yes";
		array_spinner[1] = "No";
		
		disconnect = (Spinner) findViewById(R.id.add_disconnect);
		freeconnection = (Spinner) findViewById(R.id.add_freeconnection);
		
		ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_spinner_item, array_spinner);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.setNotifyOnChange(true);
		disconnect.setAdapter(adapter);
		freeconnection.setAdapter(adapter);
		disconnect.setSelection(1, true);
		freeconnection.setSelection(1, true);

	}
}
