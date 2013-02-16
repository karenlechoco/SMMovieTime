package com.sm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_MovieTable extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "movietime";
	private static final String TABLE_MOVIE = "movie";
	
	private static final String KEY_MOVIE_CODE = "code";
	private static final String KEY_MOVIE_TITLE = "title";
	private static final String KEY_MOVIE_POSTERURL = "poster";
	private static final String KEY_MOVIE_SUMMARY = "summary";
	private static final String KEY_MOVIE_GENRE = "genre";
	private static final String KEY_MOVIE_STARRING = "starring";
	private static final String KEY_MOVIE_STATUS = "status";

	public DBHelper_MovieTable (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE + "("
				+ KEY_MOVIE_CODE + "TEXT PRIMARY KEY,"
				+ KEY_MOVIE_TITLE + "TEXT,"
				+ KEY_MOVIE_POSTERURL + "TEXT,"
				+ KEY_MOVIE_SUMMARY + "TEXT,"
				+ KEY_MOVIE_GENRE + "TEXT,"
				+ KEY_MOVIE_STARRING + "TEXT,"
				+ KEY_MOVIE_STATUS + "TEXT"
				+ ")";
		db.execSQL(CREATE_TABLE_MOVIE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
		onCreate(db);
	}

	void addMovie () {}
	
}
