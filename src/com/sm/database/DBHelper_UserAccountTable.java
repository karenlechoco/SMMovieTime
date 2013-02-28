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
	
	public DBHelper_UserAccountTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" 
				+ KEY_EMAIL + " TEXT PRIMARY KEY,"
				+ KEY_PASSWORD + " TEXT"
				+ ")";
		Log.d("APP", CREATE_TABLE);
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public void addAccount (UserAccount u) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(KEY_EMAIL, u.getEmail());
		values.put(KEY_PASSWORD, u.getPassword());
		
		db.insert(TABLE_NAME, null, values);
		db.close();
	}
	
	public boolean verifyLogin (UserAccount u) {
		SQLiteDatabase db = getWritableDatabase();
		
		boolean r = false;
		
		if (!u.getEmail().isEmpty() && !u.getPassword().isEmpty()) {
			Cursor c = db.query(TABLE_NAME, 
				new String[]{KEY_EMAIL, KEY_PASSWORD}, KEY_EMAIL + "=? AND " + KEY_PASSWORD + "=?", 
				new String[]{u.getEmail(),u.getPassword()}, null,null,null,null);
			if (c.moveToNext()) r = true;
		}
			
		return r;
	}

}
