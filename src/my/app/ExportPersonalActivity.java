package my.app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ExportPersonalActivity extends Activity {
	Spinner location=null;
	
	Button export=null;
	public void onCreate(Bundle bubd) {

		super.onCreate(bubd);
		setContentView(R.layout.exportpersonal);
		init();	
		setSpinner();
		export.setOnClickListener(exportclick);
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
		location=(Spinner)findViewById(R.id.ExportpersonalSpinner);
		export=(Button)findViewById(R.id.ExportPersonalButton);
	}
	View.OnClickListener exportclick =new View.OnClickListener() {
		public void onClick(View arg0) {
			try{
			Excel e=new Excel();
			 String loc = location.getSelectedItem().toString();
			 if(loc=="All Location"){
				 Log.w("personal", "start");
					e.exportUserData();
					 Toast.makeText(ExportPersonalActivity.this,"Successfully Exported", Toast.LENGTH_LONG).show();
			 }else{
				 
					e.exportUserData(loc);
			
			 }}catch(Exception e){
				 
				 Toast.makeText(ExportPersonalActivity.this,"write excel Error", Toast.LENGTH_LONG).show();
			 }
			
		}};

		}