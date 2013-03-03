package com.sm.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper_MovieTable extends SQLiteOpenHelper {
		
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

	public DBHelper_MovieTable (Context context) {		
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

	public void addMovie (Movie m) {
		SQLiteDatabase db = getWritableDatabase();		

		ContentValues values = new ContentValues();
		values.put(KEY_MOVIE_CODE, m.getCode());
		values.put(KEY_MOVIE_TITLE, m.getTitle());
		values.put(KEY_MOVIE_POSTERURL, m.getPosterUrl());
		values.put(KEY_MOVIE_SUMMARY, m.getSummary());
		values.put(KEY_MOVIE_GENRE, m.getGenre());
		values.put(KEY_MOVIE_STARRING, m.getStarring());
		values.put(KEY_MOVIE_STATUS, m.getStatus());

		db.insert(TABLE_MOVIE, null, values);
		db.close();
	}
	
	public List<Movie> getMovies (String status) {
		SQLiteDatabase db = getReadableDatabase();
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "SELECT * FROM " + TABLE_MOVIE + " WHERE " + KEY_MOVIE_STATUS + "='" + status + "'";
		Cursor c = db.rawQuery(sql, null);
		while (c.moveToNext()) {
			Movie m = new Movie();
			m.setCode(c.getString(c.getColumnIndex(KEY_MOVIE_CODE)));
			m.setGenre(c.getString(c.getColumnIndex(KEY_MOVIE_GENRE)));
			m.setPosterUrl(c.getString(c.getColumnIndex(KEY_MOVIE_POSTERURL)));
			m.setStarring(c.getString(c.getColumnIndex(KEY_MOVIE_STARRING)));
			m.setStatus(c.getString(c.getColumnIndex(KEY_MOVIE_STATUS)));
			m.setSummary(c.getString(c.getColumnIndex(KEY_MOVIE_SUMMARY)));
			m.setTitle(c.getString(c.getColumnIndex(KEY_MOVIE_TITLE)));
			movies.add(m);
		}
		c.close();
		return movies;
	}
	
	public Movie getMovie (String code) {
		Movie m = new Movie();
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor c = db.query(TABLE_MOVIE, 
				new String[] { KEY_MOVIE_TITLE, KEY_MOVIE_SUMMARY, KEY_MOVIE_GENRE, KEY_MOVIE_STARRING }, 
				KEY_MOVIE_CODE + "=?",
				new String[] { code }, null, null, null, null);
		
		if (c.moveToNext()) {
			m.setCode(code);
			m.setGenre(c.getString(c.getColumnIndex(KEY_MOVIE_GENRE)));
			m.setPosterUrl(c.getString(c.getColumnIndex(KEY_MOVIE_POSTERURL)));
			m.setStarring(c.getString(c.getColumnIndex(KEY_MOVIE_STARRING)));
			m.setSummary(c.getString(c.getColumnIndex(KEY_MOVIE_SUMMARY)));
			m.setTitle(c.getString(c.getColumnIndex(KEY_MOVIE_TITLE)));
		}
		c.close();
		return m;
	}
	
}
