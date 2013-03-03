package com.sm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_ScheduleTable extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_CINEMA = "schedule";
	
	private static final String KEY_GENLOC = "general_location"; //Metro Manila, Luzon, VisMin
	private static final String KEY_SPECLOC = "specific_location"; //Bicutan, Baguio, Bacolod
	private static final String KEY_CINEMA = "cinema_name"; //Cinema 1, IMAX, etc
	private static final String KEY_MOVIE_CODE = "movie_code";
	private static final String KEY_DATE = "date";
	private static final String KEY_TIME = "time";
	private static final String KEY_TICKET_PRICE = "ticket_price";
	private static final String KEY_VACANT_SEATS = "vacant_seats";
	
	
	public DBHelper_ScheduleTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
	public void addSchedule (Schedule s) {
		
	}

}
