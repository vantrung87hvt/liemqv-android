/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng ImCategory
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.ImCategory;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ImCategoryBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.CATEGORY_NAME, HrmContactSqliteHelper.TABLE_IM_CATEGORY_SORT_NAME};

		public ImCategoryBLL(Context context) {
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
		 * Hàm Insert một đối tượng ImCategory vào database
		 * Tham số: một đối tượng ImCategory
		 * Trả lại: đối tượng ImCategory nếu insert thành công, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public boolean createImCategory(ImCategory imCategory) {
			long longInsertID = -1;
			try{
				ContentValues values = new ContentValues();
				//Đẩy các tham số vào parameter để insert
				values.put(HrmContactSqliteHelper.PRIMARY_KEY, imCategory.getiId());
				values.put(HrmContactSqliteHelper.CATEGORY_NAME, imCategory.getsCategoryname());
				values.put(HrmContactSqliteHelper.TABLE_IM_CATEGORY_SORT_NAME, imCategory.getsSortname());
				longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_IM_CATEGORY, null, values);
			}catch (Exception e) {
				
			}finally{
				return (longInsertID != -1 ? true : false);
			}
		}

		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm lấy danh sách ImCategory từ database
		 * Trả lại: Danh sách ImCategory có trong database, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<ImCategory> getAllImCategory() {
			List<ImCategory> lstImCategory = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_IM_CATEGORY, allColumns, null, null, null, null, HrmContactSqliteHelper.PRIMARY_KEY);
				if(cursor.getCount() > 0)
				{
					lstImCategory = new ArrayList<ImCategory>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						ImCategory imCategory = imCategoryFromCursor(cursor);
						lstImCategory.add(imCategory);
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
				return lstImCategory;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng ImCategory theo ID
		 * Trả lại: một đối tượng ImCategory, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public ImCategory getImCategoryByID(int iId) {
			ImCategory imCategoryRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_IM_CATEGORY, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					imCategoryRes = imCategoryFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return imCategoryRes;
			}
		}
		
		@SuppressWarnings("finally")
		private ImCategory imCategoryFromCursor(Cursor cursor) {
			ImCategory imCategoryRes = null;
			try{
				imCategoryRes = new ImCategory();
				imCategoryRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				imCategoryRes.setsCategoryname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_IM_CATEGORY)));
				imCategoryRes.setsSortname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_IM_CATEGORY_SORT_NAME)));
			}
			catch (Exception e) {
				
			}
			finally{
				return imCategoryRes;
			}
		}
}
