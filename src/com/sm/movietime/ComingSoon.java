package com.sm.movietime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.sm.database.DBHelper_MovieTable;
import com.sm.database.DBHelper_PrefsTable;
import com.sm.database.Movie;

@SuppressWarnings("deprecation")
public class ComingSoon extends Activity {

	List<Integer> soon;
	List<Movie> soon_movies;
	
	TextView details;
	Integer currentMovie,x,y;
	Button moreinfo;
	Intent btn_intnt;
    
	@Override
	public void onBackPressed() {
		finish();
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);
        currentMovie=0;
        
        soon_movies = new ArrayList<Movie>();
        
        DBHelper_MovieTable tbl = new DBHelper_MovieTable(this);
        soon_movies = tbl.getMovies("soon");
        
        Field[] fields = R.drawable.class.getFields();
        soon = new ArrayList<Integer>();
        for (Field field : fields) {
            if (field.getName().startsWith("soon_")) {
                try { soon.add(field.getInt(null)); } 
                catch (IllegalArgumentException e) { e.printStackTrace(); } 
                catch (IllegalAccessException e) { e.printStackTrace(); }
            }
        }
        
        details = (TextView)findViewById(R.id.details);
        details.setText(soon_movies.get(0).getTitle());
        
        final Gallery coverFlow = (Gallery)findViewById(R.id.movie_catalogue);
        coverFlow.setAdapter(new ImageAdapter(this));
        coverFlow.setSpacing(10);
        coverFlow.setSelected(true);
        coverFlow.setSelection(0, true);
        coverFlow.setFocusable(false);
        
        coverFlow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				details.setText(soon_movies.get(arg2).getTitle());
				currentMovie = arg2;
				arg1.setAlpha(1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
                        
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) { 
				Rect frame = new Rect();
				arg1.getGlobalVisibleRect(frame);
				
				//check if clicked poster is one at the center
				//if yes, go to moviedetails.class
				//if no, the unselected poster becomes center
				if (arg2==currentMovie) {
					btn_intnt = new Intent(getBaseContext(), MovieDetails.class);
					btn_intnt.putExtra("MovieTitle", soon_movies.get(currentMovie).getTitle());
					btn_intnt.putExtra("MoviePoster", soon.get(currentMovie));
					btn_intnt.putExtra("MovieSummary", soon_movies.get(currentMovie).getSummary());
					btn_intnt.putExtra("MovieStarring", soon_movies.get(currentMovie).getStarring());
					btn_intnt.putExtra("MovieGenre", soon_movies.get(currentMovie).getGenre());
					btn_intnt.putExtra("status", "soon");
					startActivity(btn_intnt);
				}				
			}
		});
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		DBHelper_PrefsTable tbl = new DBHelper_PrefsTable(getBaseContext());
		if (tbl.isLoggedIn())
			getMenuInflater().inflate(R.menu.global_logout_menu, menu);
		else getMenuInflater().inflate(R.menu.global_login_menu, menu);
        return true;
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
		switch (item.getItemId()) {
            case R.id.logout:
                DBHelper_PrefsTable tbl = new DBHelper_PrefsTable(getBaseContext());
                tbl.deletePref();
                i = new Intent(getBaseContext(),TabMenu.class);
                startActivity(i);
                return true;
            case R.id.login:
                i = new Intent(getBaseContext(),CinemaLogin.class);
                startActivity(i);
                //return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public class ImageAdapter extends BaseAdapter {
	     private Context mContext;
	     
	     public ImageAdapter(Context c) { mContext = c; }
	     
	     public int getCount() { return soon.size(); }
	
	     public Object getItem(int position) { return position; }
	
	     public long getItemId(int position) { return position; }
	
	     public View getView(int position, View convertView, ViewGroup parent) {
	    	 ImageView i = new ImageView(mContext);
	    	 i.setImageResource(soon.get(position));
	         i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
	         return i;
	     }
	 }
}
