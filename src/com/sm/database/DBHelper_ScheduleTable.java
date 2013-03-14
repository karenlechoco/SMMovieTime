package com.sm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper_ScheduleTable extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_SCHEDULE = "schedule";
	
	private static final String KEY_GENLOC = "general_location"; //Metro Manila, Luzon, VisMin
	private static final String KEY_SPECLOC = "specific_location"; //Bicutan, Baguio, Bacolod
	private static final String KEY_CINEMA_NAME = "cinema_name"; //Cinema 1, IMAX, etc
	private static final String KEY_CINEMA_TYPE = "cinema_type";
	private static final String KEY_MOVIE_NAME = "movie_name";
	private static final String KEY_DATE = "date";
	private static final String KEY_TIME = "time";
	private static final String KEY_TICKET_PRICE = "ticket_price";
	private static final String KEY_VACANT_SEATS = "vacant_seats";
	
	public void addSchedule (Schedule s) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(KEY_CINEMA_NAME, s.getCinemaName());
		values.put(KEY_CINEMA_TYPE, s.getCinemaType());
		values.put(KEY_DATE, s.getDate());
		values.put(KEY_GENLOC, s.getGeneralLocation());
		values.put(KEY_MOVIE_NAME, s.getMovieName());
		values.put(KEY_SPECLOC, s.getSpecificLocation());
		values.put(KEY_TIME, s.getTime());
		values.put(KEY_TICKET_PRICE, s.getTicketPrice());
		values.put(KEY_VACANT_SEATS, s.getVacantSeats());
		
		long num = db.insert(TABLE_SCHEDULE, null, values);
		db.close();
		Log.d("Sched Table", String.valueOf(num));
	}
	
	public DBHelper_ScheduleTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
	
}
