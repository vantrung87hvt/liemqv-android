/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng PhoneCategory
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.PhoneCategory;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PhoneCategoryBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblPhone_category
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.CATEGORY_NAME};

		public PhoneCategoryBLL(Context context) {
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
		 * Hàm Insert một đối tượng Phone_category vào database
		 * Tham số: một đối tượng PhoneCategory
		 * Trả lại: đối tượng Phone_category nếu insert thành công, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public boolean createPhoneCategory(PhoneCategory phoneCategory) {
			long longInsertID = -1;
			try{
				ContentValues values = new ContentValues();
				//Đẩy các tham số vào parameter để insert
				values.put(HrmContactSqliteHelper.PRIMARY_KEY, phoneCategory.getiId());
				values.put(HrmContactSqliteHelper.CATEGORY_NAME, phoneCategory.getsCategoryname());
				longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_PHONE_CATEGORY, null, values);
			}catch (Exception e) {
				
			}finally{
				return (longInsertID != -1 ? true : false);
			}
		}

		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm lấy danh sách PhoneCategory từ database
		 * Trả lại: Danh sách PhoneCategory có trong database, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<PhoneCategory> getAllPhoneCategory() {
			List<PhoneCategory> lstPhoneCategory = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_PHONE_CATEGORY, allColumns, null, null, null, null, HrmContactSqliteHelper.PRIMARY_KEY);
				if(cursor.getCount() > 0)
				{
					lstPhoneCategory = new ArrayList<PhoneCategory>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						PhoneCategory phoneCategory = phoneCategoryFromCursor(cursor);
						lstPhoneCategory.add(phoneCategory);
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
				return lstPhoneCategory;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng PhoneCategory theo ID
		 * Trả lại: một đối tượng PhoneCategory, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public PhoneCategory getPhoneCategoryByID(int iId) {
			PhoneCategory phoneCategoryRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_PHONE_CATEGORY, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					phoneCategoryRes = phoneCategoryFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return phoneCategoryRes;
			}
		}
		
		@SuppressWarnings("finally")
		private PhoneCategory phoneCategoryFromCursor(Cursor cursor) {
			PhoneCategory phoneCategoryRes = null;
			try{
				phoneCategoryRes = new PhoneCategory();
				phoneCategoryRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				phoneCategoryRes.setsCategoryname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_PHONE_CATEGORY)));
			}
			catch (Exception e) {
				
			}
			finally{
				return phoneCategoryRes;
			}
		}
}
