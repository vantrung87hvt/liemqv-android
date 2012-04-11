/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng AddressCategory
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.AddressCategory;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AddressCategoryBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.CATEGORY_NAME};

		public AddressCategoryBLL(Context context) {
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
		 * Hàm Insert một đối tượng AddressCategory vào database
		 * Tham số: một đối tượng AddressCategory
		 * Trả lại: đối tượng AddressCategory nếu insert thành công, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public boolean createAddressCategory(AddressCategory addressCategory) {
			long longInsertID = -1;
			try{
				ContentValues values = new ContentValues();
				//Đẩy các tham số vào parameter để insert
				values.put(HrmContactSqliteHelper.PRIMARY_KEY, addressCategory.getiId());
				values.put(HrmContactSqliteHelper.CATEGORY_NAME, addressCategory.getsCategoryname());
				longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_ADDRESS_CATEGORY, null, values);
			}catch (Exception e) {
				
			}finally{
				return (longInsertID != -1 ? true : false);
			}
		}

		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm lấy danh sách AddressCategory từ database
		 * Trả lại: Danh sách AddressCategory có trong database, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<AddressCategory> getAllAddressCategory() {
			List<AddressCategory> lstAddressCategory = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ADDRESS_CATEGORY, allColumns, null, null, null, null, HrmContactSqliteHelper.PRIMARY_KEY);
				if(cursor.getCount() > 0)
				{
					lstAddressCategory = new ArrayList<AddressCategory>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						AddressCategory addressCategory = addressCategoryFromCursor(cursor);
						lstAddressCategory.add(addressCategory);
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
				return lstAddressCategory;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng AddressCategory theo ID
		 * Trả lại: một đối tượng AddressCategory, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public AddressCategory getAddressCategoryByID(int iId) {
			AddressCategory addressCategoryRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ADDRESS_CATEGORY, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					addressCategoryRes = addressCategoryFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return addressCategoryRes;
			}
		}
		
		@SuppressWarnings("finally")
		private AddressCategory addressCategoryFromCursor(Cursor cursor) {
			AddressCategory addressCategoryRes = null;
			try{
				addressCategoryRes = new AddressCategory();
				addressCategoryRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				addressCategoryRes.setsCategoryname(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ADDRESS_CATEGORY)));
			}
			catch (Exception e) {
				
			}
			finally{
				return addressCategoryRes;
			}
		}
}
