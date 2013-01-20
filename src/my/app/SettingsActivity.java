package my.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends ListActivity {

	
	String[] list = new String[]{
			 "Add Month",
			 "Add Location",
			 "Delete Location"
		 };
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, list));
			getListView().setTextFilterEnabled(true);
		} catch (Exception e) {
			Toast.makeText(SettingsActivity.this,"SplashActivity Error", Toast.LENGTH_LONG).show();
		}
	}
	 
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	super.onListItemClick(l, v, position, id);
	    	switch(position){
	    	case 0: try{startActivity(new Intent(SettingsActivity.this,AddMonthActivity.class)); }catch(Exception e){Toast.makeText(SettingsActivity.this," Error", Toast.LENGTH_LONG).show();}break;
	    	case 1: startActivity(new Intent(SettingsActivity.this, AddLocationActivity.class));break;
	    	case 2: startActivity(new Intent(SettingsActivity.this, DeleteLocationActivity.class));break;
	    	}
	    }
}
