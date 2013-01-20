package my.app;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import android.database.Cursor;
import android.os.Environment;

public class Excel {

	File 	dbDir=null;
	Calendar c;
	int cyear, cday, cmonth,cmin;
	String date;
	 int chour;
	 int csec;
	Excel(){}

	public void exportUserData() throws IOException, WriteException {
		// TODO Auto-generated method stub
		createFolder();
		setDate();
		DB mydb=new DB();
		mydb.open();
		Cursor c= mydb.fetchAllUserData();
	    writeToExcel(c);
	    c.close();
	    mydb.close();
	}

	private void setDate() {
		// TODO Auto-generated method stub
		c = Calendar.getInstance();
		cyear = c.get(Calendar.YEAR);
		cmonth = c.get(Calendar.MONTH);
		cday = c.get(Calendar.DAY_OF_MONTH);
		chour=c.get(Calendar.HOUR_OF_DAY);
		cmin=c.get(Calendar.MINUTE);
		csec=c.get(Calendar.SECOND);
		date=(cday + "-" + (cmonth + 1) + "-" + cyear+"_"+chour+"-"+cmin+"-"+csec);
	}

	private void createFolder() {
		// TODO Auto-generated method stub
		dbDir = Environment.getExternalStorageDirectory();	
		String newFolder = "/export";
	      String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
	      File myNewFolder = new File(extStorageDirectory + newFolder);
	      if(!myNewFolder.exists())
	         myNewFolder.mkdir();
	}

	public void exportUserData(String loc) throws RowsExceededException, WriteException, IOException {
		// TODO Auto-generated method stub
		createFolder();
		setDate();
		DB mydb=new DB();
		mydb.open(); 
	    writeToExcel( mydb.fetchAllUserDataWithLoc(loc));
	    mydb.close();
	}
	void writeToExcel(Cursor cursor) throws RowsExceededException, WriteException, IOException
	{
		String filename="UserData"+date+".xls";
		WritableWorkbook workbook = Workbook.createWorkbook(new File(dbDir+"/export",filename));
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		int x=0,i=1;
		/* "create table tab_main (_id INTEGER PRIMARY KEY autoincrement,
		 * name VARCHAR ," +
					" phone INTEGER," +
					" address VARCHAR," +
					" doorno VARCHAR," +
					" Location VARCHAR," +
					" balance INTEGER," +
					" stdsign VARCHAR," +
					" stdamount INTEGER," +
					" disconnect VARCHAR," +
					" freeconnection VARCHAR," +
					" offeramount INTEGER )";*/
		if(1==1){
		Label label = new Label(x++, i, "Customer Id"); 
		sheet.addCell(label);
		
		Label label1 = new Label(x++, i, "name"); 
		sheet.addCell(label1);
		
		Label label2 = new Label(x++, i, "phone"); 
		sheet.addCell(label2);
		
		Label label3 = new Label(x++, i, "address"); 
		sheet.addCell(label3);
		
		Label label4 = new Label(x++, i, "doorno"); 
		sheet.addCell(label4);
		
		Label label5 = new Label(x++, i, "Location"); 
		sheet.addCell(label5);
		
		Label label16 = new Label(x++, i, "balance Sign"); 
		sheet.addCell(label16);
		
		Label label6 = new Label(x++, i, "balance"); 
		sheet.addCell(label6);
		
		Label label7 = new Label(x++, i, "stdsign"); 
		sheet.addCell(label7);
		
		Label label8 = new Label(x++, i, "stdamount"); 
		sheet.addCell(label8);
		
		Label label9 = new Label(x++, i, "disconnect"); 
		sheet.addCell(label9);
		
		Label label12 = new Label(x++, i, "Connection Date"); 
		sheet.addCell(label12);
		
		Label label10 = new Label(x++, i, "freeconnection"); 
		sheet.addCell(label10);
		
		Label label11 = new Label(x++, i, "offeramount"); 
		sheet.addCell(label11);	}
		
    	 i=3;
		while(cursor.moveToNext()){
			
			int j=0,k=0;
			Label label = new Label(j++, i, cursor.getInt(k++)+""); 
			sheet.addCell(label);
			
			Label label1 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label1);
			
			Label label2 = new Label(j++, i, cursor.getString(k++)+""); 
			sheet.addCell(label2);
			
			Label label3 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label3);
			
			Label label4 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label4);
			
			Label label5 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label5);
			
			Label label15 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label15);
			
			Label label6 = new Label(j++, i, cursor.getInt(k++)+""); 
			sheet.addCell(label6);
			
			Label label7 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label7);
			
			Label label8 = new Label(j++, i, cursor.getInt(k++)+""); 
			sheet.addCell(label8);
			
			Label label9 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label9);
			
			Label label12 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label12);
			
			Label label10 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label10);
			
			Label label11 = new Label(j++, i, cursor.getInt(k++)+""); 
			sheet.addCell(label11);	
			
			i++;
		}cursor.close();
		workbook.write(); 
		workbook.close();
		
	}

	public void exportBilldate(int id) throws RowsExceededException, WriteException, IOException {
		// TODO Auto-generated method stub
		createFolder();
		setDate();
		DB mydb=new DB();
		mydb.open();
		Cursor cur=mydb.fetchNameFromId(id);
		cur.moveToNext();String name=cur.getString(1).toString();cur.close();
		
		Cursor c= mydb.fetchBillData(id);
		
	    writeToExcel(c,name);
	    c.close();
	    mydb.close();
		
	}

	private void writeToExcel(Cursor cursor, String name) throws RowsExceededException, WriteException, IOException {
		// TODO Auto-generated method stub
		String filename="BillData"+date+".xls";
		WritableWorkbook workbook = Workbook.createWorkbook(new File(dbDir+"/export",filename));
		WritableSheet sheet = workbook.createSheet("First Sheet", 0);
		int x=0,i=3;

		if(1==1){
			
		Label label = new Label(x++, i, "Date"); 
		sheet.addCell(label);
		
		Label label1 = new Label(x++, i, "Amount"); 
		sheet.addCell(label1);
		
		Label label2 = new Label(1, 1, name); 
		sheet.addCell(label2);
		}
		
    	 i=5;
		while(cursor.moveToNext()){
			
			int j=0,k=1;
			Label label1 = new Label(j++, i, cursor.getString(k++)); 
			sheet.addCell(label1);
			
			Label label = new Label(j++, i, cursor.getInt(k++)+""); 
			sheet.addCell(label);
			
			
			
			i++;
		}cursor.close();
		workbook.write(); 
		workbook.close();
	}
	
}
