package vn.com.misa.hrm_contact.dal;



import vn.com.misa.hrm_contact.model.Contact;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;




public class DatabaseHelper extends SQLiteOpenHelper {

	static final String dbName="misa_hrm_contact";
	static final String contactTable="tblContact";
	static final String colID="contactID";
	static final String colName="sName";
	static final String colPhone="sPhone";
	static final String colEmail="sEmail";
	
	static final String viewContact="ViewContact";
	
	
	public DatabaseHelper(Context context) {
		super(context, dbName, null,33);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("CREATE TABLE "+contactTable+" ("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
				colName+" TEXT, "+colPhone+" Integer, "+colEmail+" INTEGER NOT NULL ,FOREIGN KEY ("+colEmail+");");
		//Inserts pre-defined departments
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		db.execSQL("DROP TABLE IF EXISTS "+contactTable);
		onCreate(db);
	}
	
	 void AddEmployee(Contact _contact)
	{
		 SQLiteDatabase db= this.getWritableDatabase();
		 
		ContentValues cv=new ContentValues();
		
		cv.put(colName, _contact.getsName());
		cv.put(colPhone, _contact.getsPhone());
		cv.put(colEmail, _contact.getsEmail());

		db.insert(contactTable, colEmail, cv);
		db.close();
	}
	 
	 int getContactListCount()
	 {
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cur= db.rawQuery("Select * from "+contactTable, null);
		int x= cur.getCount();
		cur.close();
		return x;
	 }
	 
	 Cursor getAllContact()
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 //Cursor cur= db.rawQuery("Select "+colID+" as _id , "+colName+", "+colAge+" from "+employeeTable, new String [] {});
		 Cursor cur= db.rawQuery("SELECT * FROM tblContact",null);
		 return cur;
	 }
	 
	 
	 public Contact getOneContact(int ID)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 
		 String[] params=new String[]{String.valueOf(ID)};
		 Cursor c=db.rawQuery("SELECT * from tblContact where contactID = " + ID,params);
		 c.moveToFirst();
		 Contact ct = new Contact(c.getString(1), c.getString(2), c.getString(3), false);
		 return ct;
	 }
	 
	 public Contact getOneContactByName(String _name)
	 {
		 SQLiteDatabase db=this.getReadableDatabase();
		 
		 String[] params=new String[]{String.valueOf(_name)};
		 Cursor c=db.rawQuery("SELECT * from tblContact where sName = '" + _name + "'",params);
		 c.moveToFirst();
		 Contact ct = new Contact(c.getString(1), c.getString(2), c.getString(3), false);
		 return ct;
	 }
	 
	 public int updateContact(Contact _contact)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 ContentValues cv=new ContentValues();
		 cv.put(colName, _contact.getsName());
		 cv.put(colPhone, _contact.getsPhone());
		 cv.put(colEmail, _contact.getsEmail());
		 return db.update(contactTable, cv, colID+"=?", new String []{String.valueOf(_contact.getiID())});
		 
	 }
	 
	 public void deleteContact(Contact _contact)
	 {
		 SQLiteDatabase db=this.getWritableDatabase();
		 db.delete(contactTable,colID+"=?", new String [] {String.valueOf(_contact.getiID())});
		 db.close();
	 }

}
