package com.sm.movietime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sm.database.DBHelper_MovieTable;
import com.sm.database.Movie;

@SuppressWarnings("deprecation")
public class NowShowing extends Activity {

	List<Movie> now_movies;
	List<Integer> now;

	TextView details;
	Integer currentMovie, x, y;
	Intent btn_intnt, tabs_intnt;
	
	ProgressBar bar;
		
	@Override
	public void onBackPressed() {
		finish();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_showing);        
        currentMovie=0;
        
        now_movies = new ArrayList<Movie>();
        
        DBHelper_MovieTable tbl = new DBHelper_MovieTable(this);
		now_movies = tbl.getMovies("now");
		
		for (int i=0; i<now_movies.size(); i++) {
			Log.d("APP", now_movies.get(i).getSummary());
		}
        
        Field[] fields = R.drawable.class.getFields();
        now = new ArrayList<Integer>();
        for (Field field : fields) {
            if (field.getName().startsWith("now_")) {
                try {
					now.add(field.getInt(null));
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
        details.setText(now_movies.get(0).getTitle());

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
				details.setText(now_movies.get(arg2).getTitle());
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
				if (frame.contains(x, y) && arg2==currentMovie) {
					btn_intnt = new Intent(getBaseContext(), MovieDetails.class);
					btn_intnt.putExtra("MovieTitle", now_movies.get(currentMovie).getTitle());
					btn_intnt.putExtra("MoviePoster", now.get(currentMovie));
					btn_intnt.putExtra("MovieSummary", now_movies.get(currentMovie).getSummary());
					btn_intnt.putExtra("MovieStarring", now_movies.get(currentMovie).getStarring());
					btn_intnt.putExtra("MovieGenre", now_movies.get(currentMovie).getGenre());
					btn_intnt.putExtra("status", "now");
					startActivity(btn_intnt);
				}				
			}
		});        
    }

    @Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
    	x = (int)event.getRawX();
        y = (int)event.getRawY();
		return super.onTouchEvent(event);
	}
    
    public boolean onTouch(View v, MotionEvent event) {
        x = (int)event.getRawX();
        y = (int)event.getRawY();
        return false;
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_now_showing, menu);
        return true;
    }
    	
    public class ImageAdapter extends BaseAdapter {
	     private Context mContext;
	     
	     public ImageAdapter(Context c) { mContext = c; }
	     	
	     public int getCount() { return now_movies.size(); }
	
	     public Object getItem(int position) { return position; }
	
	     public long getItemId(int position) { return position; }
	
	     public View getView(int position, View convertView, ViewGroup parent) {
	    	 ImageView i = new ImageView(mContext);
	    	 i.setImageResource(now.get(position));
	         i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));	         	         
	         return i;
	     }
	 }
}
