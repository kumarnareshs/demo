package my.app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SplashScreenActivity extends ListActivity {

	
	String[] list = new String[]{
			 "Add New ",
			  "List It",
			  "Search",
			  "pay Bill",
			  "Report",
			  "Export" ,
			  "Setting"
					 
		 };
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			setListAdapter(new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, list));
			getListView().setTextFilterEnabled(true);
		} catch (Exception e) {
			Toast.makeText(SplashScreenActivity.this,"SplashActivity Error", Toast.LENGTH_LONG).show();
		}
	}
	 
	    protected void onListItemClick(ListView l, View v, int position, long id) {
	    	super.onListItemClick(l, v, position, id);
	    	switch(position){
	    	case 0: try{startActivity(new Intent(SplashScreenActivity.this, AddNewActivity.class)); }catch(Exception e){Toast.makeText(SplashScreenActivity.this," Error", Toast.LENGTH_LONG).show();}break;
	    	case 1:startActivity(new Intent(SplashScreenActivity.this, MyListActivity.class)); break;
	    	case 2:startActivity(new Intent(SplashScreenActivity.this, SearchActivity.class)); break;
	    	case 3:startActivity(new Intent(SplashScreenActivity.this, BillActivity.class)); break;
	    	case 4:startActivity(new Intent(SplashScreenActivity.this, ReportActivity.class)); break;
	    	case 5:startActivity(new Intent(SplashScreenActivity.this, ExportActivity.class)); break;
	    	case 6:startActivity(new Intent(SplashScreenActivity.this, SettingsActivity.class)); break;
	    	}
	    }
}
