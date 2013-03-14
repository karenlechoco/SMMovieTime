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
	private static final String KEY_FNAME = "firstname";
	private static final String KEY_MNAME = "middlename";
	private static final String KEY_LNAME = "lastname";
	private static final String KEY_CITY = "city";
	private static final String KEY_ADDRESS = "adress";
	private static final String KEY_BIRTHDATE = "birthdate";
	private static final String KEY_COMPANY = "company";
	private static final String KEY_MOBILE = "mobile";
	private static final String KEY_PHONE = "phone";
	
	private static final String TABLE_SCHEDULE = "schedule";
	private static final String KEY_GENLOC = "general_location";
	private static final String KEY_SPECLOC = "specific_location";
	private static final String KEY_CINEMA_NAME = "cinema_name";
	private static final String KEY_CINEMA_TYPE = "cinema_type";
	private static final String KEY_MOVIE_NAME = "movie_name";
	private static final String KEY_DATE = "date";
	private static final String KEY_TIME = "time";
	private static final String KEY_TICKET_PRICE = "ticket_price";
	private static final String KEY_VACANT_SEATS = "vacant_seats";
	
	private static final String TABLE_PREFS = "preferences";
	private static final String KEY_LOGGEDIN = "logged_username";
	
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
				+ KEY_PASSWORD + " TEXT,"
				+ KEY_FNAME + " TEXT," 
				+ KEY_MNAME + " TEXT,"
				+ KEY_LNAME + " TEXT,"
				+ KEY_ADDRESS + " TEXT,"
				+ KEY_CITY + " TEXT,"
				+ KEY_BIRTHDATE + " TEXT,"
				+ KEY_COMPANY + " TEXT,"
				+ KEY_MOBILE + " TEXT,"
				+ KEY_PHONE + " TEXT"
				+ ")";
		db.execSQL(CREATE_USER_TABLE);
		Log.d("Database", CREATE_USER_TABLE);
		
		String CREATE_CINEMA_TABLE = "CREATE TABLE " + TABLE_SCHEDULE + "("
				+ KEY_GENLOC + " TEXT,"
				+ KEY_SPECLOC + " TEXT,"
				+ KEY_CINEMA_NAME + " TEXT,"
				+ KEY_MOVIE_NAME + " TEXT,"
				+ KEY_DATE + " TEXT,"
				+ KEY_TIME + " TEXT,"
				+ KEY_TICKET_PRICE + " DOUBLE,"
				+ KEY_CINEMA_TYPE + " TEXT,"
				+ KEY_VACANT_SEATS + " INTEGER"
				+ ")";
		db.execSQL(CREATE_CINEMA_TABLE);
		Log.d("Database", CREATE_CINEMA_TABLE);
		
		String CREATE_PREFS_TABLE = "CREATE TABLE " + TABLE_PREFS + "(" 
				+ KEY_LOGGEDIN + " TEXT"
				+ ")";
		db.execSQL(CREATE_PREFS_TABLE);
		Log.d("Database", CREATE_CINEMA_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_MOVIE);
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_USER);
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_SCHEDULE);
		db.execSQL("DELETE TABLE IF EXISTS " + TABLE_PREFS);
		onCreate(db);
	}
	
	public void Truncate() {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_MOVIE, null, null);
		db.delete(TABLE_SCHEDULE, null, null);
		db.delete(TABLE_USER, null, null);
	}
}