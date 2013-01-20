package my.app;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

public class MyListActivity extends ListActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cablelist);
		fillData();
	}

	private void fillData() {

		try {
			DB mydb = new DB();
			mydb.open();
			Cursor notesCursor = mydb.fetchAllData();
			startManagingCursor(notesCursor);
			String[] from = new String[] { "_id","name" };
			int[] to = new int[] {  R.id.textid,R.id.textname };
			SimpleCursorAdapter notes = new SimpleCursorAdapter(
					MyListActivity.this, R.layout.listrow, notesCursor, from,
					to);
			setListAdapter(notes);

		} catch (Exception e) {
			Log.v("naresh", "catched");
		}
	}
}
