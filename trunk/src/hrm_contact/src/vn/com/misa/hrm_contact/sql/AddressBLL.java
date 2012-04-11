/**
 * Create by: liemqv
 * Create date: 11/04/2012 
 * Lớp này để thực hiện các nghiệp vụ thao tác với bảng tblAddress
 * Cần phải truyền vào đối tượng Context trong hàm tạo
 * */
package vn.com.misa.hrm_contact.sql;

import java.util.ArrayList;
import java.util.List;

import vn.com.misa.hrm_contact.bean.Address;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AddressBLL {
		Context context = null;
		private SQLiteDatabase databaseHelper;
		private HrmContactSqliteHelper hrmcontactSqlHelper;
		/**
		 * Danh sách các cột của bảng tblAddress
		 * */
		private String[] allColumns = { HrmContactSqliteHelper.PRIMARY_KEY,
				HrmContactSqliteHelper.TABLE_ADDRESS_STREET, HrmContactSqliteHelper.TABLE_ADDRESS_CITY,
				HrmContactSqliteHelper.TABLE_ADDRESS_STATE, HrmContactSqliteHelper.TABLE_ADDRESS_ZIPCODE,
				HrmContactSqliteHelper.TABLE_ADDRESS_FK_CATEGORY, HrmContactSqliteHelper.FK_CONTACT};

		public AddressBLL(Context context) {
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
		 * Hàm Insert một Address mới vào database
		 * Trả lại: đối tượng Address đã thêm vào, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Address createAddress(Address address) {
			Address addressRes = null;
			Cursor cursor = null;
			try{
				ContentValues values = new ContentValues();
				/*Đẩy các tham số vào parameter để insert */
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_STREET, address.getsStreet());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_CITY, address.getsCity());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_STATE, address.getsState());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_ZIPCODE, address.getsZipcode());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_FK_CATEGORY, address.getiFK_Address_categoryID());
				values.put(HrmContactSqliteHelper.FK_CONTACT, address.getiFK_iContactID());
				
				long longInsertID = databaseHelper.insert(HrmContactSqliteHelper.TABLE_ADDRESS, null, values);
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ADDRESS,
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + longInsertID, null, null, null, null);
				cursor.moveToFirst();
				addressRes = addressFromCursor(cursor);
				cursor.close();
			}catch (Exception e) {
				
			}finally{
				return addressRes;
			}
		}

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm update một Address
		 * Tham số: một đối tượng Address, sẽ cập nhật các trường của đối tượng Address hiện tại theo iId
		 * Trả lại: boolean cho biết kết quả cập nhật
		 * */
		@SuppressWarnings("finally")
		public boolean updateAddress(Address address)
		{
			boolean bRes = false;
			try{
			    SQLiteDatabase sqlDb = hrmcontactSqlHelper.getWritableDatabase();
			    ContentValues values = new ContentValues();
			    values.put(HrmContactSqliteHelper.TABLE_ADDRESS_STREET, address.getsStreet());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_CITY, address.getsCity());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_STATE, address.getsState());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_ZIPCODE, address.getsZipcode());
				values.put(HrmContactSqliteHelper.TABLE_ADDRESS_FK_CATEGORY, address.getiFK_Address_categoryID());
				values.put(HrmContactSqliteHelper.FK_CONTACT, address.getiFK_iContactID());
	
			    if(sqlDb.update(HrmContactSqliteHelper.TABLE_ADDRESS, values, HrmContactSqliteHelper.PRIMARY_KEY + " = ?",
			            new String[] { String.valueOf(address.getiId()) }) > 0)
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
		 * Hàm delete một Address
		 * Tham số: id của Address cần xóa
		 * Trả lại: boolean cho biết kết quả xóa
		 * */
		@SuppressWarnings("finally")
		public boolean deleteAddress(int iId) {
			boolean bRes = false;
			try
			{
				if(databaseHelper.delete(HrmContactSqliteHelper.TABLE_ADDRESS, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null) > 0)
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
		 * Hàm lấy danh sách tất cả Address trong bảng tblAddress
		 * Trả lại: kiểu List<Address>, nếu lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public List<Address> getAllAddress() {
			List<Address> lstAddress = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ADDRESS, allColumns, null, null, null, null, HrmContactSqliteHelper.TABLE_ADDRESS_FK_CATEGORY);
				if(cursor.getCount() > 0)
				{
					lstAddress = new ArrayList<Address>();
					cursor.moveToFirst();
					while (!cursor.isAfterLast()) {
						Address objAddress = addressFromCursor(cursor);
						lstAddress.add(objAddress);
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
				return lstAddress;
			}
		}
		

		/**
		 * Create date: 10/04/2012
		 * Create by: liemqv
		 * Hàm lấy một đối tượng Address theo ID
		 * Trả lại: một đối tượng Contact, nếu ID không tồn tại hoặc lỗi trả lại null
		 * */
		@SuppressWarnings("finally")
		public Address getAddressByID(int iId) {
			Address addressRes = null;
			Cursor cursor = null;
			try
			{
				cursor = databaseHelper.query(HrmContactSqliteHelper.TABLE_ADDRESS, 
						allColumns, HrmContactSqliteHelper.PRIMARY_KEY + " = " + iId, null, null, null, null);
				if(cursor.getCount() > 0)
				{
					cursor.moveToFirst();
					addressRes = addressFromCursor(cursor);
				}
			}
			catch (Exception e) {
				
			}
			finally{
				cursor.close();
				return addressRes;
			}
		}
		

		/**
		 * Create date: 11/04/2012
		 * Create by: liemqv
		 * Hàm chuyển đổi con trỏ Cursor thành một đối tượng Contact
		 * Trả lại: một đối tượng Contact, ngược lại trả lại null
		 * */
		@SuppressWarnings("finally")
		private Address addressFromCursor(Cursor cursor) {
			Address addressRes = null;
			try{
				
			}catch (Exception e) {
				addressRes.setiId(cursor.getInt(cursor.getColumnIndex(HrmContactSqliteHelper.PRIMARY_KEY)));
				addressRes.setsStreet(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ADDRESS_STREET)));
				addressRes.setsCity(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ADDRESS_CITY)));
				addressRes.setsState(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ADDRESS_STATE)));
				addressRes.setsZipcode(cursor.getString(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ADDRESS_ZIPCODE)));
				addressRes.setiFK_Address_categoryID(cursor.getColumnIndex(HrmContactSqliteHelper.TABLE_ADDRESS_FK_CATEGORY));
				addressRes.setiFK_iContactID(cursor.getColumnIndex(HrmContactSqliteHelper.FK_CONTACT));
			}finally{
				return addressRes;
			}
		}
}
