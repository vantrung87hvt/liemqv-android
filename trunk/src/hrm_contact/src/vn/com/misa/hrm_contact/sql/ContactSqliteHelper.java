package vn.com.misa.hrm_contact.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactSqliteHelper extends SQLiteOpenHelper {
	public static final String TABLE_CONTACT = "tblContact";
	public static final String COLUMN_ID = "_id";
	public static final String COL_NAME = "sName";
	public static final String COL_PHONE = "sPhone";
	public static final String COL_EMAIL = "sEmail";

	private static final String DATABASE_NAME = "hrm_contact";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_CONTACT + "( " + COLUMN_ID
			+ " integer primary key autoincrement, "
			+ COL_NAME + " text not null, "
			+ COL_PHONE + " text not null, "
			+ COL_EMAIL + " text not null);";

	public ContactSqliteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(ContactSqliteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);
		onCreate(db);
	}
}
