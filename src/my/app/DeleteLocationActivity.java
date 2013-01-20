package my.app;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class DeleteLocationActivity extends Activity{
	Spinner spin=null;
	Button delete=null;
	@Override
	public void onCreate(Bundle bubd) {

		super.onCreate(bubd);
		setContentView(R.layout.deletelocation);
		//test();
		setvar();
		
		settree();
		delete.setOnClickListener(click);
	}

	private void settree() {
		// TODO Auto-generated method stub
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
		
		
		
		ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_spinner_item, array_spinner);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.setNotifyOnChange(true);
		spin.setAdapter(adapter);
		mydb.close();
	}
	catch(Exception e)
	{Log.w("exception",e.getMessage() );
	}
	}
	private void setvar() {
		// TODO Auto-generated method stub
		spin =(Spinner)findViewById(R.id.DeleteLocationSpinner);
		delete=(Button)findViewById(R.id.DeleteLocationDelete);
	}
	View.OnClickListener click= new OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String loca=spin.getSelectedItem().toString();
			DB db=new DB();db.open();
			int result=db.deleteLocation(loca);
			if(result==1){
				Toast.makeText(DeleteLocationActivity.this, loca + " Deleted Successfully", Toast.LENGTH_LONG).show();
			}else{Toast.makeText(DeleteLocationActivity.this, loca + " Not Deleted Successfully", Toast.LENGTH_LONG).show();}
			db.close();settree();
		}
	};
}
