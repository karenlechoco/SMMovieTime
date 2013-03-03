package com.sm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB_Initializer extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	
	private static final String TABLE_MOVIE = "movie";
	private static final String KEY_MOVIE_CODE = "code";
	private static final String KEY_MOVIE_TITLE = "title";
	private static final String KEY_MOVIE_POSTERURL = "posterurl";
	private static final String KEY_MOVIE_SUMMARY = "summary";
	private static final String KEY_MOVIE_GENRE = "genre";
	private static final String KEY_MOVIE_STARRING = "starring";
	private static final String KEY_MOVIE_STATUS = "status";
	
	private static final String TABLE_USER = "useraccount";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PASSWORD = "password";
	
	private static final String TABLE_CINEMA = "schedule";
	private static final String KEY_GENLOC = "general_location";
	private static final String KEY_SPECLOC = "specific_location";
	private static final String KEY_CINEMA = "cinema_name";
	private static final String KEY_SCHED_MOVIE_CODE = "movie_code";
	private static final String KEY_DATE = "date";
	private static final String KEY_TIME = "time";
	private static final String KEY_TICKET_PRICE = "ticket_price";
	private static final String KEY_VACANT_SEATS = "vacant_seats";
	
	SQLiteDatabase database;
	
	public DB_Initializer(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		database = getReadableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE + "("
				+ KEY_MOVIE_CODE + " TEXT PRIMARY KEY,"
				+ KEY_MOVIE_TITLE + " TEXT,"
				+ KEY_MOVIE_POSTERURL + " TEXT,"
				+ KEY_MOVIE_SUMMARY + " TEXT,"
				+ KEY_MOVIE_GENRE + " TEXT,"
				+ KEY_MOVIE_STARRING + " TEXT,"
				+ KEY_MOVIE_STATUS + " TEXT"
				+ ")";
		db.execSQL(CREATE_TABLE_MOVIE);
		Log.d("Database", CREATE_TABLE_MOVIE);
		
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" 
				+ KEY_EMAIL + " TEXT PRIMARY KEY,"
				+ KEY_PASSWORD + " TEXT"
				+ ")";
		db.execSQL(CREATE_USER_TABLE);
		Log.d("Database", CREATE_USER_TABLE);
		
		String CREATE_CINEMA_TABLE = "CREATE TABLE " + TABLE_CINEMA + "("
				+ KEY_GENLOC + " TEXT,"
				+ KEY_SPECLOC + " TEXT,"
				+ KEY_CINEMA + " TEXT,"
				+ KEY_SCHED_MOVIE_CODE + " TEXT,"
				+ KEY_DATE + " TEXT,"
				+ KEY_TIME + " TEXT,"
				+ KEY_TICKET_PRICE + " TEXT,"
				+ KEY_VACANT_SEATS + " TEXT"
				+ ")";
		db.execSQL(CREATE_CINEMA_TABLE);
		Log.d("Database", CREATE_CINEMA_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_MOVIE);
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_CINEMA);
		onCreate(db);
	}

}
