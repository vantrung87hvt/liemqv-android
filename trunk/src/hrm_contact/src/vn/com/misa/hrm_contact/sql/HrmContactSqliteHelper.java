package vn.com.misa.hrm_contact.sql;

import vn.com.misa.hrm_contact.bean.AddressCategory;
import vn.com.misa.hrm_contact.bean.EmailCategory;
import vn.com.misa.hrm_contact.bean.ImCategory;
import vn.com.misa.hrm_contact.bean.Organization;
import vn.com.misa.hrm_contact.bean.PhoneCategory;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HrmContactSqliteHelper extends SQLiteOpenHelper {
	public Context context;
	/*---- Database  -------*/
	private static final String DATABASE_NAME = "hrm_contact";
	private static final int DATABASE_VERSION = 2;
	
	/*----Bang tblContact --*/
	public static final String TABLE_CONTACT = "tblContact";
	public static final String TABLE_CONTACT_ID = "_id";
	public static final String TABLE_CONTACT_FIRSTNAME = "sFirstname";
	public static final String TABLE_CONTACT_LASTNAME = "sLastname";
	public static final String TABLE_CONTACT_GENDER = "iGender";
	public static final String TABLE_CONTACT_NOTES = "sNotes";
	public static final String TABLE_CONTACT_NICKNAME = "sNickname";
	public static final String TABLE_CONTACT_AVATAR = "bAvatar";
	
	/*----  --*/
	public static final String FK_CONTACT = "FK_iContactID";
	public static final String CATEGORY_NAME = "sCategoryname";
	public static final String PRIMARY_KEY = "_id";
	
	/*----Bang tblPhone_category --*/
	public static final String TABLE_PHONE_CATEGORY = "tblPhone_category";

	/*----Bang tblPhone --*/
	public static final String TABLE_PHONE = "tblPhone";
	public static final String TABLE_PHONE_NUMBER = "sNumber";
	public static final String TABLE_PHONE_FK_CATEGORY = "FK_iPhone_CategoryID";
	
	/*----Bang tblEmail_category --*/
	public static final String TABLE_EMAIL_CATEGORY = "tblEmail_category";
	
	/*----Bang tblEmail --*/
	public static final String TABLE_EMAIL = "tblEmail";
	public static final String TABLE_EMAIL_EMAIL = "sEmail";
	public static final String TABLE_EMAIL_FK_CATEGORY = "FK_iEmail_CategoryID";
	
	/*----Bang tblAddress_category --*/
	public static final String TABLE_ADDRESS_CATEGORY = "tblAddress_category";
	
	/*----Bang tblAddress --*/
	public static final String TABLE_ADDRESS = "tblAddress";
	public static final String TABLE_ADDRESS_STREET = "sStreet";
	public static final String TABLE_ADDRESS_CITY = "sCity";
	public static final String TABLE_ADDRESS_STATE = "sState";
	public static final String TABLE_ADDRESS_ZIPCODE = "sZipcode";
	public static final String TABLE_ADDRESS_FK_CATEGORY = "FK_iAddress_CategoryID";
	
	/*----Bang tblIm_category --*/
	public static final String TABLE_IM_CATEGORY = "tblIm_category";
	public static final String TABLE_IM_CATEGORY_SORT_NAME = "sSortname";
	
	/*----Bang tblIm --*/
	public static final String TABLE_IM = "tblIm";
	public static final String TABLE_IM_IM = "sIm";
	public static final String TABLE_IM_FK_CATEGORY = "FK_iIm_cateogryID";
	
	/*----Bang tblOrganization --*/
	public static final String TABLE_ORG = "tblOrganization";
	public static final String TABLE_ORG_COMPANY = "sCompany";
	public static final String TABLE_ORG_TITLE = "sTitle";
	
	/*----Bang tblWebsite --*/
	public static final String TABLE_WEBSITE = "tblWebsite";
	public static final String TABLE_WEBSITE_WEBSITE = "sWebsite";
	
	public HrmContactSqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {
		createDatabase(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(HrmContactSqliteHelper.class.getName(),
				"Upgrading database hrm_contact from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONE_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMAIL_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMAIL);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_IM_CATEGORY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_IM);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WEBSITE);
		onCreate(db);
	}
	
	public void createDatabase(SQLiteDatabase database)
	{
		String strTableContact = "create table "
				+ TABLE_CONTACT + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_CONTACT_FIRSTNAME + " text not null, "
				+ TABLE_CONTACT_LASTNAME + " text not null, "
				+ TABLE_CONTACT_GENDER + " integer not null, "
				+ TABLE_CONTACT_NOTES + " text not null, "
				+ TABLE_CONTACT_NICKNAME + " text not null, "
				+ TABLE_CONTACT_AVATAR + " blob null);";
		
		String strTablePhoneCategory = "create table "
				+ TABLE_PHONE_CATEGORY + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ CATEGORY_NAME + " text not null);";
		
		String strTablePhone = "create table "
				+ TABLE_PHONE + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_PHONE_NUMBER + " text not null, "
				+ TABLE_PHONE_FK_CATEGORY + " integer not null, "
				+ FK_CONTACT + " integer not null);";
		
		String strTableEmailCategory = "create table "
				+ TABLE_EMAIL_CATEGORY + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ CATEGORY_NAME + " text not null);";
		
		String strTableEmail = "create table "
				+ TABLE_EMAIL + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_EMAIL_EMAIL + " text not null, "
				+ TABLE_EMAIL_FK_CATEGORY + " integer not null, "
				+ FK_CONTACT + " integer not null);";
		
		String strTableAddressCategory = "create table "
				+ TABLE_ADDRESS_CATEGORY + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ CATEGORY_NAME + " text not null);";
		
		String strTableAddress = "create table "
				+ TABLE_ADDRESS + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_ADDRESS_STREET + " text null, "
				+ TABLE_ADDRESS_CITY + " text null, "
				+ TABLE_ADDRESS_STATE + " text null, "
				+ TABLE_ADDRESS_ZIPCODE + " text null, "
				+ TABLE_ADDRESS_FK_CATEGORY + " integer not null, "
				+ FK_CONTACT + " integer not null);";
		
		String strTableImCategory = "create table "
				+ TABLE_IM_CATEGORY + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ CATEGORY_NAME + " text not null);";
		
		String strTableIm = "create table "
				+ TABLE_IM + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_IM_IM + " text not null, "
				+ TABLE_IM_FK_CATEGORY + " integer not null, "
				+ FK_CONTACT + " integer not null);";
		
		String strTableOrganization = "create table "
				+ TABLE_ORG + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_ORG_COMPANY + " text not null, "
				+ TABLE_ORG_TITLE + " text null, "
				+ FK_CONTACT + " integer not null);";
		
		String strTableWebsite = "create table "
				+ TABLE_WEBSITE + "( " + PRIMARY_KEY
				+ " integer primary key autoincrement, "
				+ TABLE_WEBSITE_WEBSITE + " text not null, "
				+ FK_CONTACT + " integer not null);";
		/* --- Execute Database ---*/
		database.execSQL(strTableContact);
		database.execSQL(strTablePhoneCategory);
		database.execSQL(strTablePhone);
		database.execSQL(strTableEmailCategory);
		database.execSQL(strTableEmail);
		database.execSQL(strTableAddressCategory);
		database.execSQL(strTableAddress);
		database.execSQL(strTableImCategory);
		database.execSQL(strTableIm);
		database.execSQL(strTableOrganization);
		database.execSQL(strTableWebsite);
		/* Thêm các danh mục mặc định */
		PhoneCategoryBLL phoneCategoryBLL = new PhoneCategoryBLL(this.context);
		phoneCategoryBLL.createPhoneCategory(new PhoneCategory("Nhà riêng"));
		phoneCategoryBLL.createPhoneCategory(new PhoneCategory("Cơ quan"));
		
		EmailCategoryBLL emailCategoryBLL = new EmailCategoryBLL(this.context);
		emailCategoryBLL.createEmailCategory(new EmailCategory("Cá nhân"));
		emailCategoryBLL.createEmailCategory(new EmailCategory("Công việc"));
		
		AddressCategoryBLL addressCategoryBLL = new AddressCategoryBLL(this.context);
		addressCategoryBLL.createAddressCategory(new AddressCategory("Nhà riêng"));
		addressCategoryBLL.createAddressCategory(new AddressCategory("Cơ quan"));
		
		ImCategoryBLL imCategoryBLL = new ImCategoryBLL(this.context);
		imCategoryBLL.createImCategory(new ImCategory("Skype", "SKY"));
		imCategoryBLL.createImCategory(new ImCategory("Yahoo", "YMS"));
	}
}
