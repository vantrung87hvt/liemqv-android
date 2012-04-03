package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.model.Contact;

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

		public Contact createContact(String _name, String _phone, String _email) {
			ContentValues values = new ContentValues();
			//Đẩy các tham số vào parameter để insert
			values.put(ContactSqliteHelper.COL_NAME, _name);
			values.put(ContactSqliteHelper.COL_PHONE, _phone);
			values.put(ContactSqliteHelper.COL_EMAIL, _email);
			
			long insertId = database.insert(ContactSqliteHelper.TABLE_CONTACT, null, values);
			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, ContactSqliteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Contact newContact = cursorToContact(cursor);
			cursor.close();
			return newContact;
		}
		
		public Contact createContact(Contact _contact) {
			ContentValues values = new ContentValues();
			//Đẩy các tham số vào parameter để insert
			values.put(ContactSqliteHelper.COL_NAME, _contact.getsName());
			values.put(ContactSqliteHelper.COL_PHONE, _contact.getsPhone());
			values.put(ContactSqliteHelper.COL_EMAIL, _contact.getsEmail());
			
			long insertId = database.insert(ContactSqliteHelper.TABLE_CONTACT, null, values);
			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, ContactSqliteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
			cursor.moveToFirst();
			Contact newContact = cursorToContact(cursor);
			cursor.close();
			return newContact;
		}

		public void deleteContact(Contact objContact) {
			long id = objContact.getiID();
			Toast.makeText(context, "Contact deleted!", Toast.LENGTH_LONG).show();
			database.delete(ContactSqliteHelper.TABLE_CONTACT, ContactSqliteHelper.COLUMN_ID
					+ " = " + id, null);
		}

		public List<Contact> getAllContacts() {
			List<Contact> lstContact = new ArrayList<Contact>();

			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Contact objContact = cursorToContact(cursor);
				lstContact.add(objContact);
				cursor.moveToNext();
			}
			// Đóng con trỏ kết nối
			cursor.close();
			return lstContact;
		}
		
		public Contact getOneContacts(Integer _id) {
			Contact objContact = null;

			Cursor cursor = database.query(ContactSqliteHelper.TABLE_CONTACT,
					allColumns, ContactSqliteHelper.COLUMN_ID + " = " + _id, null, null, null, null);
			cursor.moveToFirst();
			objContact = cursorToContact(cursor);
			// Đóng con trỏ kết nối
			cursor.close();
			return objContact;
		}

		private Contact cursorToContact(Cursor cursor) {
			Contact objContact = new Contact();
			objContact.setiID(cursor.getInt(0));
			objContact.setsName(cursor.getString(cursor.getColumnIndexOrThrow("sName")));
			objContact.setsPhone(cursor.getString(cursor.getColumnIndexOrThrow("sPhone")));
			objContact.setsEmail(cursor.getString(cursor.getColumnIndexOrThrow("sEmail")));
			return objContact;
		}
}
