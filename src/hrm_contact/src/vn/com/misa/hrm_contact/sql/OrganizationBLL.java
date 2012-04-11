/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng tblOrganization
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Organization;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OrganizationBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblOrganization
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_ORG_COMPANY, HrmContactSqliteHelper.TABLE_ORG_TITLE,
				HrmContactSqliteHelper.FK_CONTACT};

		public OrganizationBLL(Context context) {
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
		 * Hàm Insert một Organization mới vào database
		 * Trả lại: đối tượng Organization đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Organization createOrganization(Organization organization) {
			Organization organizationRes = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_ORG_COMPANY, organization.getsCompany());
				values.put(HrmContactSqliteHelper.TABLE_ORG_TITLE, organization.getsTitle());
				values.put(HrmContactSqliteHelper.FK_CONTACT, organization.getiFK_ContactID());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_ORG, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ORG,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				organizationRes = organizationFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return organizationRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một Organization
		 * Tham số: một đối tượng Organization, sẽ cập nhật các trường của đối tượng Organization hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateOrganization(Organization organization)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_ORG_COMPANY, organization.getsCompany());
			    values.put(HrmContactSqliteHelper.TABLE_ORG_TITLE, organization.getsTitle());
			    values.put(HrmContactSqliteHelper.FK_CONTACT, organization.getiFK_ContactID());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_ORG, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(organization.getiId()) }) > 0)
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
		 * Hàm delete một Organization
		 * Tham số: id của Organization cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteOrganization(int iId) {
			boolean bRes = false;
			try
			{
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_ORG, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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
		 * Hàm lấy danh sách tất cả Organization trong bảng tblOrganization
		 * Trả lại: kiểu List<Organization>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Organization> getAllOrganization() {
			List<Organization> lstOrganization = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ORG, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_ORG_COMPANY);
				if(cursor.getCount() > 0)
				{
					lstOrganization = new ArrayList<Organization>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Organization objOrganization = organizationFromCursor(cursor);
						lstOrganization.add(objOrganization);
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
				return lstOrganization;
			}
		}
		

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Organization theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Organization getOrganizationByID(int iId) {
			Organization organizationRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ORG, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					organizationRes = organizationFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return organizationRes;
			}
		}
		
		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm kiểm tra trùng organization
		 * Trả lại: true nếu trùng, ngược lại trả lại false
		 * */
		@SuppressWarnings("finally")
		public boolean checkDupOrganization(String strOrganization) {
			boolean bRes = false;
			Cursor cursor = null;
			try
			{
				String strWhere = HrmContactSqliteHelper.TABLE_ORG_COMPANY+ " = '" + strOrganization + "'";
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ORG,
						allColumns, strWhere, null, null, null, null);
				cursor.moveToFirst();
				if(!cursor.isAfterLast())
				{
					if(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ORG_COMPANY)).equals(strOrganization))
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
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Organization
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Organization organizationFromCursor(Cursor cursor) {
			Organization organizationRes = null;
			try{
				
			}catch (Exception e) {
				organizationRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				organizationRes.setsCompany(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ORG_COMPANY)));
				organizationRes.setsTitle(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ORG_TITLE)));
				organizationRes.setiFK_ContactID(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.FK_CONTACT)));
			}finally{
				return organizationRes;
			}
		}
}
