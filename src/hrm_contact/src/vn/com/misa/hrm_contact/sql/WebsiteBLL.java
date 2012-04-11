/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng tblWebsite
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Website;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class WebsiteBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblWebsite
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE, HrmContactSqliteHelper.FK_CONTACT};

		public WebsiteBLL(Context context) {
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
		 * Hàm Insert một Website mới vào database
		 * Trả lại: đối tượng Website đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Website createWebsite(Website website) {
			Website websiteRes = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE, website.getsWebsite());
				values.put(HrmContactSqliteHelper.FK_CONTACT, website.getiFK_ContactID());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_WEBSITE, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_WEBSITE,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				websiteRes = websiteFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return websiteRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một Website
		 * Tham số: một đối tượng Website, sẽ cập nhật các trường của đối tượng Website hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateWebsite(Website website)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE, website.getsWebsite());
			    values.put(HrmContactSqliteHelper.FK_CONTACT, website.getiFK_ContactID());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_WEBSITE, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(website.getiId()) }) > 0)
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
		 * Hàm delete một Website
		 * Tham số: id của Website cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteWebsite(int iId) {
			boolean bRes = false;
			try
			{
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_WEBSITE, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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
		 * Hàm lấy danh sách tất cả Website trong bảng tblWebsite
		 * Trả lại: kiểu List<Website>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Website> getAllWebsite() {
			List<Website> lstWebsite = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_WEBSITE, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE);
				if(cursor.getCount() > 0)
				{
					lstWebsite = new ArrayList<Website>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Website objWebsite = websiteFromCursor(cursor);
						lstWebsite.add(objWebsite);
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
				return lstWebsite;
			}
		}
		

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Website theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Website getWebsiteByID(int iId) {
			Website websiteRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_WEBSITE, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					websiteRes = websiteFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return websiteRes;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng website
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		@SuppressWarnings("finally")
		public boolean checkDupWebsite(String strWebsite) {
			boolean bRes = false;
			Cursor cursor = null;
			try
			{
				String strWhere = HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE+ " = '" + strWebsite + "'";
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_WEBSITE,
						allColumns, strWhere, null, null, null, null);
				cursor.moveToFirst();
				if(!cursor.isAfterLast())
				{
					if(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE)).equals(strWebsite))
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
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Website
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Website websiteFromCursor(Cursor cursor) {
			Website websiteRes = null;
			try{
				
			}catch (Exception e) {
				websiteRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				websiteRes.setsWebsite(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_WEBSITE_WEBSITE)));
				websiteRes.setiFK_ContactID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.FK_CONTACT)));
			}finally{
				return websiteRes;
			}
		}
}
