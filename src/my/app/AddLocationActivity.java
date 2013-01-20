package my.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLocationActivity extends Activity{
	EditText loc=null;
	Button add=null;
	@Override
	public void onCreate(Bundle bubd) {

		super.onCreate(bubd);
		setContentView(R.layout.addlocation);
		setvar();
		add.setOnClickListener(submit);
	}

	private void setvar() {
		// TODO Auto-generated method stub
		loc =(EditText)findViewById(R.id.AddLocationText);
		add =(Button)findViewById(R.id.AddLocationAdd);
	}
	View.OnClickListener submit= new View.OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			String location=loc.getText().toString();
			DB db = new DB();
			db.open();
			int d=db.insertLocation(location);
			if(d==1){
				Toast.makeText(AddLocationActivity.this, location.toString() + " Added Successfully", Toast.LENGTH_LONG).show();
			}else{Toast.makeText(AddLocationActivity.this, location.toString() + " Not Added Successfully", Toast.LENGTH_LONG).show();}
			db.close();
		}
	};
}
