package com.sm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper_UserAccountTable extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_NAME = "useraccount";

	private static final String KEY_EMAIL = "email";
	private static final String KEY_PASSWORD = "password";
	private static final String KEY_FNAME = "firstname";
	private static final String KEY_MNAME = "middlename";
	private static final String KEY_LNAME = "lastname";
	private static final String KEY_CITY = "city";
	private static final String KEY_ADDRESS = "adress";
	private static final String KEY_BIRTHDATE = "birthdate";
	private static final String KEY_COMPANY = "company";
	private static final String KEY_MOBILE = "mobile";
	private static final String KEY_PHONE = "phone";
	
	public DBHelper_UserAccountTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	public void addAccount (UserAccount u) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(KEY_EMAIL, u.getEmail());
		values.put(KEY_PASSWORD, u.getPassword());
		values.put(KEY_ADDRESS, u.getAddress());
		values.put(KEY_BIRTHDATE, u.getBirthdate());
		values.put(KEY_CITY, u.getCity());
		values.put(KEY_COMPANY, u.getCompanyName());
		values.put(KEY_FNAME, u.getFirstName());
		values.put(KEY_LNAME, u.getLastName());
		values.put(KEY_MNAME, u.getMiddleName());
		values.put(KEY_MOBILE, u.getMobileNumber());
		values.put(KEY_PHONE, u.getPhoneNumber());
		
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public boolean verifyLogin (UserAccount u, Context context) {
		SQLiteDatabase db = getReadableDatabase();
		
		boolean r = false;
		
		if (!u.getEmail().isEmpty() && !u.getPassword().isEmpty()) {
			Cursor c = db.query(TABLE_NAME, 
				new String[]{KEY_EMAIL, KEY_PASSWORD}, KEY_EMAIL + "=? AND " + KEY_PASSWORD + "=?", 
				new String[]{u.getEmail(),u.getPassword()}, null,null,null,null);
			if (c.moveToNext()) r = true;
			
			DBHelper_PrefsTable pt = new DBHelper_PrefsTable(context);
			pt.addPref(u.getEmail());
		}
			
		return r;
	}

}
