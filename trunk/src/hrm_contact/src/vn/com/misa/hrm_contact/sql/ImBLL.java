/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng tblIm
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Im;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ImBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblIm
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_IM_IM, HrmContactSqliteHelper.TABLE_IM_FK_CATEGORY,
				HrmContactSqliteHelper.FK_CONTACT};

		public ImBLL(Context context) {
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
		 * Hàm Insert một Im mới vào database
		 * Trả lại: đối tượng Im đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Im createIm(Im im) {
			Im imRes = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_IM_IM, im.getsIm());
				values.put(HrmContactSqliteHelper.TABLE_IM_FK_CATEGORY, im.getiFK_Im_categoryID());
				values.put(HrmContactSqliteHelper.FK_CONTACT, im.getiFK_ContactID());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_IM, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_IM,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				imRes = imFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return imRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một Im
		 * Tham số: một đối tượng Im, sẽ cập nhật các trường của đối tượng Im hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateIm(Im im)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_IM_IM, im.getsIm());
			    values.put(HrmContactSqliteHelper.TABLE_IM_FK_CATEGORY, im.getiFK_Im_categoryID());
			    values.put(HrmContactSqliteHelper.FK_CONTACT, im.getiFK_ContactID());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_IM, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(im.getiId()) }) > 0)
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
		 * Hàm delete một Im
		 * Tham số: id của Im cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteIm(int iId) {
			boolean bRes = false;
			try
			{
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_IM, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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
		 * Hàm lấy danh sách tất cả Im trong bảng tblIm
		 * Trả lại: kiểu List<Im>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Im> getAllIm() {
			List<Im> lstIm = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_IM, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_IM_FK_CATEGORY);
				if(cursor.getCount() > 0)
				{
					lstIm = new ArrayList<Im>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Im objIm = imFromCursor(cursor);
						lstIm.add(objIm);
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
				return lstIm;
			}
		}
		

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Im theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Im getImByID(int iId) {
			Im imRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_IM, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					imRes = imFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return imRes;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng im
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		@SuppressWarnings("finally")
		public boolean checkDupIm(String strIm) {
			boolean bRes = false;
			Cursor cursor = null;
			try
			{
				String strWhere = HrmContactSqliteHelper.TABLE_IM_IM + " = '" + strIm + "'";
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_IM,
						allColumns, strWhere, null, null, null, null);
				cursor.moveToFirst();
				if(!cursor.isAfterLast())
				{
					if(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_IM_IM)).equals(strIm))
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
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Im
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Im imFromCursor(Cursor cursor) {
			Im imRes = null;
			try{
				
			}catch (Exception e) {
				imRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				imRes.setsIm(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_IM_IM)));
				imRes.setiFK_Im_categoryID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_IM_FK_CATEGORY)));
				imRes.setiFK_ContactID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.FK_CONTACT)));
			}finally{
				return imRes;
			}
		}
}
