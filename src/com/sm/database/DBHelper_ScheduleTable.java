package com.sm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_ScheduleTable extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_CINEMA = "schedule";
	
	private static final String KEY_SPECLOC_CODE = "specloc_code";
	private static final String KEY_CINEMA_CODE = "cinema_code";
	private static final String KEY_MOVIE_CODE = "movie_code";
	private static final String KEY_DATE = "date";
	private static final String KEY_TIME = "time";
	private static final String KEY_TICKET_PRICE = "ticket_price";
	private static final String KEY_VACANT_SEATS = "vacant_seats";
	
	
	public DBHelper_ScheduleTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_CINEMA_TABLE = "CREATE TABLE " + TABLE_CINEMA + "("
				+ KEY_SPECLOC_CODE + "TEXT,"
				+ KEY_CINEMA_CODE + "TEXT,"
				+ KEY_MOVIE_CODE + "TEXT,"
				+ KEY_DATE + "TEXT,"
				+ KEY_TIME + "TEXT,"
				+ KEY_TICKET_PRICE + "TEXT,"
				+ KEY_VACANT_SEATS + "TEXT,"
				+ ")";
		db.execSQL(CREATE_CINEMA_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_CINEMA);
		onCreate(db);
	}

}
