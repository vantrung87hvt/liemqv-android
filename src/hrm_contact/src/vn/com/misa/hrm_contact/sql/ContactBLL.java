/**
 * Create by: liemqv
 * Create date: 10/04/2012 
 * Lớp này để thực hiện các nghiệp vụ: lấy danh sách Contact, lấy theo ID, thêm, sửa, xóa
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;
import vn.com.misa.hrm_contact.bean.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ContactBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
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

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm Insert một contact mới vào database
		 * Tham số: một đối tượng Contact, có thể thiếu iId
		 * Trả lại: đối tượng Contact đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Contact createContact(Contact contact) {
			Contact contactReturn = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME, contact.getsFirstname());
				values.put(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME, contact.getsLastname());
				values.put(HrmContactSqliteHelper.TABLE_CONTACT_GENDER, contact.getiGender());
				values.put(HrmContactSqliteHelper.TABLE_CONTACT_NOTES, contact.getsNotes());
				values.put(HrmContactSqliteHelper.TABLE_CONTACT_NICKNAME, contact.getsNickname());
				values.put(HrmContactSqliteHelper.TABLE_CONTACT_AVATAR, contact.getbAvatar());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_CONTACT, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				contactReturn = contactFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return contactReturn;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một contact
		 * Tham số: một đối tượng Contact, sẽ cập nhật các trường của đối tượng Contact hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateContact(Contact contact)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME, contact.getsFirstname());
			    values.put(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME, contact.getsLastname());
			    values.put(HrmContactSqliteHelper.TABLE_CONTACT_GENDER, contact.getiGender());
			    values.put(HrmContactSqliteHelper.TABLE_CONTACT_NOTES, contact.getsNotes());
			    values.put(HrmContactSqliteHelper.TABLE_CONTACT_NICKNAME, contact.getsNickname());
			    values.put(HrmContactSqliteHelper.TABLE_CONTACT_AVATAR, contact.getbAvatar());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_CONTACT, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(contact.getiId()) }) > 0)
			    {
			    	bRes = true;
			    }
			}catch (Exception e) {
				
			}
			finally{
				return bRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm delete một contact
		 * Tham số: id của Contact cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteContact(int iId) {
			boolean bRes = false;
			try
			{
				
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_CONTACT, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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

		/**
		 * Create date: 10/04/2012
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
				//Con trỏ tới danh sách Contact trả về, sắp xếp tăng dần theo Name
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME);
				if(cursor.getCount() > 0)
				{
					lstContact = new ArrayList<Contact>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Contact objContact = contactFromCursor(cursor);
						lstContact.add(objContact);
						cursor.moveToNext();
					}
					cursor.close();
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
				return lstContact;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Contact theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Contact getContactByID(int iId) {
			Contact contactReturn = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					contactReturn = contactFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return contactReturn;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng tên (First name và Last name)
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		@SuppressWarnings("finally")
		public boolean checkDupName(String strFirstName, String strLastName) {
			boolean bRes = false;
			Cursor cursor = null;
			try
			{
				String strWhere = HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME + " = '" + strFirstName + "' AND "+
				HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME + " = '" + strLastName;
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_CONTACT,
						allColumns, strWhere, null, null, null, null);
				cursor.moveToFirst();
				String strFullnameParameter = strFirstName + strLastName.toUpperCase();
				String strFullnameDatabase;
				if(!cursor.isAfterLast())
				{
					strFullnameDatabase = cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME)).toUpperCase();
					strFullnameDatabase += cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME)).toUpperCase();
					if(strFullnameParameter.equals(strFullnameDatabase))
					{
						bRes = true;
					}
				}
			}
			catch(Exception ex)
			{
				
			}
			finally
			{
				cursor.close();
				return bRes;
			}
		}
		
		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm trả lại ID mới nhất, áp dụng với trường ID tăng tự động
		 * Trả lại: ID mới nhất, ngược lại trả lại -1
		 * */
		@SuppressWarnings("finally")
		public int getMaxID() {
			int iRes = -1;
			Cursor cursor = null;
			try
			{
				String selectQuery = "SELECT MAX(" + HrmContactSqliteHelper.PRIMARY_KEY + ") as max_id FROM " + HrmContactSqliteHelper.TABLE_CONTACT;
				 
			    SQLiteDatabase db = hrmcontactSqlHelper.getWritableDatabase();
			    cursor = db.rawQuery(selectQuery, null);
			    cursor.moveToFirst();
			    if(!cursor.isAfterLast())
			    {
			    	iRes = cursor.getInt(cursor.getColumnIndex("max_id"));
			    }
			}
			catch(Exception ex)
			{
				
			}
			finally
			{
				cursor.close();
				return iRes;
			}
		}

		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Contact
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Contact contactFromCursor(Cursor cursor) 
		{
			Contact contactRes = null;
			try
			{
				contactRes = new Contact();
				contactRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				contactRes.setsFirstname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_FIRSTNAME)));
				contactRes.setsLastname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_LASTNAME)));
				contactRes.setiGender(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_GENDER)));
				contactRes.setsNickname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_NICKNAME)));
				contactRes.setsNotes(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_NOTES)));
				contactRes.setbAvatar(cursor.getBlob(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_CONTACT_AVATAR)));
			}
			catch(Exception ex)
			{
				
			}
			finally
			{
				return contactRes;
			}
		}
}
