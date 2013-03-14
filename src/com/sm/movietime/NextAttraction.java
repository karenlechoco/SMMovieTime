package com.sm.movietime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
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

@SuppressWarnings({ "deprecation" })
public class NextAttraction extends Activity {

	List<Integer> next;
	List<Movie> next_movies;
	
	TextView details;
	Integer currentMovie, x, y;
	Button moreinfo;
	Intent btn_intnt;
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_attraction);
        currentMovie=0;
        
        next_movies = new ArrayList<Movie>();
        
        DBHelper_MovieTable tbl = new DBHelper_MovieTable(this);
        next_movies = tbl.getMovies("next");
        
        Field[] fields = R.drawable.class.getFields();
        next = new ArrayList<Integer>();
        for (Field field : fields) {
            if (field.getName().startsWith("next_")) {
                try {
					next.add(field.getInt(null));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
        
        details = (TextView)findViewById(R.id.details);
        details.setText(next_movies.get(0).getTitle());
        
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
				details.setText(next_movies.get(arg2).getTitle());
				currentMovie = arg2;
				arg1.setAlpha(1);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
                        
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Rect frame = new Rect();
				arg1.getGlobalVisibleRect(frame);
				
				//check if clicked poster is one at the center
				//if yes, go to moviedetails.class
				//if no, the unselected poster becomes center
				if (arg2==currentMovie) {
					btn_intnt = new Intent(getBaseContext(), MovieDetails.class);
					btn_intnt.putExtra("MovieTitle", next_movies.get(currentMovie).getTitle());
					btn_intnt.putExtra("MoviePoster", next.get(currentMovie));
					btn_intnt.putExtra("MovieSummary", next_movies.get(currentMovie).getSummary());
					btn_intnt.putExtra("MovieStarring", next_movies.get(currentMovie).getStarring());
					btn_intnt.putExtra("MovieGenre", next_movies.get(currentMovie).getGenre());
					btn_intnt.putExtra("status", "next");
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
	     
	     public int getCount() { return next_movies.size(); }
	
	     public Object getItem(int position) { return position; }
	
	     public long getItemId(int position) { return position; }
	
	     public View getView(int position, View convertView, ViewGroup parent) {
	    	 ImageView i = new ImageView(mContext);
	    	 i.setImageResource(next.get(position));
	         i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));        
	         return i;
	     }	
	 }
}
