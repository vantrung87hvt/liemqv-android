/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng tblEmail
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Email;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EmailBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblEmail
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_EMAIL_EMAIL, HrmContactSqliteHelper.TABLE_EMAIL_FK_CATEGORY,
				HrmContactSqliteHelper.FK_CONTACT};

		public EmailBLL(Context context) {
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
		 * Hàm Insert một Email mới vào database
		 * Trả lại: đối tượng Email đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Email createEmail(Email email) {
			Email emailRes = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_EMAIL_EMAIL, email.getsEmail());
				values.put(HrmContactSqliteHelper.TABLE_EMAIL_FK_CATEGORY, email.getiFK_Email_categoryID());
				values.put(HrmContactSqliteHelper.FK_CONTACT, email.getiFK_ContactID());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_EMAIL, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_EMAIL,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				emailRes = emailFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return emailRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một Email
		 * Tham số: một đối tượng Email, sẽ cập nhật các trường của đối tượng Email hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateEmail(Email email)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_EMAIL_EMAIL, email.getsEmail());
			    values.put(HrmContactSqliteHelper.TABLE_EMAIL_FK_CATEGORY, email.getiFK_Email_categoryID());
			    values.put(HrmContactSqliteHelper.FK_CONTACT, email.getiFK_ContactID());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_EMAIL, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(email.getiId()) }) > 0)
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
		 * Hàm delete một Email
		 * Tham số: id của Email cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteEmail(int iId) {
			boolean bRes = false;
			try
			{
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_EMAIL, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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
		 * Hàm lấy danh sách tất cả Email trong bảng tblEmail
		 * Trả lại: kiểu List<Email>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Email> getAllEmail() {
			List<Email> lstEmail = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_EMAIL, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_EMAIL_FK_CATEGORY);
				if(cursor.getCount() > 0)
				{
					lstEmail = new ArrayList<Email>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Email objEmail = emailFromCursor(cursor);
						lstEmail.add(objEmail);
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
				return lstEmail;
			}
		}
		

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Email theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Email getEmailByID(int iId) {
			Email emailRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_EMAIL, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					emailRes = emailFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return emailRes;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng email
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		@SuppressWarnings("finally")
		public boolean checkDupEmail(String strEmail) {
			boolean bRes = false;
			Cursor cursor = null;
			try
			{
				String strWhere = HrmContactSqliteHelper.TABLE_EMAIL_EMAIL+ " = '" + strEmail + "'";
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_EMAIL,
						allColumns, strWhere, null, null, null, null);
				cursor.moveToFirst();
				if(!cursor.isAfterLast())
				{
					if(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_EMAIL_EMAIL)).equals(strEmail))
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
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Email
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Email emailFromCursor(Cursor cursor) {
			Email emailRes = null;
			try{
				
			}catch (Exception e) {
				emailRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				emailRes.setsEmail(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_EMAIL_EMAIL)));
				emailRes.setiFK_Email_categoryID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_EMAIL_FK_CATEGORY)));
				emailRes.setiFK_ContactID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.FK_CONTACT)));
			}finally{
				return emailRes;
			}
		}
}
