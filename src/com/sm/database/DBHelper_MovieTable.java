package com.sm.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
		String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE + "("
				+ KEY_MOVIE_CODE + " TEXT PRIMARY KEY,"
				+ KEY_MOVIE_TITLE + " TEXT,"
				+ KEY_MOVIE_POSTERURL + " TEXT,"
				+ KEY_MOVIE_SUMMARY + " TEXT,"
				+ KEY_MOVIE_GENRE + " TEXT,"
				+ KEY_MOVIE_STARRING + " TEXT,"
				+ KEY_MOVIE_STATUS + " TEXT"
				+ ")";
		System.out.println(CREATE_TABLE_MOVIE);
		db.execSQL(CREATE_TABLE_MOVIE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
		onCreate(db);
	}

	public void addMovie (Movie m) {
		SQLiteDatabase db = getReadableDatabase();		

		ContentValues values = new ContentValues();
		values.put(KEY_MOVIE_CODE, m.getCode());
		values.put(KEY_MOVIE_TITLE, m.getTitle());
		values.put(KEY_MOVIE_POSTERURL, m.getPosterUrl());
		values.put(KEY_MOVIE_SUMMARY, m.getSummary());
		values.put(KEY_MOVIE_GENRE, m.getGenre());
		values.put(KEY_MOVIE_STARRING, m.getStarring());
		values.put(KEY_MOVIE_STATUS, m.getStatus());
		
		

		db.insert(TABLE_MOVIE, null, values);
		db.close(); // Closing database connection
		Log.d("APP", "done addMovie()");
	}
	
	public List<Movie> getMovies (String status) {
		SQLiteDatabase db = getReadableDatabase();
		List<Movie> movies = new ArrayList<Movie>();
		Movie m = new Movie();
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MOVIE + " WHERE " + KEY_MOVIE_STATUS + "='" + status + "'", null);
		if (c.moveToFirst()) {
			do {
				m.setCode(c.getString(c.getColumnIndex(KEY_MOVIE_CODE)));
				m.setGenre(c.getString(c.getColumnIndex(KEY_MOVIE_GENRE)));
				m.setPosterUrl(c.getString(c.getColumnIndex(KEY_MOVIE_POSTERURL)));
				m.setStarring(c.getString(c.getColumnIndex(KEY_MOVIE_STARRING)));
				m.setStatus(c.getString(c.getColumnIndex(KEY_MOVIE_STATUS)));
				m.setSummary(c.getString(c.getColumnIndex(KEY_MOVIE_SUMMARY)));
				m.setTitle(c.getString(c.getColumnIndex(KEY_MOVIE_TITLE)));
				movies.add(m);
			} while (c.moveToNext());
		}
		return movies;
	}
	
	public Movie getMovie (String code) {
		Movie m = new Movie();
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_MOVIE + " WHERE " + KEY_MOVIE_CODE + "='" + code + "'", null);
		if (c!=null) {
			m.setCode(code);
			m.setGenre(c.getString(c.getColumnIndex(KEY_MOVIE_GENRE)));
			m.setPosterUrl(c.getString(c.getColumnIndex(KEY_MOVIE_POSTERURL)));
			m.setStarring(c.getString(c.getColumnIndex(KEY_MOVIE_STARRING)));
			m.setSummary(c.getString(c.getColumnIndex(KEY_MOVIE_SUMMARY)));
			m.setTitle(c.getString(c.getColumnIndex(KEY_MOVIE_TITLE)));
		}
		
		return m;
	}
	
}
