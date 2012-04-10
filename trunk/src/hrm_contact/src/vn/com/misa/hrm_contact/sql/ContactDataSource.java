package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Contact_;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ContactDataSource {
		Context context = null;
	// Database fields
		private SQLiteDatabase database;
		private ContactSqliteHelper dbHelper;
		private String[] allColumns = { ContactSqliteHelper.COLUMN_ID,
				ContactSqliteHelper.COL_NAME, ContactSqliteHelper.COL_PHONE, ContactSqliteHelper.COL_EMAIL};

		public ContactDataSource(Context context) {
			this.context = context;
			dbHelper = new ContactSqliteHelper(context);
		}

		public void open() throws SQLException {
			database = dbHelper.getWritableDatabase();
		}

		public void close() {
			dbHelper.close();
		}

		public Contact_ createContact(String _name, String _phone, String _email) {
			ContentValues values = new ContentValues();
			//Đẩy các tham số vào parameter để insert
			values.put(ContactSqliteHelper.COL_NAME, _name);
			values.put(ContactSqliteHelper.COL_PHONE, _phone);
			values.put(ContactSqliteHelper.COL_EMAIL, _email);
			
			long insertId = database.insert(ContactSqliteHelper.TABLE_CONTACT, null, values);
			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, ContactSqliteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Contact_ newContact = cursorToContact(cursor);
			cursor.close();
			return newContact;
		}
		
		public Contact_ createContact(Contact_ _contact) {
			ContentValues values = new ContentValues();
			//Đẩy các tham số vào parameter để insert
			values.put(ContactSqliteHelper.COL_NAME, _contact.getsName());
			values.put(ContactSqliteHelper.COL_PHONE, _contact.getsPhone());
			values.put(ContactSqliteHelper.COL_EMAIL, _contact.getsEmail());
			
			long insertId = database.insert(ContactSqliteHelper.TABLE_CONTACT, null, values);
			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, ContactSqliteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Contact_ newContact = cursorToContact(cursor);
			cursor.close();
			return newContact;
		}
		
		public boolean editContact(Contact_ _contact)
		{
			boolean bRes = false;
			
		    SQLiteDatabase db = dbHelper.getWritableDatabase();
		    ContentValues values = new ContentValues();
		    values.put(ContactSqliteHelper.COL_NAME, _contact.getsName());
		    values.put(ContactSqliteHelper.COL_PHONE, _contact.getsPhone());
		    values.put(ContactSqliteHelper.COL_EMAIL, _contact.getsEmail());

		    if(db.update(ContactSqliteHelper.TABLE_CONTACT, values, ContactSqliteHelper.COLUMN_ID + " = ?",
		            new String[] { String.valueOf(_contact.getiID()) }) > 0)
		    {
		    	bRes = true;
		    }
		    else
		    {
		    	bRes = false;
		    }

			return bRes;
		}

		public void deleteContact(int _id) {
			database.delete(ContactSqliteHelper.TABLE_CONTACT, ContactSqliteHelper.COLUMN_ID
					+ " = " + _id, null);
		}

		public List<Contact_> getAllContacts() {
			List<Contact_> lstContact = new ArrayList<Contact_>();

			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, null, null, null, null, "sName");

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Contact_ objContact = cursorToContact(cursor);
				lstContact.add(objContact);
				cursor.moveToNext();
			}
			// Đóng con trỏ kết nối
			cursor.close();
			return lstContact;
		}
		
		public Contact_ getOneContacts(Integer _id) {
			Contact_ objContact = null;

			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, ContactSqliteHelper.COLUMN_ID + " = " + _id, null, null, null, null);
			cursor.moveToFirst();
			objContact = cursorToContact(cursor);
			// Đóng con trỏ kết nối
			cursor.close();
			return objContact;
		}
		
		public int CheckDupContact(Contact_ _contact) {
			int iRes = 0;
			String sWhere = ContactSqliteHelper.COL_NAME + " = '" + _contact.getsName() + "' OR "+
			ContactSqliteHelper.COL_PHONE + " = '" + _contact.getsPhone() + "' OR "+
			ContactSqliteHelper.COL_EMAIL + " = '" + _contact.getsEmail() + "'";
			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, sWhere, null, null, null, null);
			cursor.moveToFirst();
			if(!cursor.isAfterLast())
			{
				if(cursor.getString(cursor.getColumnIndex("sName")).toUpperCase().equals(_contact.getsName().toUpperCase()))
				{
					iRes = 1;
				}
				else
					if(cursor.getString(cursor.getColumnIndex("sPhone")).toUpperCase().equals(_contact.getsPhone().toUpperCase()))
					{
						iRes = 2;
					}
				else
					if(cursor.getString(cursor.getColumnIndex("sEmail")).toUpperCase().equals(_contact.getsEmail().toUpperCase()))
					{
						iRes = 3;
					}
			}
			// Đóng con trỏ kết nối
			cursor.close();
			return iRes;
		}
		
		public int getMaxID() {
			int iRes = 0;
			
			String selectQuery = "SELECT MAX(" + ContactSqliteHelper.COLUMN_ID + ") as max_id FROM " + ContactSqliteHelper.TABLE_CONTACT;
			 
		    SQLiteDatabase db = dbHelper.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    cursor.moveToFirst();
		    if(!cursor.isAfterLast())
		    {
		    	iRes = cursor.getInt(cursor.getColumnIndex("max_id"));
		    }
			cursor.close();
			return iRes;
		}

		private Contact_ cursorToContact(Cursor cursor) {
			Contact_ objContact = new Contact_();
			objContact.setiID(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
			objContact.setsName(cursor.getString(cursor.getColumnIndexOrThrow("sName")));
			objContact.setsPhone(cursor.getString(cursor.getColumnIndexOrThrow("sPhone")));
			objContact.setsEmail(cursor.getString(cursor.getColumnIndexOrThrow("sEmail")));
			return objContact;
		}
}
