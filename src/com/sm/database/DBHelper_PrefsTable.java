package com.sm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_PrefsTable extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_PREFS = "preferences";
	private static final String KEY_LOGGEDIN = "logged_username";
	
	public DBHelper_PrefsTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public boolean isLoggedIn () {
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = db.query(TABLE_PREFS, new String[]{KEY_LOGGEDIN}, null, null, null, null, null);
		if (c.moveToFirst()) return true; else return false;
	}
	
	public void addPref (String useremail) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_LOGGEDIN, useremail);
		db.insert(TABLE_PREFS, null, values);
		db.close();
	}
	
	public void deletePref () {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_PREFS, null, null);
		db.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}
