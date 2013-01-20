package my.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExportBillActivity extends Activity  {
	EditText CusId=null;
	
	Button export=null;
	@Override
	
		public void onCreate(Bundle bubd) {

			super.onCreate(bubd);
			setContentView(R.layout.exportbill);
			init();	
			
			export.setOnClickListener(exportclick);
		}

	
		private void init() {
			// TODO Auto-generated method stub
			CusId=(EditText)findViewById(R.id.ExportBillText);
			export=(Button)findViewById(R.id.ExportBillButton);
		}
		View.OnClickListener exportclick =new View.OnClickListener() {
			public void onClick(View arg0) {
				try{
				Excel e=new Excel();
				 int id= Integer.valueOf(CusId.getText().toString());
				 e.exportBilldate(id);
				 Toast.makeText(ExportBillActivity.this,"Successfully Exported", Toast.LENGTH_LONG).show();
				}catch(Exception e){
					 
					 Toast.makeText(ExportBillActivity.this,"write excel Error", Toast.LENGTH_LONG).show();
				 }
				
			}};

			}