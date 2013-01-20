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


public class SearchActivity  extends ListActivity{
	private Spinner spin=null;
	EditText text=null;
	private String list;
	private String stext;
	private String slist="No";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchlist);
		init();	
	}

	private void init() {
		Button search =(Button)findViewById(R.id.searchbutton);
		search.setOnClickListener(searchlist);
		setSpinner();
	}
	
private void setSpinner() {

	String array_spinner[] = new String[10];
	array_spinner[0] = "Name";
	array_spinner[1] = "No";
	array_spinner[2] = "Address";
	array_spinner[3] = "Door No";
	array_spinner[4] = "Location";
	array_spinner[5] = "Phone";
	array_spinner[6] = "Balance";
	array_spinner[7] = "Standard Amount";
	
	array_spinner[8] = "Free Connection";
	array_spinner[9] = "Date";

	spin =(Spinner) findViewById(R.id.searchspinner);
	ArrayAdapter<?> adapter = new ArrayAdapter<Object>(this,android.R.layout.simple_spinner_item, array_spinner);
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	adapter.setNotifyOnChange(true);
	spin.setAdapter(adapter);
	spin.setSelection(1, true);
	}


	View.OnClickListener searchlist =new View.OnClickListener() {
	public void onClick(View arg0) {
		 text = (EditText) findViewById(R.id.searchtext);
		 list=spin.getSelectedItem().toString();
		 stext=text.getText().toString();
		 getselectedlist();
		 fillData();
	}};
private void getselectedlist() {
	if(list=="Name"){slist="name";}
	if(list=="Address"){slist="address";}
	if(list=="Door No"){slist="doorno";}
	if(list=="Location"){slist="location";}
	if(list=="Phone"){slist="phone";}
	if(list=="Balance"){slist="balance";}
	if(list=="Standard Amount"){slist="stdamount";}
	if(list=="Free Connection"){slist="freeconnection";}
	if(list=="Date"){slist="date";}
	if(list=="No"){slist="_id";}
	
}	

private void fillData() {
		try {
			DB mydb = new DB();
			mydb.open();
			Cursor notesCursor = mydb.fetchSearchData(stext,slist);
			startManagingCursor(notesCursor);
			String[] from = new String[] { "_id","name" };
			int[] to = new int[] {  R.id.textid,R.id.textname };
			SimpleCursorAdapter notes = new SimpleCursorAdapter(
					SearchActivity.this, R.layout.listrow, notesCursor, from,
					to);
			setListAdapter(notes);
		} catch (Exception e) {
			Log.v("naresh", "catched");
		}
	}
}
