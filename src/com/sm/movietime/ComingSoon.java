package com.sm.movietime;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.sm.database.*;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

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
				if (frame.contains(x, y) && arg2==currentMovie) {
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
	public boolean onTouchEvent(MotionEvent event) {
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
        getMenuInflater().inflate(R.menu.activity_coming_soon, menu);
        return true;
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
