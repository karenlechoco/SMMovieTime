package com.sm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_SpecificLocationDefaultTable extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_NAME = "spec_loc_default";
	
	private static final String KEY_SPECLOC_CODE = "specloc_code";
	private static final String KEY_CINEMA_CODES = "cinema_codes";
	
	public DBHelper_SpecificLocationDefaultTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
				+ KEY_SPECLOC_CODE + "TEXT PRIMARY KEY,"
				+ KEY_CINEMA_CODES + "TEXT"
				+ ")";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
