/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng tblPhone
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Phone;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PhoneBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblPhone
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_PHONE_NUMBER, HrmContactSqliteHelper.TABLE_PHONE_FK_CATEGORY,
				HrmContactSqliteHelper.FK_CONTACT};

		public PhoneBLL(Context context) {
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
		 * Hàm Insert một Phone mới vào database
		 * Trả lại: đối tượng Phone đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Phone createContact(Phone phone) {
			Phone phoneRes = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_PHONE_NUMBER, phone.getsNumber());
				values.put(HrmContactSqliteHelper.TABLE_PHONE_FK_CATEGORY, phone.getiFK_Phone_categoryID());
				values.put(HrmContactSqliteHelper.FK_CONTACT, phone.getiFK_ContactID());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_PHONE, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_PHONE,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				phoneRes = phoneFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return phoneRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một Phone
		 * Tham số: một đối tượng Phone, sẽ cập nhật các trường của đối tượng Phone hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateContact(Phone phone)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_PHONE_NUMBER, phone.getsNumber());
			    values.put(HrmContactSqliteHelper.TABLE_PHONE_FK_CATEGORY, phone.getiFK_Phone_categoryID());
			    values.put(HrmContactSqliteHelper.FK_CONTACT, phone.getiFK_ContactID());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_PHONE, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(phone.getiId()) }) > 0)
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
		 * Hàm delete một Phone
		 * Tham số: id của Phone cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteContact(int iId) {
			boolean bRes = false;
			try
			{
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_PHONE, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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
		 * Hàm lấy danh sách tất cả Phone trong bảng tblPhone
		 * Trả lại: kiểu List<Phone>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Phone> getAllPhone() {
			List<Phone> lstPhone = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_PHONE, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_PHONE_FK_CATEGORY);
				if(cursor.getCount() > 0)
				{
					lstPhone = new ArrayList<Phone>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Phone objPhone = phoneFromCursor(cursor);
						lstPhone.add(objPhone);
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
				return lstPhone;
			}
		}
		

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Phone theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Phone getContactByID(int iId) {
			Phone phoneRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_PHONE, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					phoneRes = phoneFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return phoneRes;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng phone number
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		@SuppressWarnings("finally")
		public boolean checkDupNumber(String strNumber) {
			boolean bRes = false;
			Cursor cursor = null;
			try
			{
				String strWhere = HrmContactSqliteHelper.TABLE_PHONE_NUMBER+ " = '" + strNumber + "'";
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_PHONE,
						allColumns, strWhere, null, null, null, null);
				cursor.moveToFirst();
				if(!cursor.isAfterLast())
				{
					if(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_PHONE_NUMBER)).equals(strNumber))
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
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Phone
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Phone phoneFromCursor(Cursor cursor) {
			Phone phoneRes = null;
			try{
				
			}catch (Exception e) {
				phoneRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				phoneRes.setsNumber(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_PHONE_NUMBER)));
				phoneRes.setiFK_Phone_categoryID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_PHONE_FK_CATEGORY)));
				phoneRes.setiFK_ContactID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.FK_CONTACT)));
			}finally{
				return phoneRes;
			}
		}
}
