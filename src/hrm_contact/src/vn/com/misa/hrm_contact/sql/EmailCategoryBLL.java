/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng EmailCategory
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.EmailCategory;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class EmailCategoryBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.CATEGORY_NAME};

		public EmailCategoryBLL(Context context) {
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
		 * Hàm Insert một đối tượng EmailCategory vào database
		 * Tham số: một đối tượng EmailCategory
		 * Trả lại: đối tượng EmailCategory nếu insert thành công, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public boolean createEmailCategory(EmailCategory emailCategory) {
			long longInsertID = -1;
			try{
				ContentValues values = new ContentValues();
				//Đẩy các tham số vào parameter để insert
				values.put(HrmContactSqliteHelper.PRIMARY_KEY, emailCategory.getiId());
				values.put(HrmContactSqliteHelper.CATEGORY_NAME, emailCategory.getsCategoryname());
				longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_EMAIL_CATEGORY, null, values);
			}catch (Exception e) {
				
			}finally{
				return (longInsertID != -1 ? true : false);
			}
		}

		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm lấy danh sách EmailCategory từ database
		 * Trả lại: Danh sách EmailCategory có trong database, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<EmailCategory> getAllEmailCategory() {
			List<EmailCategory> lstEmailCategory = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_EMAIL_CATEGORY, allColumns, null, null, null, null, HrmContactSqliteHelper.PRIMARY_KEY);
				if(cursor.getCount() > 0)
				{
					lstEmailCategory = new ArrayList<EmailCategory>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						EmailCategory emailCategory = emailCategoryFromCursor(cursor);
						lstEmailCategory.add(emailCategory);
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
				return lstEmailCategory;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng EmailCategory theo ID
		 * Trả lại: một đối tượng EmailCategory, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public EmailCategory getEmailCategoryByID(int iId) {
			EmailCategory emailCategoryRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_EMAIL_CATEGORY, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					emailCategoryRes = emailCategoryFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return emailCategoryRes;
			}
		}
		
		@SuppressWarnings("finally")
		private EmailCategory emailCategoryFromCursor(Cursor cursor) {
			EmailCategory emailCategoryRes = null;
			try{
				emailCategoryRes = new EmailCategory();
				emailCategoryRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				emailCategoryRes.setsCategoryname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_EMAIL_CATEGORY)));
			}
			catch (Exception e) {
				
			}
			finally{
				return emailCategoryRes;
			}
		}
}
