package my.app;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class ReportActivity extends ListActivity {
	Spinner location=null;
	EditText bal=null;
	Button report=null;
	String loc=null;int balance=0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		init();	
		setSpinner();
		report.setOnClickListener(reportlist);
	}

	private void setSpinner() {
		// TODO Auto-generated method stub
		try{DB mydb=new DB();
		mydb.open();
		Cursor cursor;
    	cursor = mydb.fetchAllArea();
    	startManagingCursor(cursor);
		String array_spinner[] = new String[cursor.getCount()+1];
		array_spinner[0]="All Location";
		int i=1;
		while(cursor.moveToNext()){
			
			array_spinner[i]=cursor.getString(1);
			i++;
		}
		
		
		
		ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_spinner_item, array_spinner);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.setNotifyOnChange(true);
		location.setAdapter(adapter);
		mydb.close();
	}
	catch(Exception e)
	{Log.w("exception",e.getMessage() );
	}
	}

	private void init() {
		// TODO Auto-generated method stub
		location=(Spinner)findViewById(R.id.ReportSpinner);
		bal=(EditText)findViewById(R.id.ReportText);
		report=(Button)findViewById(R.id.ReportButton);
	}
	View.OnClickListener reportlist =new View.OnClickListener() {
		public void onClick(View arg0) {
			
			 loc=location.getSelectedItem().toString();
			 balance=Integer.valueOf(bal.getText().toString());
			 
			 fillData();
		}};

		private void fillData() {
			try {
				DB mydb = new DB();
				mydb.open();Cursor notesCursor=null;
				if(loc=="All Location"){
					notesCursor = mydb.fetchReportAllData(balance);
					
				}else{
					
					notesCursor = mydb.fetchReportData(balance,loc);
				}
				
				startManagingCursor(notesCursor);
				String[] from = new String[] { "_id","name" };
				int[] to = new int[] {  R.id.textid,R.id.textname };
				SimpleCursorAdapter notes = new SimpleCursorAdapter(
						ReportActivity.this, R.layout.listrow, notesCursor, from,
						to);
				setListAdapter(notes);mydb.close();
			} catch (Exception e) {
				Log.v("naresh", "catched");
			}
		}}
