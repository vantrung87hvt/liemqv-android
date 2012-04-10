/*
 * Create by: liemqv
 * Create date: 04/10/2012 
 * Lớp này để thực hiện các nghiệp vụ: lấy danh sách Contact, lấy theo ID, thêm, sửa, xóa
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import vn.com.misa.hrm_contact.bean.Contact;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class ContactBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/*
		 * Danh sách các cột của bảng tblContact
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME, HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME, HrmContactSqliteHelper.TABLE_CONTACT_GENDER,
				HrmContactSqliteHelper.TABLE_CONTACT_NOTES, HrmContactSqliteHelper.TABLE_CONTACT_NICKNAME, HrmContactSqliteHelper.TABLE_CONTACT_AVATAR};

		public ContactBLL(Context context) {
			this.context = context;
			hrmcontactSqlHelper = new HrmContactSqliteHelper(context);
		}

		public void open() throws SQLException {
			databaseHelper = hrmcontactSqlHelper.getWritableDatabase();
		}

		public void close() {
			hrmcontactSqlHelper.close();
		}

		/*
		 * Create date: 04/10/2012
		 * Create by: liemqv
		 * Hàm Insert một contact mới vào database
		 * Tham số: một đối tượng Contact, có thể thiếu iId
		 * Trả lại: đối tượng Contact đã thêm vào, nếu lỗi trả lại null
		 * */
		public Contact createContact(Contact _contact) {
			Contact contactReturn = null;
			ContentValues values = new ContentValues();
			//Đẩy các tham số vào parameter để insert
			values.put(HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME, _contact.getsFirstname());
			values.put(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME, _contact.getsLastname());
			values.put(HrmContactSqliteHelper.TABLE_CONTACT_GENDER, _contact.getiGender());
			values.put(HrmContactSqliteHelper.TABLE_CONTACT_NOTES, _contact.getsNotes());
			values.put(HrmContactSqliteHelper.TABLE_CONTACT_NICKNAME, _contact.getsNickname());
			values.put(HrmContactSqliteHelper.TABLE_CONTACT_AVATAR, _contact.getbAvatar());
			
			long intInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_CONTACT, null, values);
			Cursor cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT,
					allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + intInsertID, null, null, null, null);
			cursor.moveToFirst();
			contactReturn = cursorToContact(cursor);
			cursor.close();
			return contactReturn;
		}

		/*
		 * Create date: 04/10/2012
		 * Create by: liemqv
		 * Hàm update một contact
		 * Tham số: một đối tượng Contact, sẽ cập nhật các trường của đối tượng Contact hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		public boolean updateContact(Contact _contact)
		{
			boolean bRes = false;
			
		    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
		    ContentValues values = new ContentValues();
		    values.put(HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME, _contact.getsFirstname());
		    values.put(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME, _contact.getsLastname());
		    values.put(HrmContactSqliteHelper.TABLE_CONTACT_GENDER, _contact.getiGender());
		    values.put(HrmContactSqliteHelper.TABLE_CONTACT_NOTES, _contact.getsNotes());
		    values.put(HrmContactSqliteHelper.TABLE_CONTACT_NICKNAME, _contact.getsNickname());
		    values.put(HrmContactSqliteHelper.TABLE_CONTACT_AVATAR, _contact.getbAvatar());

		    if(sqlDb.update(HrmContactSqliteHelper.TABLE_CONTACT, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
		            new String[] { String.valueOf(_contact.getiId()) }) > 0)
		    {
		    	bRes = true;
		    }
		    else
		    {
		    	bRes = false;
		    }

			return bRes;
		}

		/*
		 * Create date: 04/10/2012
		 * Create by: liemqv
		 * Hàm delete một contact
		 * Tham số: id của Contact cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteContact(int _iId) {
			boolean bRes = false;
			try
			{
				
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_CONTACT, HrmContactSqliteHelper.PRIMARY_KEY + " = " + _iId, null) > 0)
				{
					bRes = true;
				}
			}
			catch (Exception e) {
				
			}
			finally
			{
				return bRes;
			}
			
		}

		/*
		 * Create date: 04/10/2012
		 * Create by: liemqv
		 * Hàm lấy danh sách tất cả Contact trong bảng tblContact
		 * Trả lại: kiểu List<Contact>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Contact> getAllContacts() {
			List<Contact> lstContact = null;
			Cursor cursor = null;
			try
			{
				//Con trỏ tới danh sách Contact trả về, sắp xếp tăng dần theo sName
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT, allColumns, null, null, null, null, "sName");
				if(cursor.getCount() > 0)
				{
					lstContact = new ArrayList<Contact>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Contact objContact = cursorToContact(cursor);
						lstContact.add(objContact);
						cursor.moveToNext();
					}
				}
				else
				{
					// Đóng con trỏ kết nối
					cursor.close();
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return lstContact;
			}
		}
		
		/*
		 * Create date: 04/10/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Contact theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Contact getContactByID(int _iId) {
			Contact contactReturn = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + _iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					contactReturn = cursorToContact(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return contactReturn;
			}
		}
		
		/*
		 * Create date: 04/10/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng tên (First name và Last name)
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		public boolean checkDupName(String _sFirstName, String _sLastName) {
			int iRes = 0;
			String sWhere = HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME + " = '" + _sFirstName + "' AND "+
			HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME + " = '" + _sLastName;
			Cursor cursor = databaseHelper.query(ContactSqliteHelper.TABLE_CONTACT,
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
			 
		    SQLiteDatabase db = hrmcontactSqlHelper.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    cursor.moveToFirst();
		    if(!cursor.isAfterLast())
		    {
		    	iRes = cursor.getInt(cursor.getColumnIndex("max_id"));
		    }
			cursor.close();
			return iRes;
		}

		private Contact cursorToContact(Cursor cursor) {
			Contact objContact = new Contact();
			objContact.setiID(cursor.getInt(cursor.getColumnIndexOrThrow("_id")));
			objContact.setsName(cursor.getString(cursor.getColumnIndexOrThrow("sName")));
			objContact.setsPhone(cursor.getString(cursor.getColumnIndexOrThrow("sPhone")));
			objContact.setsEmail(cursor.getString(cursor.getColumnIndexOrThrow("sEmail")));
			return objContact;
		}
}
